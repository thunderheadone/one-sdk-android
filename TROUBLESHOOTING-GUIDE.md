![Thunderhead SDK](https://i.imgur.com/gfizURy.png "Thunderhead")

The Thunderhead SDK for Android Troubleshooting Guide for common implementation issues.

## Table of Contents

- [Integration issues](#integration-issues)
  * [How to resolve StackOverflow Exception](#how-to-resolve-stackoverflow-exception)
  * [Resolving conflicts with `android:allowBackup`](#resolving-conflicts-with-androidallowbackup)
  * [Resolving > The library com.google.firebase:firebase-iid is being requested by various other libraries](#resolving--the-library-comgooglefirebasefirebase-iid-is-being-requested-by-various-other-libraries)
  * [Resolving: NoSuchMethodError for Base64 class or 15_000: Signpost cannot be used on this platform](#resolving-nosuchmethoderror-for-base64-class-or-15_000-signpost-cannot-be-used-on-this-platform)
- [Performance issues](#performance-issues)
  * [Build time](#build-time)

## Integration issues
### How to resolve StackOverflow Exception
Applications that override a base class set listener method may experience a `StackOverflowException`.
The `StackOverflowException` is caused by the application inadvertently taking ownership of a listener
the Thunderhead SDK is setting.  To prevent the exception, the application must take care not to
add the Thunderhead listener to those listeners managed by the application.

In the following example, an application developer has implemented the `setOnPageChangeLister`,
intercepting all `OnPageChangeListeners`. The conditional statement is necessary to
exclude all OnPageChangeListeners the Thunderhead SDK may set from being managed by the application.

Here an application developer has implemented the `setOnPageChangeLister`,
excluding all Thunderhead SDK `OnPageChangeListeners`.

```java
    public final void setOnPageChangeListener(OnPageChangeListener listener) {
        if (listener.getClass().getName().contains("com.thunderhead")) {
            return;
        }
        listeners.add(listener);
    }
```
*Note:*
- Add the `if` statement only to those set listeners that
interfere with the Thunderhead SDK, causing the `StackOverflowException`.

### Resolving conflicts with `android:allowBackup`
The Thunderhead SDK ships with `allowBackup=true` in the SDK's `AndroidManifest.xml`, which is the Android default setting. The Thunderhead SDK sets `allowBackup` to ensure the `tid` in use is backed up in the event the end user application is built to allow backups.  An application disabling backup does not affect the
use of the Thunderhead SDK.  Resetting the end user device followed by a restore, however, may
generate a new user `tid`.  To disable backup in an application, add the following to the applications
`AndroidManifest.xml`
```xml
        tools:replace="android:allowBackup"
        android:allowBackup="false"
```

For more information about backing up user data, see [Back up user data](https://developer.android.com/guide/topics/data/autobackup) in the Android Developer's guide.

### Resolving > The library com.google.firebase:firebase-iid is being requested by various other libraries

The Thunderhead SDK has a dependency on [com.google.firebase:firebase-messaging:17.3.4](https://firebase.google.com/docs/android/setup) which, at the time of writing this article, this article is the latest version.  This dependency transitively depends on firebase-iid.  Projects which integrated Firebase prior to integrating the
Thunderhead SDK may have an older version of the dependency. The Google Cloud Plugin is warning of this version mismatch. We recommend using the same versions for Gradle dependencies declared in the app and that are referenced transitively. This error can be resolved by updating an app's Firebase messaging dependency to the same version the Thunderhead SDK uses. If this is not possible the Thunderhead SDK can use an older version of Firebase artifacts whose version contains the [FirebaseInstanceId class](https://firebase.google.com/docs/reference/android/com/google/firebase/iid/FirebaseInstanceId).

```groovy
configurations.all {
    resolutionStrategy {
        eachDependency { details ->
            if(details.requested.group == "com.google.firebase" && details.requested.name == "firebase-messaging") {
                details.useVersion "17.3.2" // Where 17.3.2 is replaced with the apps required version
            }
            if(details.requested.group == "com.google.android.gms" && details.requested.name == "play-services-basement") {
                details.useVersion "15.0.1" // Where 15.0.1 is replaced with the apps required version
            }
        }
    }
}
```

### Resolving: NoSuchMethodError for Base64 class or 15000: Signpost cannot be used on this platform

This error can occur on some versions of the Android platform that include an outdated version of the `org.apache.commons.codec.binary` package. The platform version of the class is loaded onto the classpath before the bundled version of the class included in the APK. The outdated version of the class does not contain 
a method required by a third-party library which the Thunderhead SDK uses, [Signpost](https://github.com/mttkay/signpost), resulting in the error when attempting to access the missing method. You can see similar issues [here](https://blog.osom.info/2015/04/commons-codec-on-android.html) and on [StackOverflow](https://stackoverflow.com/questions/2047706/apache-commons-codec-with-android-could-not-find-method).

We are investigating long term solutions for this atypical situation and suggest the Thunderhead SDK not be initialized if this method cannot be found. Below is a sample of how this can be achieved.

```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        try { 
          Class base64Class = Base64.class;
          base64Class.getMethod("encode", byte[].class);
          // Init Thunderhead SDK
        } catch (NoSuchMethodException e) {
           Log.e("MyApplication", "Base64 Encode Missing. Not initizliing Thunderhead SDK. " + e.getMessage());
        }
    }
}
```

## Performance issues

### Build time
As instant run is not currently supported it is expected that builds will take longer as a full build will be required when a change is made as opposed to just building the changed bits. We are aware of this limitation and we may consider addressing it in future releases.

Development build times can be improved by disabling Orchestration until the feature is ready for QA. Disabling Orchestration will allow developers to turn on instant run. 
Disabling Orchestration does not remove the ability to use the SDK in [Admin Mode](https://github.com/thunderheadone/one-sdk-android#set-up-the-sdk-in-admin-mode-for-internal-distribution), it disables the codeless identity transfer and last click attribution features, thus allowing developers to still interact with the Thunderhead sdk.

If this is desired, we recommend conditionally applying the Orchestration Plugin via a Gradle project property argument. 
Set a gradle property to true for Release Builds in CI and false for development or local developer builds.  
Read this property in the _app_ `build.gradle` (example shown below) to accomplish this.
Gradle properties are passed via the `-P` command line idiom and can have a default value by 
being placed in the root `gradle.properties` file.
 
Example:

```groovy
// root gradle.properties file
// set to false in order to not orchestrate the app while developing.
enableThunderheadOrchestration=false
```

```groovy
// Place in the app build.gradle file.
if (project.hasProperty("enableThunderheadOrchestration") && project.getProperty("enableThunderheadOrchestration") == "true") {
	apply plugin: 'com.thunderhead.android.orchestration-plugin'
}
```

Then the full build with Orchestration enabled can be executed by passing a project property to the Gradle build.

`./gradlew clean assemble -PenableThunderheadOrchestration=true`

For more information on Gradle project properties please see [the documentation](https://docs.gradle.org/current/userguide/build_environment.html#sec:project_properties).

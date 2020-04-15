![Thunderhead SDK](https://www.thunderhead.com/uploads/2015/07/Thunderhead_LogoIcon_Aubergine.png "Thunderhead")

The Thunderhead SDK for Android Troubleshooting Guide for common implementation issues.

## Table of Contents

- [Integration issues](#integration-issues)
  * [How to resolve StackOverflow Exception](#how-to-resolve-stackoverflow-exception)
  * [Resolving conflicts with `android:allowBackup`](#resolving-conflicts-with-androidallowbackup)
  * [Resolving > The library com.google.firebase:firebase-iid is being requested by various other libraries](#resolving--the-library-comgooglefirebasefirebase-iid-is-being-requested-by-various-other-libraries)
  * [Resolving: NoSuchMethodError for Base64 class or 15_000: Signpost cannot be used on this platform](#resolving-nosuchmethoderror-for-base64-class-or-15_000-signpost-cannot-be-used-on-this-platform)
- [Performance issues](#performance-issues)
  * [Build Time](#build-time)
- [Error codes and resolutions](#error-codes-and-resolutions)
  * [14019: Non Adaptive Icon is not set. Android Api 26 push notifications will not be shown if this is not set](#14019-non-adaptive-icon-is-not-set-android-api-26-push-notifications-will-not-be-shown-if-this-is-not-set)

## Integration issues
### How to resolve StackOverflow Exception
Applications that override a base class set listener method may experience a `StackOverflowException`.
The `StackOverflowException` is caused by the application inadvertently taking ownership of a listener
the Thunderhead SDK is setting.  To prevent the exception the application must take care not to
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
- It is only required to add the `if` statement to those set listeners which
interfere with the Thunderhead SDK, causing the `StackOverflowException`.

### Resolving conflicts with `android:allowBackup`
The Thunderhead SDK ships with `allowBackup=true` in the SDK's `AndroidManifest.xml`, which is the Android default
setting.  The Thunderhead SDK sets `allowBackup` to ensure the `tid` in use is backed up in the event the end user
application is built to allow backups.  An application disabling backup will not effect the
use of the Thunderhead SDK.  However, resetting the end user device followed by a restore may
generate a new user `tid`.  To disable backup in an application, add the following to the applications
`AndroidManifest.xml`
```xml
        tools:replace="android:allowBackup"
        android:allowBackup="false"
```

For more information backing up user data see [Back up user data](https://developer.android.com/guide/topics/data/autobackup)
in the Android Developer's guide.

### Resolving > The library com.google.firebase:firebase-iid is being requested by various other libraries

The Thunderhead SDK has a dependency on [com.google.firebase:firebase-messaging:17.3.4](https://firebase.google.com/docs/android/setup) 
which at the time of this articles writing is the latest version.  This dependency transitively depends on firebase-iid.  Projects which integrated Firebase prior to integrating the
Thunderhead SDK may have an older version of the dependency. The Google Cloud Plugin is warning of this version mismatch. It is recommended
to use the same versions for Gradle dependencies declared in the app and that are referenced transitively. This error can be resolved
by updating an app's Firebase messaging dependency to the same version the Thunderhead SDK uses. If this is not possible the
Thunderhead SDK can use an older version of Firebase artifacts whose version contains 
the [FirebaseInstanceId class](https://firebase.google.com/docs/reference/android/com/google/firebase/iid/FirebaseInstanceId).

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

### Resolving: NoSuchMethodError for Base64 class or 15_000: Signpost cannot be used on this platform

This error can occur on some versions of the Android platform which include an outdated version of the `org.apache.commons.codec.binary` package. The platform
version of the class is loaded onto the classpath before the bundled version of the class included in the APK. The outdated version of the class does not contain 
a method required by a third-party library which the Thunderhead SDK uses, [Signpost](https://github.com/mttkay/signpost), resulting in the error when attempting to access
the missing method. You can see similar issues [here](https://blog.osom.info/2015/04/commons-codec-on-android.html) and on [StackOverflow](https://stackoverflow.com/questions/2047706/apache-commons-codec-with-android-could-not-find-method).

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
As instant run is not supported at this time it is expected that builds will take longer as a full build will be required when a change is made as opposed 
to just building the changed bits. We are aware of this limitation and we may consider addressing it in future releases.

Development build times can be improved by disabling Orchestration until the feature is ready for QA. Disabling Orchestration will allow developers to turn on instant run. 
Disabling Orchestration does not remove the ability to use the SDK in [Admin Mode](https://github.com/thunderheadone/one-sdk-android#set-up-the-framework-in-admin-mode),
it disables the codeless identity transfer and last click attribution features, thus allowing developers to still interact with the Thunderhead sdk.

If this is desired we recommend conditionally enabling/disabling the Orchestration Plugin via a Gradle project property argument. The Orchestration Plugin Gradle DSL api can be configured as follows:

```groovy
// Place in the app build.gradle file.

thunderhead {
    if (project.hasProperty("enableThunderheadOrchestration")) {
        enabled = enableThunderheadOrchestration.toBoolean()
    } else {
        enabled = false
    }
}
```

Then the full build with Orchestration enabled can be executed by passing a project property to the Gradle build.

`./gradlew clean assemble -PenableThunderheadOrchestration=true`

For more information on Gradle project properties please see [the documentation](https://docs.gradle.org/current/userguide/build_environment.html#sec:project_properties).


## Error codes and resolutions

### 14019: Non Adaptive Icon is not set. Android Api 26 push notifications will not be shown if this is not set

Android (O)reo, Api 26, shipped with a platform bug relating to Adaptive Icons and Notifications. The bug can be seen [here](https://issuetracker.google.com/issues/68716460). 
The issue was resolved in Api 27 however it was not back ported to the original Oreo Api 26 platform.  

The Thunderhead SDK will optimize your user's app experience by sending push notifications with _your_ application's icon when appropriate. In order to avoid the infinite crash
loop that the above Android bug causes, the Thunderhead SDK will not show the message if a fallback *NON ADAPTIVE* icon is not set at initialization time on Api 26 devices. 
Changing your application's icon to a non adaptive icon is not required and the fall back is **only required for Api 26**.

The Thunderhead SDK will warn you at init if the icon has not been set by logging the `14019` error.

Here is an example of setting the fallback for Api 26 devices using the built in Android "Star On" non adaptive drawable.  *Important: The icon set must not be adaptive!*

```kotlin
        One.getInstance(context)?.run {
            // set icon before init to avoid warning.
            messageConfig = MessageConfig(android.R.drawable.star_on)
            init(siteKey, touchpoint, apiKey, sharedSecret, userId, mode, host)
            enablePushNotifications(true)
        }
```

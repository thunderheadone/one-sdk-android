![Thunderhead SDK](https://www.thunderhead.com/uploads/2015/07/Thunderhead_LogoIcon_Aubergine.png "Thunderhead")

The Thunderhead SDK for Android Troubleshooting Guide for Common Implementation Issues.

## Integration Issues
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
setting.  The Thunderhead SDK sets `allowBackup` to ensure the TID in use is backed up in the event the end user
application is built to allow backups.  An application disabling backup will not effect the
use of the Thunderhead SDK.  However, resetting the end user device followed by a restore may
generate a new user TID.  To disable backup in an application, add the following to the applications
`AndroidManifest.xml`
```xml
        tools:replace="android:allowBackup"
        android:allowBackup="false"
```

For more information backing up user data see [Back up user data](https://developer.android.com/guide/topics/data/autobackup)
in the Android Developer's guide

### Resolving `java.lang.ClassNotFoundException` when running automation tests
Applications the are developed with a suite of tests within Android Studio  may experience `java.lang.ClassNotFoundException`
when running those tests.  The exception is caused by a reported defect in `aspectJ` plugin used by the codeless Thunderhead SDK.
It is possible to continue running tests encountering `java.lang.ClassNotFoundException` by removing the `aspectJ` dependency
from the test build.  Thunderhead recommends conditionally removing the `aspectJ` plugin by inserting the following conditional statement
around the `aspectJ` dependency within the build.gradle file.

+ For **Thunderhead ONE** integrations:
  ```
    if(!System.getProperty('DISABLE_ASPECT_J').toBoolean()) {
        apply plugin: 'com.archinamon.aspectj'
        aspectj {
             includeAspectsFromJar 'one-sdk'
             ajcArgs << '-Xlint:ignore'
         }
    }
  ```

+ For **Salesforce Interaction Studio** integrations:
  ```
    if(!System.getProperty('DISABLE_ASPECT_J').toBoolean()) {
        apply plugin: 'com.archinamon.aspectj'
        aspectj {
             includeAspectsFromJar 'is-sdk'
             ajcArgs << '-Xlint:ignore'
         }
    }
  ```

+ For each Android Studio test run configuration resulting in `java.lang.ClassNotFoundException`
add the following to the `VM Options` line:
  ```
   -DDISABLE_ASPECT_J=true
  ```
  
### Resolving > The library com.google.firebase:firebase-iid is being requested by various other libraries

The Thunderhead SDK has a dependency on [com.google.firebase:firebase-messaging:17.3.4](https://firebase.google.com/docs/android/setup) 
which at the time of this articles writing is the latest version.  This dependency transitively depends on firebase-iid.  Projects which integrated Firebase prior to integrating the
Thunderhead SDK may have an older version of the dependency. The Google Cloud Plugin is warning of this version mismatch. It is recommended
to use the same versions for gradle dependencies declared in the app and that are referenced transitively. This error can be resolved
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

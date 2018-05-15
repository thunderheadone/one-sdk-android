![Thunderhead ONE SDK](https://www.thunderhead.com/uploads/2015/07/Thunderhead_LogoIcon_Aubergine.png "Thunderhead ONE")

The ONE SDK for Android supports Android 4 (API 14) and above.

## Installation

### Manual installation
1. [Download the latest ONE SDK for Android](https://github.com/thunderheadone/one-sdk-android/releases) and extract the zip.
2. Open your existing Android application in Android Studio.
3. Import the aar file into your project:
+ Select File > New > New Module > Import JAR/.AAR Package.
+ Locate the .aar file on your machine and select Finish.
4. Include the ONE SDK as a dependency into your project:
+ Navigate to your app-level build.gradle file.
+ Add the following, under the dependencies section:
```gradle
dependencies {     
  	implementation project(':name-of-aar-file') 
}
```
+ Ensure the project name matches the module name.
5. Add the ONE SDK dependencies within the same `build.gradle` file. 
+ Add `RenderScript` support under the `defaultConfig` section:
```gradle
defaultConfig {
   renderscriptTargetApi 20
   renderscriptSupportModeEnabled true
}
```
+ Add the following SDK dependencies to your app:
``` gradle 
dependencies {
    implementation 'com.android.support:support-v4:26.1.0'
    implementation 'com.android.support:design:26.1.0'
    implementation 'com.android.support:recyclerview-v7:26.1.0'
    implementation 'com.android.support:customtabs:26.1.0'

    implementation 'oauth.signpost:signpost-jetty6:1.2.1.2'

    implementation 'com.squareup.okhttp:okhttp:3.7.0'
    implementation 'com.squareup.retrofit2:retrofit:2.2.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.2.0'
    implementation 'com.brsanthu:migbase64:2.2'

    implementation 'com.google.code.gson:gson:2.8.0'
}
```
6. Update your `build.gradle` to add codeless identity transfer support.
+ Navigate to the **top-level** `build.gradle` file and add a maven repository url and class path dependencies as shown below:
``` gradle 
buildscript {
    repositories {
        mavenCentral()
            google()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'
        classpath 'com.archinamon:android-gradle-aspectj:3.2.0'
    }
}
```
+ Navigate to your **app-level** `build.gradle` and append the following configuration:
``` gradle 
apply plugin: 'com.archinamon.aspectj-ext'
aspectj {
    includeAspectsFromJar 'com.thunderhead', 'one_aspects'
    ajcArgs << '-Xlint:ignore' 
}
```
#### Manual installation `build.gradle` examples
Example of the **top-level** `build.gradle` file after integration:
``` gradle
buildscript {
    repositories {
        mavenCentral()
             google()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'
        classpath 'com.archinamon:android-gradle-aspectj:3.2.0'
    }
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
        google()
    }
}
```

Example of the **app-level** `build.gradle` file after integration:
``` gradle
apply plugin: 'com.android.application'
apply plugin: 'com.archinamon.aspectj-ext'


android {
    compileSdkVersion 26
    buildToolsVersion '26.0.2'

    defaultConfig {
        applicationId "com.thunderhead.android.demo"
        minSdkVersion 14
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"

        renderscriptTargetApi 20
        renderscriptSupportModeEnabled true
    }

}

aspectj {
    includeAspectsFromJar 'com.thunderhead', 'one_aspects'
    ajcArgs << '-Xlint:ignore'
}

dependencies {
    implementation project(path: ':Thunderhead_android_release_2.18.1')
    implementation fileTree(include: ['*.jar'], dir: 'libs')

    implementation 'com.android.support:support-v4:26.1.0'
    implementation 'com.android.support:design:26.1.0'
    implementation 'com.android.support:recyclerview-v7:26.1.0'
    implementation 'com.android.support:customtabs:26.1.0'

    implementation 'oauth.signpost:signpost-jetty6:1.2.1.2'

    implementation 'com.squareup.okhttp:okhttp:3.7.0'
    implementation 'com.squareup.retrofit2:retrofit:2.2.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.2.0'
    implementation 'com.brsanthu:migbase64:2.2'

    implementation 'com.google.code.gson:gson:2.8.0'
    
}
```

## Use the Codeless ONE SDK for Android
Enable your app to automatically recognize ONE Interactions by executing the following steps.

### Update your Application Manifest File
Update your project's AndroidManifest.xml to include the following permissions:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
```
*Note:* The `SYSTEM_ALERT_WINDOW` permission is only needed for Admin mode builds. In your setup you can add this as a flavor specific permission to avoid having to show this as a permission change to your Play Store users.

### Subclass your `Application` Class
If you haven’t done so already, you will need to subclass your `Application` class in order to be able to initialize the SDK. If you are just creating an `Application` subclass please remember to define it in your app manifest.

### Initialize the SDK
#### Set up the Framework in User mode
To start tracking, capturing, and receiving optimizations with the ONE SDK in User mode, you must first initialize it with your ONE API parameters. You can find your ONE API parameters on the ONE website.

With your parameters ready to hand, add the following lines of code under the Application’s subclass onCreate() method. You must ensure the initialization method is added after super.onCreate() is called.

``` java 
public class YourApplication extends Application {
  
  private static final String siteKey = "ONE-XXXXXXXXXX-1022";
  private static final String touchpointURI = "myAppsNameURI";
  private static final String apiKey = "f713d44a-8af0-4e79-ba7e-xxxxxxxxx";
  private static final String sharedSecret = "bb8bacb2-ffc2-4c52-aaf4-xxx";
  private static final String userId = "yourUsername@yourCompanyName";
  private static final String hostName = "https://xx.thunderhead.com";
  
    @Override
    public void onCreate() {
      One one = One.getInstance(getApplicationContext());
      one.init(siteKey, touchpointURI, apiKey, sharedSecret, userId, OneModes.USER_MODE, hostName);
    }
}
```
*Note:* The User mode SDK build should be setup as part of your release build that is going to be uploaded to the Play Store.

#### Set up the Framework in Admin mode
To use the framework in Admin mode, change the ONE mode to `ADMIN_MODE`, as follows:
``` java 
one.init(siteKey, touchpointURI, apiKey, sharedSecret, userId, OneModes.ADMIN_MODE, hostName);
```
*Note:* If you are running in Admin mode on Android 6.0+, you will need to enable the “draw over other apps” permission via your OS settings. 

**You have now successfully integrated the codeless ONE SDK for Android.**

## Additional features
Follow any of the steps below to access further functions of the SDK.

*Coming soon*

## Further integration details 
### Retrofit 1.9 support
The ONE SDK for Android also supports apps that use Retrofit 1.9. In order to use this, update your app level build.gradle file to contain the following dependencies:
``` java 
dependencies {
    implementation 'com.android.support:support-v4:26.1.0'
    implementation 'com.android.support:design:26.1.0'
    implementation 'com.android.support:recyclerview-v7:26.1.0'
    implementation 'com.android.support:customtabs:26.1.0'

    implementation 'oauth.signpost:signpost-jetty6:1.2.1.2'

    implementation 'com.squareup.retrofit:retrofit:1.9.0'
    implementation 'com.squareup.okhttp:okhttp-urlconnection:2.7.5'
    
    implementation 'com.google.code.gson:gson:2.8.0'
     
    implementation 'com.squareup.okhttp3:okhttp:3.7.0'
    implementation 'com.jakewharton.retrofit:retrofit1-okhttp3-client:1.1.0'
    implementation 'com.brsanthu:migbase64:2.2'
}
```
### How to disable the codeless identity transfer support
To remove the codeless identity transfer functionality for Android, you need to make the following updates:
1. Open the **top-level** `build.gradle` file and remove the following dependency referece.
```gradle 
classpath 'com.archinamon:android-gradle-aspectj:3.2.0'
```
2. Open the **app-level** `build.gradle` file and remove the following references.
```gradle 
apply plugin: 'com.archinamon.aspectj-ext'
aspectj {
    includeAspectsFromJar 'com.thunderhead', 'one_aspects'
    ajcArgs << '-Xlint:ignore' 
}
```

## Questions or need help

_The Thunderhead ONE team is available 24/7 to answer any questions you have. Just email onesupport@thunderhead.com or visit our docs page for more detailed installation and usage information._

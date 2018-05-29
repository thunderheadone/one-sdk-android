![Thunderhead ONE SDK](https://www.thunderhead.com/uploads/2015/07/Thunderhead_LogoIcon_Aubergine.png "Thunderhead ONE")

The ONE SDK for Android supports Android 4.1 (API 16) and above.

## Installation

### Manual installation
1. Open your existing Android application in Android Studio.
2. Include the ONE SDK as a dependency into your project:
+ Navigate to your app-level build.gradle file.
+ Add the following, under the dependencies section:
```gradle
dependencies {     
  implementation ("com.thunderhead.android:one-sdk:2.20.0-alpha") {
      exclude group: retrofit1Package
    }
}
```
+ **Note:** See [Retrofit 1.9 Support](#retro19) for applications that implement Retrofit 1.9
+ Ensure the project name matches the module name.
3. Add the ONE SDK dependencies within the same `build.gradle` file. 
+ Add `RenderScript` support under the `defaultConfig` section:
```gradle
defaultConfig {
   renderscriptTargetApi 20
   renderscriptSupportModeEnabled true
}
```
+ Add the following, under the repositories section:
``` gradle 
repositories {
  maven {
   url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
  }
}
```
+ Append the following configuration:
``` gradle 
apply plugin: 'com.archinamon.aspectj-ext'
aspectj {
    includeAspectsFromJar 'com.thunderhead', 'one_aspects'
    ajcArgs << '-Xlint:ignore' 
}
```
4. Update your `build.gradle` to add codeless identity transfer support.
+ Navigate to the **top-level** `build.gradle` file and add a maven repository url and class path dependencies as shown below:
``` gradle 
buildscript {
    repositories {
        jcenter()   // or mavenCentral()
        google()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'
        classpath 'com.archinamon:android-gradle-aspectj:3.2.0'
    }
}
```
#### Manual installation `build.gradle` examples
Example of the **top-level** `build.gradle` file after integration:
``` gradle
buildscript {
    repositories {
        jcenter()
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
    compileSdkVersion 27
    buildToolsVersion '27.0.3'

    defaultConfig {
        applicationId "com.thunderhead.android.demo"
        minSdkVersion 16
        targetSdkVersion 27
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
    implementation ("com.thunderhead.android:one-sdk:2.20.0-alpha") {
      exclude group: retrofit1Package
    }
    implementation fileTree(include: ['*.jar'], dir: 'libs')    
}
```

## Use the Codeless ONE SDK for Android
Enable your app to automatically recognize ONE Interactions by executing the following steps.

### The Thunderhead Application Manifest File Permissions:
Included in the Thunderhead ONE SDK's AndroidManifest.xml are the following permissions which will be merged with your applications AndroidManifest.xml:
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
      super.onCreate();
      
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
### Retrofit 2.x support
The ONE SDK for Android supports apps that use Retrofit 2.X. In order to use this, update your app level build.gradle file to contain the following dependencies:
``` java 
dependencies {
    implementation ("com.thunderhead.android:one-sdk:2.20.0-alpha") {
      exclude group: retrofit1Package
    }
}
```
### <a name="retro19"></a>Retrofit 1.9 support
The ONE SDK for Android also supports apps that use Retrofit 1.9. In order to use this, update your app level build.gradle file to contain the following dependencies:
``` java 
dependencies {
    implementation ("com.thunderhead.android:one-sdk:2.20.0-alpha") {
      exclude group: retrofit2Package
    }
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

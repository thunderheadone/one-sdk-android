![Thunderhead ONE SDK](https://www.thunderhead.com/uploads/2015/07/Thunderhead_LogoIcon_Aubergine.png "Thunderhead ONE")

The ONE SDK for Android supports Android 4.1 (API 16) and above.

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
    implementation 'com.android.support:support-v4:27.0.2'
    implementation 'com.android.support:design:27.0.2'
    implementation 'com.android.support:recyclerview-v7:27.0.2'
    implementation 'com.android.support:customtabs:27.0.2'

    implementation 'oauth.signpost:signpost-jetty6:1.2.1.2'

    implementation 'com.squareup.okhttp3:okhttp:3.7.0'
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
    implementation project(path: ':Thunderhead_android_release_2.19.0')
    implementation fileTree(include: ['*.jar'], dir: 'libs')

    implementation 'com.android.support:support-v4:27.0.2'
    implementation 'com.android.support:design:27.0.2'
    implementation 'com.android.support:recyclerview-v7:27.0.2'
    implementation 'com.android.support:customtabs:27.0.2'

    implementation 'oauth.signpost:signpost-jetty6:1.2.1.2'

    implementation 'com.squareup.okhttp3:okhttp:3.7.0'
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

### Opt an end-user out of tracking
To opt an end-user out of tracking when the end-user does not give permission to be tracked in the client app, call the opt out method as shown below:

```java
One one = One.getInstance(getApplicationContext());
one.optOut(true);
```
*Note:* 
- When opted out, tracking will stop and local data will be cleared. 
- At any point you can opt a user back in by passing `false` into the same method. 
- For instructions on how completly remove a user's data from ONE - see our [one-api documentation](https://thunderheadone.github.io/one-api/#operation/delete).

### Exclude an Interaction
You can exclude a specific view from being automatically recognized as an Interaction by using the `excludeInteractionView` method.

In your `onCreate` or `onCreateView`, call `excludeInteractionView` and pass the view you would like to exclude from being automatically tracked like shown in the example below:

```java
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.fragment_layout_2, null);
    One.getInstance(getActivity()).excludeInteractionView(rootView);
    return rootView;
}
```

### Disable automatic Interaction detection
You can disable automatic Interaction detection by calling the method `disableAutomaticInteractionDetection` and passing `true` as a parameter, as shown below:

```java
One one = One.getInstance(getApplicationContext());
one.disableAutomaticInteractionDetection(true);
```

By disabling automatic Interaction detection, the SDK won’t automatically send Interaction requests. It becomes your responsibility to send them when needed by using the methods provided in the sections below.

You can set this back to false at any point to restart automatic Interaction detection.

### Send an Interaction request programmatically 
You can send an Interaction request programmatically by calling the `sendInteraction` method and passing an Interaction path as a parameter as shown below:
```java
One one = One.getInstance(getApplicationContext());
one.sendInteraction("/interactionPath");
```
*Note:* 
- This will send a POST request to ONE. Only the tid from the response will be used by the SDK - all other response objects will be ignored.
- When sending Interaction requests programmatically please ensure the Interaction starts with a “/” and only contains letters, numbers and/or dashes.

### Send an Interaction request programmatically 
You can send an Interaction request programmatically and retrieve its response by calling the `sendInteraction` method with a callback. You need to pass an Interaction path and a callback to the method as shown below:
```java
One one = One.getInstance(getApplicationContext());
one.sendInteraction("/interactionName", new GetCallback<ResponseObject>() {  public void done(ResponseObject response, ThunderheadException e) {
    if (e == null) {
      // Success!
      one.processResponse(response);
    } else {
      // Failure!
    }
  }
});
```
The response can be passed to `processResponse` method as shown above. This method returns the response to the SDK to process, attaching any capture, track or optimize instructions to the interaction.

*Note:* 
- This will send a `POST` request to ONE.
- When sending Interaction requests programmatically please ensure the Interaction starts with a `/` and only contains letters, numbers and/or dashes.

### Retrieve a response for an automatically triggered Interaction request 
You can retrieve a response for an automatically triggered interaction request by registering for an interaction callback as shown below:
```java
One one = One.getInstance(getApplicationContext());
one.registerInteractionCallback("/interactionName", new InteractionCallback() {  
 @Override
 public void onReceive(String interactionPath, BaseResponse response) {
    one.processResponse(response);
 }
});
```
The response can be passed to the `processResponse` method as shown above. By calling this method the response is returned to the SDK to process, attaching any capture, track or optimize instructions to the interaction.

*Note:* 
- If you register to retrieve a response for an automatically triggered Interaction, you are responsible to unregister from this callback. You are advised to do this as soon as you no longer need this callback or under your activity or fragment’s `onStop` method.
    
    ```java
    protected void onStop() {
        super.onStop();
        one.unregisterInteractionCallback("/interactionName");
    }
    });
    ```
### Explicitly define a View as an Interaction
You can explicitly define a view as an Interaction by calling `setInteractionView` method and passing a view and desired Interaction path to it as shown below:
```java
One.getInstance(this).setInteractionView(customView, "/interactionPath");
```
This could be useful in the following cases:
1. If an activity with the same layout implements generic functionality and used to represent various Interactions within the same application. For example it could be a list view, which is being reused across the application to display branch locations in one use case and cash point locations in a second use case.
```java
public class LocationsList extends ListActivity implements GISDataPresenter{
  @Override
  public void onCreate(Bundle savedInstanceState) {
 	  super.onCreate(savedInstanceState);
	  …
     	if (presenterType == CASH_POINT_LOCATION) {
          	One.getInstance(this).setInteractionView(getListView(), "/cashPointList");
 		} else {
             One.getInstance(this).setInteractionView(getListView(), "/branchList");
            }
 	   …
  }
}
```
2. If a fragment implements generic functionality and could represent various Interactions. For instance in one case it could show a screen containing laptops in another case it could show cameras category.
```java 
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
View view = inflater.inflate(R.layout.products_tiles_view, container, false);
  if (category == Product.Category.LAPTOP) {
      One.getInstance(getActivity()).setInteractionView(view, "/LaptopsList");
  } else if (category == Product.Category.CAMERA) {
      One.getInstance(getActivity()).setInteractionView(view, "/CamerasList");
  }
  …
  return view;
}
```
3. If an Interaction is represented by a custom view.
```java
private void showVariants() {
    if (variantsView == null) {
        inflater.inflate(R.layout.variants_slide, mainPaneView, false);
        One.getInstance(this).setInteractionView(variantsView, "/Variants");
    }
    …
}
```
### Send Properties to ONE
Properties in the form of key/value pair strings can be sent to ONE using the SDK's public methods. Create a HashMap containing key/value pair strings, and call the appropriate properties public method, as follows:
```java
HashMap<String, String> propertiesMap = new HashMap<>();
propertiesMap.put("key1", "value1");
propertiesMap.put("key2", "value2");
```

#### Send properties to a base Touchpoint
To send properties to a base Touchpoint, call the following public method and pass in your `HashMap`:
```java
One one = One.getInstance(getApplicationContext());
one.sendBaseTouchpointProperties(propertiesMap);
```
*Note:* 
- This sends a `PUT` request to ONE. 
- Properties sent to a base touchpoint will be captured under a base (`/`) or wildcard (`/*`) Interaction in ONE. The capture point api name in ONE would have to match your key name sent above. 

#### Send Properties to an Interaction
To send properties to a specific Interaction, call the following public method, passing the Interaction path as a string together with your HashMap containing the said properties:

```java
One one = One.getInstance(getApplicationContext());
one.sendProperties("/interactionPath", propertiesMap);
```
*Note:*
- This sends a `PUT` request to ONE. 
- When sending Interaction requests programmatically please ensure the Interaction starts with a `/` and only contains letters, numbers and/or dashes.

#### Send an Interaction request with properties
You can send an Interaction request with properties by calling the `sendInteraction` method, and passing an Interaction path as a parameter and a `HashMap` containing the said properties, as shown below:
```java
One one = One.getInstance(getApplicationContext());
one.sendInteraction("/interactionPath", propertiesMap);
```
*Note:* 
- This sends a POST request to ONE.
- When sending Interaction requests programmatically, please ensure the Interaction starts with a `/` and only contains letters, numbers and/or dashes.

#### Send an Interaction Request with properties and a callback
You can send an Interaction request with properties and retrieve its response by calling corresponding `sendInteraction` method with a callback. You need to pass an Interaction path, a properties map and a callback to the method as shown below:
```java
One one = One.getInstance(getApplicationContext());
one.sendInteraction("/interactionName", propertiesMap, new GetCallback<ResponseObject>() {
  public void done(ResponseObject response, ThunderheadException e) {
    if (e == null) {
      // Success!
      one.processResponse(response);
    } else {
      // Failure!
    }
  }
});
```
The response can be passed to the `processResponse` method as shown above. This method returns the response to the SDK to process, attaching any capture, track or optimize instructions to the interaction.

*Note:* 
- This will send a `POST` request to ONE. 
- When sending Interaction requests programmatically, please ensure the Interaction starts with a `/` and only contains letters, numbers and/or dashes.

#### Send a response code
To send a response code, call `sendResponseCode` by passing the response code and the corresponding interaction path as parameters as shown below:
```java
One one = One.getInstance(getApplicationContext());
one.sendResponseCode("yourCode", "/interactionPath");
```

*Note:* 
- This method should be used when displaying optimziations programmatically and you need to capture the user's response.
- This will send a `PUT` request to ONE.
- When sending Interaction requests programmatically, please ensure the Interaction starts with a `/` and only contains letters, numbers and/or dashes.


## Further integration details 
### Retrofit 1.9 support
The ONE SDK for Android also supports apps that use Retrofit 1.9. In order to use this, update your app level build.gradle file to contain the following dependencies:
``` java 
dependencies {
    implementation 'com.android.support:support-v4:27.0.2'
    implementation 'com.android.support:design:27.0.2'
    implementation 'com.android.support:recyclerview-v7:27.0.2'
    implementation 'com.android.support:customtabs:27.0.2'

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

![Thunderhead SDK](https://www.thunderhead.com/uploads/2015/07/Thunderhead_LogoIcon_Aubergine.png "Thunderhead")

The Thunderhead SDK for Android supports Android 4.1 (API 16) and above.

## Installation

### Manual installation
1. Open your existing Android application in Android Studio.
2. Include the Thunderhead SDK as a dependency into your project:

+ Navigate to your **app-level** build.gradle file.
+ Add the following, under the dependencies section:
	+ For **Thunderhead ONE** integrations:
	
	```gradle
	dependencies {     
	  implementation ("com.thunderhead.android:one-sdk:2.21.1") {
	      exclude group: 'com.squareup.retrofit'
	  }
	}
	```
	
	+ For **Salesforce Interaction Studio** integrations:
	
	```gradle
	dependencies {     
	  implementation ("com.thunderhead.android:is-sdk:2.21.1") {
	      exclude group: 'com.squareup.retrofit'
	  }
	}
	```
	
+ **Note:** See [Retrofit 1.9 Support](#retro19) for applications that implement Retrofit 1.9
3. Add the Thunderhead SDK configuration within the same **app-level** `build.gradle` file. 
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
	+ For **Thunderhead ONE** integrations:
		``` gradle 
		apply plugin: 'com.thunderhead.android.aspectj-ext'
		aspectj {
		    includeAspectsFromJar 'one_sdk'
		    ajcArgs << '-Xlint:ignore' 
		}
		```
	+ For **Salesforce Interaction Studio** integrations:
		``` gradle 
		apply plugin: 'com.thunderhead.android.aspectj-ext'
		aspectj {
		    includeAspectsFromJar 'is_sdk'
		    ajcArgs << '-Xlint:ignore' 
		}
		```
4. Update your `build.gradle` to add codeless identity transfer support.
+ Navigate to the **top-level** `build.gradle` file and add a maven repository url and class path dependencies as shown below:
``` gradle 
buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'
        classpath 'com.thunderhead.android:android-gradle-plugin-aspectj:4.0.0'
    }
}
```
####  `build.gradle` examples
Example of the **top-level** `build.gradle` file after integration:
``` gradle
buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'
        classpath 'com.thunderhead.android:android-gradle-plugin-aspectj:4.0.0'
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
apply plugin: 'com.thunderhead.android.aspectj-ext'


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
    includeAspectsFromJar 'one_sdk'
    ajcArgs << '-Xlint:ignore'
}

dependencies {
    implementation (group: 'com.thunderhead.android', name: 'one-sdk', version: '2.21.1') {
      exclude group: 'com.squareup.retrofit'
    }
    implementation fileTree(include: ['*.jar'], dir: 'libs')    
}
repositories {
    maven {
       url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
    }
}

```

## Use the Codeless Thunderhead SDK for Android
Enable your app to automatically recognize **Interactions** by executing the following steps.

### The Thunderhead Application Manifest File Permissions:
Included in the Thunderhead SDK's AndroidManifest.xml are the following permissions which will be merged with your applications AndroidManifest.xml:
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
To start tracking, capturing, and receiving optimizations with the Thunderhead SDK in User mode, you must first initialize it with your Thunderhead API parameters. You can find your Thunderhead API parameters on the Thunderhead ONE website or in Salesforce Interaction Studio.

With your parameters ready at hand, add the following lines of code under the Application’s subclass onCreate() method. You must ensure the initialization method is added after super.onCreate() is called.

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

**You have now successfully integrated the codeless Thunderhead SDK for Android.**

## Additional features
Follow any of the steps below to access further functions of the SDK.

### Opt an end-user out of tracking
To opt an end-user out of tracking when the end-user does not give permission to be tracked in the client app, call the opt out method as shown below:

```java
One one = One.getInstance(getApplicationContext());
one.optOut(true);
```
*Note:* 
- When opted out, tracking will stop and locally queued data will be removed.
- At any point you can opt a user back in by passing `false` into the same method. 
- For instructions on how completely remove a user's data from Thunderhead ONE or Salesforce Interaction Studio - see our [api documentation](https://thunderheadone.github.io/one-api/#operation/delete).

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
- This will send a POST request to Thunderhead ONE or Salesforce Interaction Studio. Only the tid from the response will be used by the SDK - all other response objects will be ignored.
- When sending Interaction requests programmatically please ensure the Interaction starts with a `/` and only contains letters, numbers and/or dashes.

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
- This will send a `POST` request to Thunderhead ONE or Salesforce Interaction Studio.
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
### Send Properties to Thunderhead ONE or Salesforce Interaction Studio
Properties in the form of key/value pair strings can be sent to Thunderhead ONE or Salesforce Interaction Studio using the SDK's public methods. Create a HashMap containing key/value pair strings, and call the appropriate properties public method, as follows:
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
- This sends a `PUT` request to Thunderhead ONE or Salesforce Interaction Studio.
- Properties sent to a base touchpoint will be captured under a base (`/`) or wildcard (`/*`) Interaction in Thunderhead ONE or Salesforce Interaction Studio. The capture point api name in Thunderhead ONE, or Salesforce Interaction Studio, would have to match your key name sent above.

#### Send Properties to an Interaction
To send properties to a specific Interaction, call the following public method, passing the Interaction path as a string together with your HashMap containing the said properties:

```java
One one = One.getInstance(getApplicationContext());
one.sendProperties("/interactionPath", propertiesMap);
```
*Note:*
- This sends a `PUT` request to Thunderhead ONE or Salesforce Interaction Studio.
- When sending Interaction requests programmatically please ensure the Interaction starts with a `/` and only contains letters, numbers and/or dashes.

#### Send an Interaction request with properties
You can send an Interaction request with properties by calling the `sendInteraction` method, and passing an Interaction path as a parameter and a `HashMap` containing the said properties, as shown below:
```java
One one = One.getInstance(getApplicationContext());
one.sendInteraction("/interactionPath", propertiesMap);
```
*Note:* 
- This sends a POST request to Thunderhead ONE or Salesforce Interaction Studio.
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
- This will send a `POST` request to Thunderhead ONE or Salesforce Interaction Studio.
- When sending Interaction requests programmatically, please ensure the Interaction starts with a `/` and only contains letters, numbers and/or dashes.

#### Send a response code
To send a response code, call `sendResponseCode` by passing the response code and the corresponding interaction path as parameters as shown below:
```java
One one = One.getInstance(getApplicationContext());
one.sendResponseCode("yourCode", "/interactionPath");
```

*Note:* 
- This method should be used when displaying optimizations programmatically and you need to capture the user's response.
- This will send a `PUT` request to Thunderhead ONE or Salesforce Interaction Studio.
- When sending Interaction requests programmatically, please ensure the Interaction starts with a `/` and only contains letters, numbers and/or dashes.

### Identity sync
#### Identity sync with Thunderhead ONE or Salesforce Interaction Studio

To synchronise the Chrome Mobile identity set by the Thunderhead ONE Tag, or Salesforce Interaction Studio Tag, with the current app identity, call:

```java
One one = One.getInstance(getApplicationContext()); 
one.identitySync();
```
*Note:*
- This functionality only works if Chrome for Android is installed on the device and the device is connected to WiFi. 

#### Identity sync with Thunderhead ONE or Salesforce Interaction Studio and your web touchpoint

To synchronise the Chrome Mobile identity set by the Thunderhead ONE Tag, or Salesforce Interaction Studio Tag, with the current app identity and your web touchpoint, call:

```java
One one = One.getInstance(getApplicationContext()); 
one.identitySyncWithURL("https://your-web-touchpoint-url");
```

*Note:*
- This functionality only works if Chrome for Android is installed on the device and the device is connected to WiFi. 

### Ability to whitelist identity transfer links

The SDK will append a `one-tid` url parameter to all links opened from a mobile app. If you would like to limit this behaviour, for the SDK to only append a `one-tid` to a specific set of links, you can whitelist the links to which the SDK should append a `one-tid` by calling the method `whitelistIdentityTransferLinks` and passing your links as shown below:

```java
// This example shows how to whitelist links under specific domain names
// www.google.com and www.uber.com. For example,
// https://www.google.com, https://www.uber.com/en/,
// https://www.uber.com/en/ride/, etc.
One one = One.getInstance(getApplicationContext());
ArrayList<String> whitelist = new ArrayList<>();
whitelist.add("www.google.com");
whitelist.add("www.uber.com");
one.whitelistIdentityTransferLinks(whitelist);


// This example shows how to whitelist the main domain name
// wikipedia.org and any subdomain. For example,
// https://en.wikipedia.org, https://simple.wikipedia.org, etc.
One one = One.getInstance(getApplicationContext());
ArrayList<String> whitelist = new ArrayList<>();
// this will cover any google.com domains and subdomains
whitelist.add("*.wikipedia.org");
one.whitelistIdentityTransferLinks(whitelist);
```

*Note:* 
- When a link is whitelisted, a `one-tid` will be appended to the whitelisted link/s only.

### Ability to blacklist identity transfer links

The SDK will append a `one-tid` url parameter to all links opened from a mobile app. If you would like to limit this behaviour, for the SDK to only append a `one-tid` specific set of links, you can blacklist the links to which the SDK should not append a `one-tid` by calling the method `blacklistIdentityTransferLinks` and passing your links as shown below: 

```java
// This example shows how to blacklist links under specific domain names
// www.google.com and www.uber.com. For example,
// https://www.google.com, https://www.uber.com/en/,
// https://www.uber.com/en/ride/, etc.
One one = One.getInstance(getApplicationContext());
ArrayList<String> blacklist = new ArrayList<>();
blacklist.add("www.google.com");
blacklist.add("www.uber.com");
one.blacklistIdentityTransferLinks(blacklist);


// This example shows how to blacklist the main domain name
// wikipedia.org and any subdomain. For example,
// https://en.wikipedia.org, https://simple.wikipedia.org, etc.
One one = One.getInstance(getApplicationContext());
ArrayList<String> whitelist = new ArrayList<>();
// this will cover any google.com domains and subdomains
blacklist.add("*.wikipedia.org");
one.blacklistIdentityTransferLinks(blacklist);
```

*Note:* 
- If a link is blacklisted, a `one-tid` will be appended to all other links but the blacklisted link. 

###	Disable automatic identity transfer

If the Aspects functionality was enabled, the SDK adds a `one-tid` as a URL query parameter to web links opened in `WebView`, `CustomTabs` and external browsers(via `Intent`). To disable this functionality, call the `disableIdentityTransfer` method by passing `true` as shown below:  

```java
One one = One.getInstance(getApplicationContext());
one.disableIdentityTransfer(true);
```

*Note:* 
- This will also disable the ability to automatically pick up parameters from deep links that open the app, whilst also preventing the SDK from adding a ‘one-tid’ as a URL query parameter to web links opened from the app, resulting in the customer's identity not being transferred as they move across channels.

#### Send properties for a URL scheme

If you have disabled automatic identity transfer, you can still send all URL parameters received as part of a deep link by calling the `handleURL` SDK public method and passing the URL as a parameter into it, as shown below:

```java
One one = One.getInstance(getApplicationContext());
one.handleURL("myapp://MainActivity?customerKey=1");
```

*Note:* 
- This will send a `PUT` request to Thunderhead ONE or Salesforce Interaction Studio.

#### Append a ‘one-tid’ parameter to a `URL` to facilitate identity transfer 

If you have disabled automatic identity transfer, you can still add a `one-tid` parameter to a link opened from the app programmatically, by calling `getURLWithOneTid` as shown below:

```java
One one = One.getInstance(getApplicationContext());
URL urlWithOneTid = one.getURLWithOneTid(url);
```

Once you have the `urlWithOneTid`, pass this into the method which handles the opening of the `URL`.

#### Append a ‘one-tid’ parameter to a `Uri` to facilitate identity transfer 

If you have disabled automatic identity transfer, you can still add a `one-tid` parameter to a link opened from the app programmatically, by calling `getUriWithOneTid` as shown below:

```java
One one = One.getInstance(getApplicationContext());
Uro uriWithOneTid = one.getUriWithOneTid(url);
```

Once you have the `uriWithOneTid`, pass this into the method which handles the opening of the `Uri`.

### Disable automatic outbound link tracking

If the Aspects functionality was enabled, the SDK will automatically send an Interaction request to `/one-click` as a url is opened in a `WebView`, `CustomTab` or external browser to facilitate last click attribution.

To disable this functionality call the `disableAutomaticOutboundLinkTracking` method and pass `true`, as shown below:

```java
One one = One.getInstance(getApplicationContext());
one.disableAutomaticOutboundLinkTracking(true);
```

#### Programmatically trigger an outbound link tracking Interaction call

If you have disabled automatic outbound link tracking, you can still track a `URL` or `Uri`, by calling:

```java
// URL example
One one = One.getInstance(getApplicationContext());
try {
    one.sendInteractionForOutboundLink(new URL("https://www.yourfullurl.com/"));
} catch (MalformedURLException e) {
    e.printStackTrace();
}

// URI example
One one = One.getInstance(getApplicationContext());
try {
     one.sendInteractionForOutboundLink(Uri.parse("https://www.yourfullurl.com/"));
} catch (MalformedURLException e) {
    e.printStackTrace();
}

```
Pass the `URL` or `Uri`, which will send an Interaction request to `/one-click` using the same logic as available automatically.

*Note:* 
- This will send a `POST` request to Thunderhead ONE or Salesforce Interaction Studio.
- The `/one-click` Interaction request should be setup in Thunderhead ONE, or Salesforce Interaction Studio, to capture the appropriate attributes and activity.

### Enable push notifications

To receive push notifications from Thunderhead ONE or Salesforce Interaction Studio, ensure the correct dependencies have been added to the project and that you have followed the GCM or FCM instructions to be able to receive push notifications.

#### Add gradle build dependencies 
To enable the push notifications functionality, you need to make the following gradle build updates: 

1. Add the messaging class path to your top-level build.gradle file, located in the root project directory, as shown below:
    ```gradle
    buildscript {
        repositories {
            jcenter()
            mavenCentral()
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:3.0.1'
            classpath 'com.thunderhead.android:android-gradle-plugin-aspectj:4.0.0'
            // for cloud messaging support
            classpath 'com.google.gms:google-services:3.1.0'
        }
    }
    ```
2.	Add the FCM or GCM dependencies and apply the Google Messaging Service plugin to the app-level build.gradle file, as shown below:
- If you are using Firebase, add the following dependencies: 
    ```gradle
    dependencies {   
        ...
        // FCM dependencies    
        implementation "com.google.firebase:firebase-messaging:11.0.1"
        implementation "com.google.android.gms:play-services-base:11.0.1"
    }

    // place this at the bottom of your build.gradle
    apply plugin: 'com.google.gms.google-services'
    ```
- If you are using GCM, add the following dependencies:
    ```gradle
    dependencies {    
        ...   
        // GCM dependencies
        implementation ("com.google.android.gms:play-services-gcm:11.0.1")
    }

    // place this at the bottom of your build.gradle
    apply plugin: 'com.google.gms.google-services'
    ```
#### Enable codeless push notification support programmatically
- If you are using FCM, simply enable push notifications as shown below:
    ```java
    One one = One.getInstance(getApplicationContext());
    one.enablePushNotifications(true);
    ```

- If you are using GCM, enable push notifications and ensure you also pass the sender ID, as shown below:
    ```java
    One one = One.getInstance(getApplicationContext());
    one.enablePushNotifications(true, "your_sender_id");
    ```
*Note:* 
- When you enable codeless push notification support, the SDK will automatically get the push token and handle receiving of push notifications on behalf of your app.

### Get a push token

To get the push token codelessly retrieved by the SDK, call the `getPushToken` method as shown below:

```java  
One one = One.getInstance(getApplicationContext());
String pushToken = one.getPushToken();
// work with the push token
```
*Note:*
- This can be useful for testing and debugging, or to retrieve the token and pass it to another push notification provider. 

### Send a push token

To send a push token, call the `sendPushToken` method by passing a push token, as shown below:

```java
One one = One.getInstance(getApplicationContext());
one.sendPushToken("DUI03F379S1UUIDA6DADF8DFQPZ");

```

### Send a location object

To send a location object, call:

```java
One one = One.getInstance(getApplicationContext());
one.updateLocation(location);
```

passing the location object as a parameter to the `updateLocation` method. Use `LocationListener` callback method to call ``updateLocation`, as shown below:

```java
LocationListener locationListener = new LocationListener() {
    public void onLocationChanged(Location location) {
        One.getInstance(Activity.this).updateLocation(location);
    }

    public void onStatusChanged(String provider, int status, Bundle extras){
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
};
```

### Get Tid

To get the current `tid` used by the SDK, call:

```java
One one = One.getInstance(getApplicationContext()); 
one.getTid();
```

*Note:*
- This will return the `tid` assigned to the current user as a `String`.
- Retrieving the current `tid` can be useful if you want to monitor the current user in Thunderhead ONE, or Salesforce Interaction Studio, or if you need to pass the identity of the current user to another system which sends data to Thunderhead ONE or Salesforce Interaction Studio.

### Access Debug Information

The Thunderhead SDK for Android provides 4 distinct debugging levels, that can be enabled once the SDK has been initialized, as shown below:

1. `NONE_LEVEL` - if set, no messages will be displayed in the console.
	
	```java
    ThunderheadLogger.setLogLevel(ThunderheadLogger.NONE_LEVEL);
	```

2. `ALL_LEVEL` - if set, all log messages will be displayed in the console.
	
	```java
	ThunderheadLogger.setLogLevel(ThunderheadLogger.ALL_LEVEL);
	```

3. `WEB_SERVICE_LEVEL` - if set, only web service logs will be displayed in the console.

	```java
	ThunderheadLogger.setLogLevel(ThunderheadLogger.WEB_SERVICE_LEVEL);
	```

4. `FRAMEWORK_LEVEL` - if set, only framework logs will be displayed in the console.
	
	```java
	ThunderheadLogger.setLogLevel(ThunderheadLogger.FRAMEWORK_LEVEL);
	```

*Note:* 
- By default, the Thunderhead SDK for Android does not display any debug log messages. However, exception messages are printed in the console, when these occur.

### Identify the framework version

You can find out the current version of the framework by calling:

```java
One one = One.getInstance(getApplicationContext());
one.frameworkVersion();
```

### Clear the user profile

You can programmatically erase the user profile data by calling:

```java
One one = One.getInstance(getApplicationContext());
one.clearUserProfile();
```
*Note:* 
- This method removes `tid` from local storage only.
- For instructions on how completely remove a user's data from Thunderhead ONE or Salesforce Interaction Studio - see our [api documentation](https://thunderheadone.github.io/one-api/#operation/delete).

## Further integration details 
### Retrofit 2.x support
The Thunderhead SDK for Android supports apps that use Retrofit 2.X. In order to use this, update your app level build.gradle file to contain the following dependencies:
+ For Thunderhead ONE:

``` java 
dependencies {
    implementation (group: 'com.thunderhead.android', name: 'one-sdk', version: '2.21.1') {
      exclude group: 'com.squareup.retrofit'
    }
}
```

+ For Salesforce Interaction Studio:

``` java 
dependencies {
    implementation (group: 'com.thunderhead.android', name: 'is-sdk', version: '2.21.0') {
      exclude group: 'com.squareup.retrofit'
    }
}
```

### <a name="retro19"></a>Retrofit 1.9 support
The Thunderhead SDK for Android also supports apps that use Retrofit 1.9. In order to use this, update your app level build.gradle file to contain the following dependencies:
+ For Thunderhead ONE:

``` java 
dependencies {
    implementation (group: 'com.thunderhead.android', name: 'one-sdk', version: '2.21.0') {
      exclude group: 'com.squareup.retrofit2'
    }
}
```

+ For Salesforce Interaction Studio:

``` java 
dependencies {
    implementation (group: 'com.thunderhead.android', name: 'is-sdk', version: '2.21.0') {
      exclude group: 'com.squareup.retrofit2'
    }
}
```

### How to disable the codeless identity transfer support
To remove the codeless identity transfer functionality for Android, you need to make the following updates:
1. Open the **top-level** `build.gradle` file and remove the following dependency reference.
```gradle 
classpath 'com.thunderhead.android:android-gradle-plugin-aspectj:4.0.0'
```
2. Open the **app-level** `build.gradle` file and remove the following references.
```gradle 
apply plugin: 'com.thunderhead.android.aspectj-ext'
aspectj {
    includeAspectsFromJar 'one_sdk'
    ajcArgs << '-Xlint:ignore' 
}
```

## Questions or need help

_The Thunderhead ONE team is available 24/7 to answer any questions you have. Just email onesupport@thunderhead.com or visit our docs page for more detailed installation and usage information._

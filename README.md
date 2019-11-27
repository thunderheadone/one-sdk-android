![Thunderhead SDK](https://www.thunderhead.com/uploads/2015/07/Thunderhead_LogoIcon_Aubergine.png "Thunderhead")

The Thunderhead SDK for Android supports Android 4.1+ (API 16) and Android Gradle Plugin 3.4.2+.

**For _migrating_ from version(s) <= 3.0.0 to version(s) 4.0.0+ of the Thunderhead SDK, please see the [plugin migration guide](ORCHESTRATION-PLUGIN-MIGRATION.md) 
for details on updating the required Gradle plugins.**

## Installation

### Manual installation
Requires Gradle 5.2.1+
1. Open your existing Android application in Android Studio.
2. Include the Thunderhead SDK as a dependency into your project:

+ Navigate to your **app-level** build.gradle file.
+ Add the following, under the dependencies section:
	+ For **Thunderhead ONE** integrations:
	
	```gradle
	dependencies {     
	  implementation "com.thunderhead.android:one-sdk:4.2.6"
	}
	```
	
	+ For **Salesforce Interaction Studio** integrations:
	
	```gradle
	dependencies {     
	  implementation "com.thunderhead.android:is-sdk:4.2.6" 
	}
	```
	
3. Add the Thunderhead SDK configuration within the same **app-level** `build.gradle` file. 
+ Add `RenderScript` support under the `defaultConfig` section:
```gradle
defaultConfig {
   renderscriptTargetApi 22
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
+ Append the following configuration, for **Thunderhead ONE** and **Salesforce Interaction Studio** integrations: 
``` gradle 
apply plugin: 'com.thunderhead.android.orchestration-plugin'
```
		
4. Update your `build.gradle` to add codeless identity transfer support.
+ Navigate to the **top-level** `build.gradle` file and add a maven repository url and class path dependencies as shown below:
``` gradle 
buildscript {
    repositories {
        google()
        jcenter()
        maven {
            name 'Thunderhead'
            url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
        }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        classpath 'com.thunderhead.android:orchestration-plugin:1.0.0'
    }
}
```
####  `build.gradle` examples

#####  **Thunderhead ONE** `build.gradle` examples:

###### Example of the **top-level** `build.gradle` file after integration:
``` gradle
buildscript {
    repositories {
        google()
        jcenter()
        maven {
            name 'Thunderhead'
            url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
        }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        classpath 'com.thunderhead.android:orchestration-plugin:1.0.0'
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
```

###### Example of the **app-level** `build.gradle` file after integration:
``` gradle
apply plugin: 'com.android.application'
apply plugin: 'com.thunderhead.android.orchestration-plugin'

android {
    compileSdkVersion 28
    buildToolsVersion '28.0.0'

    defaultConfig {
        applicationId "com.thunderhead.android.demo"
        minSdkVersion 16
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"

        renderscriptTargetApi 22
        renderscriptSupportModeEnabled true
    }
}

dependencies {     
	implementation "com.thunderhead.android:one-sdk:4.2.6"
}

repositories {
    maven {
       url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
    }
}

```

#####  **Salesforce Interaction Studio** `build.gradle` examples:

###### Example of the **top-level** `build.gradle` file after integration:
``` gradle
buildscript {
    repositories {
        google()
        jcenter()
        maven {
            name 'Thunderhead'
            url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
        }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        classpath 'com.thunderhead.android:orchestration-plugin:1.0.0'
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
```

###### Example of the **app-level** `build.gradle` file after integration:
``` gradle
apply plugin: 'com.android.application'
apply plugin: 'com.thunderhead.android.orchestration-plugin'

android {
    compileSdkVersion 28
    buildToolsVersion '28.0.0'

    defaultConfig {
        applicationId "com.thunderhead.android.demo"
        minSdkVersion 16
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"

        renderscriptTargetApi 22
        renderscriptSupportModeEnabled true
    }
}

dependencies {     
	implementation "com.thunderhead.android:is-sdk:4.2.6" 
}

repositories {
    maven {
       url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
    }
}

```

For further documentation on the `orchestration-plugin` please see the [reference docs](ORCHESTRATION-PLUGIN-README.md).

## Use the Codeless Thunderhead SDK for Android
Enable your app to automatically recognize **Interactions** by executing the following steps.

* Developer note: Android Studio `Instant Run` is not supported at this time and must be disabled.

### The Thunderhead Application Manifest File Permissions:
Included in the Thunderhead SDK's AndroidManifest.xml are the following permissions which will be merged with your applications AndroidManifest.xml:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
```
*Note:* 
- The `SYSTEM_ALERT_WINDOW` permission is only needed for Admin mode builds. In your setup you can add this as a flavor specific permission to avoid having to show this as a permission change to your Play Store users.
- You can remove this permission in User mode builds by adding the following to your manifest: 
    ```xml 
        <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" tools:node="remove" />
    ```

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
*Note:* 
- If you are running in Admin mode on Android 6.0+, you will need to enable the “draw over other apps” permission via your OS settings. 
- If you have added both User and Admin mode support under the same app build, please note that the app will need to be terminated and restarted when switching from one mode to the other.

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
// this will cover any wikipedia.org domains and subdomains
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

If the Orchestration Plugin was enabled, the SDK adds a `one-tid` as a URL query parameter to web links opened in `WebView`, `CustomTabs` and external browsers(via `Intent`). To disable this functionality, call the `disableIdentityTransfer` method by passing `true` as shown below:  

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
Uri uriWithOneTid = one.getUriWithOneTid(url);
```

Once you have the `uriWithOneTid`, pass this into the method which handles the opening of the `Uri`.

### Disable automatic outbound link tracking

If the Orchestration Plugin was enabled, the SDK will automatically send an Interaction request to `/one-click` as a url is opened in a `WebView`, `CustomTab` or external browser to facilitate last click attribution.

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
To receive push notifications from Thunderhead ONE or Salesforce Interaction Studio, Firebase Cloud Messaging (FCM) must be configured by following the FCM setup instructions. 
At minimum the app must be configured in Firebase and the `google-services.json` needs to be in the root of the app project.

#### Minimum Gradle Configuration 
To use the codeless push notifications functionality without using FCM directly, you need to at least have the `google-services` plugin applied to your app build.gradle: 

1. Add the Google Services Plugin to your classpath in the top-level build.gradle file, located in the root project directory, as shown below:
    ```gradle
    buildscript {
        repositories {
            google()
            jcenter()
            mavenCentral()
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:3.4.2'
            // for cloud messaging support
            classpath 'com.google.gms:google-services:4.2.0'
        }
    }
    ```
2.	Apply the Google Messaging Service plugin to the app-level build.gradle file, as shown below:

    ```gradle
    // place this at the bottom of your app build.gradle
    apply plugin: 'com.google.gms.google-services'
    ```
    
    - The `Warning: The app gradle file must have a dependency on com.google.firebase:firebase-core for Firebase services to work as intended.` 
    can safely be ignored as this is not required for Push Notification Support.
    
#### Enable codeless push notification support programmatically
- For Firebase Cloud Messaging simply enable push notifications as shown below:
    ```java
    One one = One.getInstance(getApplicationContext());
    one.enablePushNotifications(true);
    ```
*Note:* 
- When you enable codeless push notification support, the SDK will automatically get the push token and handle receiving of push notifications on behalf of your app.

##### Set a non adaptive fallback.

Android (O)reo, Api 26, shipped with a platform bug relating to Adaptive Icons and Notifications. The bug can be seen [here](https://issuetracker.google.com/issues/68716460). 
The issue was resolved in Api 27 however it was not back ported to the original Oreo Api 26 platform.  

The Thunderhead SDK will optimize your user's App experience by sending Push Notifications with _your_ application's icon when appropriate. In order to avoid the infinite crash
loop that the above Android bug causes, the Thunderhead SDK will not show the message if a fallback *NON ADAPTIVE* icon is not set at initialization time on Api 26 devices. 
Changing your application's icon to a non adaptive icon is not required and the fall back is **only required for Api 26**.

The Thunderhead SDK will warn you at init if the icon has not been set by logging the `14019` error. See [Troubleshooting Guide](https://github.com/thunderheadone/one-sdk-android/blob/master/TROUBLESHOOTING-GUIDE.md#14019-non-adaptive-icon-is-not-set-android-api-26-push-notifications-will-not-be-shown-if-this-is-not-set)

Here is an example of setting the fallback for Api 26 devices using the built in Android "Star On" non adaptive drawable.  *Important: The icon set must not be adaptive!*

```kotlin
One.getInstance(context)?.run {
    // set icon before init to avoid warning.
    messageConfig = MessageConfig(android.R.drawable.star_on)
    enablePushNotifications(true)
    init(siteKey, touchpoint, apiKey, sharedSecret, userId, mode, host)
}
```

```java
One one = One.getInstance(getApplicationContext());
one.setMessageConfig(
    new MessageConfig(android.R.drawable.star_on)
);
one.enablePushNotifications(true);
one.init(siteKey, touchpoint, apiKey, sharedSecret, userId, mode, host);
```


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

1. `NONE` - if set, no messages will be displayed in the console.
	
	```java
	// this is an instance of Android Context.
	One.getInstance(this).setLogLevel(OneLogLevel.NONE);
	```

2. `ALL` - if set, all log messages will be displayed in the console.
	
	```java
	// this is an instance of Android Context.
	One.getInstance(this).setLogLevel(OneLogLevel.ALL);
	```

3. `WEB_SERVICE` - if set, only web service logs will be displayed in the console.

	```java
	// this is an instance of Android Context.
	One.getInstance(this).setLogLevel(OneLogLevel.WEB_SERVICE);
	```

4. `FRAMEWORK` - if set, only framework logs will be displayed in the console.
	
	```java
	// this is an instance of Android Context.
	One.getInstance(this).setLogLevel(OneLogLevel.FRAMEWORK);
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

### How to disable the codeless identity transfer support
To completely remove the codeless identity transfer functionality for Android, you need to make the following updates:
1. Open the **top-level** `build.gradle` file and remove the following dependency reference.
```gradle 
classpath 'com.thunderhead.android:orchestration-plugin:1.0.0'
```
2. Open the **app-level** `build.gradle` file and remove the following references.
```gradle 
apply plugin: 'com.thunderhead.android.orchestration-plugin'
```

## Troubleshooting Guide
[Troubleshooting Guide](TROUBLESHOOTING-GUIDE.md)

## Questions or need help

### Salesforce Interaction Studio Support
_For Salesforce Marketing Cloud Interaction Studio questions, please submit a support ticket via https://help.salesforce.com/home_

### Thunderhead ONE Support
_The Thunderhead team is available 24/7 to answer any questions you have. Just email onesupport@thunderhead.com or visit our docs page for more detailed installation and usage information._

![Thunderhead SDK](https://i.imgur.com/gfizURy.png "Thunderhead")

The Thunderhead SDK for Android supports Android 5.0+ (API 21) and Android Gradle Plugin 3.6.x.

**To  _migrate_ from version(s) <= 3.0.0 to version(s) 4.0.0+ of the Thunderhead SDK, please see the [plugin migration guide](https://github.com/thunderheadone/one-android-orchestration-plugin/blob/master/MIGRATION.md)
for details on updating the required Gradle plugins.**

**To _migrate_ from version(s) < 5.0.0 of the Thunderhead SDK to version(s) 5.0.0+, please see the [Java 8 migration guide](JAVA8-MIGRATION-GUIDE.md) for details
on updating your app to be Java 8 compatible in order to use the Thunderhead SDK.**

**To _migrate_ from version(s) < 6.0.0 of the Thunderhead SDK to version(s) 6.0.0+, please see the [Version 6 migration guide](MIGRATION-VERSION-6.md) for details
on updating your existing SDK configuration.**

## Table of Contents

* [Installation](#installation)
    * [Manual installation](#manual-installation)
* [Use the codeless Thunderhead SDK for Android](#use-the-codeless-thunderhead-sdk-for-android)
     * [Thunderhead Application Manifest file permissions](#thunderhead-application-manifest-file-permissions)
     * [Configure and reconfigure the SDK](#configure-and-reconfigure-the-sdk)
        * [SDK initialization not required](#sdk-initialization-not-required)
        * [Set up the SDK in User mode](#set-up-the-sdk-in-user-mode)
        * [Set up the SDK in Admin mode](#set-up-the-sdk-in-admin-mode)
* [Considerations](#considerations)
     * [Additional configuration required for apps configured with push messaging](#additional-configuration-required-for-apps-configured-with-push-messaging)
* [Additional features](#additional-features)
    * [Opt an end-user out of tracking](#opt-an-end-user-out-of-tracking)
    * [Exclude an Interaction](#exclude-an-interaction)
    * [Disable automatic Interaction detection](#disable-automatic-interaction-detection)
    * [Programmatic Interactions and Properties API](#programmatic-interactions-and-properties-api)
        * [Send Interactions](#send-interactions)
            * [Send an Interaction request](#send-an-interaction-request)
            * [Send an Interaction request and retrieve the response](#send-an-interaction-request-and-retrieve-the-response)
        * [Send Properties](#send-properties)
            * [Send an Interaction request with Properties](#send-an-interaction-request-with-properties)
            * [Send Properties to a base Touchpoint](#send-properties-to-a-base-touchpoint)
        * [Send a response code](#send-a-response-code)
    * [Retrieve a response for an automatically triggered Interaction request](#retrieve-a-response-for-an-automatically-triggered-interaction-request)
    * [Assign an Interaction to a View](#assign-an-interaction-to-a-view)
    * [Ability to whitelist identity transfer links](#ability-to-whitelist-identity-transfer-links)
    * [Ability to blacklist identity transfer links](#ability-to-blacklist-identity-transfer-links)
    * [Disable automatic identity transfer](#disable-automatic-identity-transfer)
        * [Send deep link properties programmatically](#send-deep-link-properties-programmatically)
        * [Create a `URL` with a `one-tid` parameter to facilitate identity transfer](#create-a-url-with-a-one-tid-parameter-to-facilitate-identity-transfer)
        * [Create an `android.net.Uri` or `java.net.URI` with a `one-tid` parameter to facilitate identity transfer](#create-an-androidneturi-or-javaneturi-with-a-one-tid-parameter-to-facilitate-identity-transfer)
    * [Disable automatic outbound link tracking](#disable-automatic-outbound-link-tracking)
        * [Programmatically trigger an outbound link tracking Interaction call](#programmatically-trigger-an-outbound-link-tracking-interaction-call)
    * [Enable push notifications](#enable-push-notifications)
        * [Minimum Gradle configuration](#minimum-gradle-configuration)
        * [Enable codeless push notification support programmatically](#enable-codeless-push-notification-support-programmatically)
           * [Configure push notifications with multiple push message SDKs](#configure-push-notifications-with-multiple-push-message-sdks)
           * [Set a non adaptive fallback](#set-a-non-adaptive-fallback)
    * [Get a push token](#get-a-push-token)
    * [Send a push token](#send-a-push-token)
     * [Send a location object](#send-a-location-object)
     * [Get Tid](#get-tid)
     * [Access debug information](#access-debug-information)
     * [Identify the SDK version](#identify-the-sdk-version)
     * [Clear the user profile](#clear-the-user-profile)
* [Further integration details](#further-integration-details)
    * [How to disable the codeless identity transfer support](#how-to-disable-the-codeless-identity-transfer-support)
* [Troubleshooting guide](#troubleshooting-guide)
* [Questions or need help](#questions-or-need-help)
    * [Salesforce Interaction Studio support](#salesforce-interaction-studio-support)
    * [Thunderhead ONE support](#thunderhead-one-support)

## Installation

### Manual installation

Requires Gradle 5.6.4+

1. Open your existing Android application in Android Studio.
2. Include the Thunderhead SDK as a dependency in your project:
+ Navigate to your **app-level** build.gradle.
+ Add the following, under the dependencies section:
    + For **Thunderhead ONE** integrations:

    ```gradle
    dependencies {     
      implementation "com.thunderhead.android:one-sdk:{SDK_VERSION}"
    }
    ```
    
    + For **Salesforce Interaction Studio** integrations:
    
    ```gradle
    dependencies {     
      implementation "com.thunderhead.android:is-sdk:{SDK_VERSION}"
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

+ Append the following configuration, for both **Thunderhead ONE** and **Salesforce Interaction Studio** integrations:

``` gradle 
apply plugin: 'com.thunderhead.android.orchestration-plugin'
```
  
4. Add Java 8 Support

+ Add the following, under the `android` section

```groovy
compileOptions {
    sourceCompatibility 1.8
    targetCompatibility 1.8
}
```

5. Update your `build.gradle` file to add codeless identity transfer support.

+ Navigate to the **top-level** `build.gradle` file and add both a maven repository url and class path dependencies as shown below:

``` gradle 
buildscript {
    repositories {
        google()
        jcenter()
        maven {
            name 'ThunderheadSpringMilestone'
            url = 'https://repo.spring.io/milestone'
        }
        maven {
            name 'Thunderhead'
            url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
        }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        classpath 'com.thunderhead.android:orchestration-plugin:2.0.0'
    }
}
```

####  `build.gradle` examples

#####  **Thunderhead ONE** `build.gradle` examples

###### Example of the **top-level** `build.gradle` file after integration

``` gradle
buildscript {
    repositories {
        google()
        jcenter()
        maven {
            name 'ThunderheadSpringMilestone'
            url = 'https://repo.spring.io/milestone'
        }
        maven {
            name 'Thunderhead'
            url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
        }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        classpath 'com.thunderhead.android:orchestration-plugin:2.0.0'
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

###### Example of the **app-level** `build.gradle` file after integration

``` gradle
apply plugin: 'com.android.application'
apply plugin: 'com.thunderhead.android.orchestration-plugin'

android {
    compileSdkVersion 28
    buildToolsVersion '28.0.0'
    compileOptions {
        sourceCompatibility 1.8
        targetCompatibility 1.8
    }

    defaultConfig {
        applicationId "com.thunderhead.android.demo"
        minSdkVersion 21
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"

        renderscriptTargetApi 22
        renderscriptSupportModeEnabled true
    }
}

dependencies {     
  implementation "com.thunderhead.android:one-sdk:{SDK_VERSION}"
}

repositories {
    maven {
       url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
    }
}

```

#####  **Salesforce Interaction Studio** `build.gradle` examples

###### Example of the **top-level** `build.gradle` file after integration

``` gradle
buildscript {
    repositories {
        google()
        jcenter()
        maven {
            name 'ThunderheadSpringMilestone'
            url = 'https://repo.spring.io/milestone'
        }
        maven {
            name 'Thunderhead'
            url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
        }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        classpath 'com.thunderhead.android:orchestration-plugin:2.0.0'
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

###### Example of the **app-level** `build.gradle` file after integration

``` gradle
apply plugin: 'com.android.application'
apply plugin: 'com.thunderhead.android.orchestration-plugin'

android {
    compileSdkVersion 28
    buildToolsVersion '28.0.0'
    compileOptions {
        sourceCompatibility 1.8
        targetCompatibility 1.8
    }
    defaultConfig {
        applicationId "com.thunderhead.android.demo"
        minSdkVersion 21
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"

        renderscriptTargetApi 22
        renderscriptSupportModeEnabled true
    }
}

dependencies {     
  implementation "com.thunderhead.android:is-sdk:{SDK_VERSION}"
}

repositories {
    maven {
       url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
    }
}

```

For further documentation on the `orchestration-plugin` please see the [Orchestration Plugin Readme](https://github.com/thunderheadone/one-android-orchestration-plugin/blob/master/README.md).

## Use the codeless Thunderhead SDK for Android

Enable your app to automatically recognize **Interactions** by executing the following steps.

*Developer note:* Android Studio `Instant Run` is not currently supported and must be disabled.

### Thunderhead Application Manifest file permissions

The following permissions are included in the Thunderhead SDK's `AndroidManifest.xml` and will be merged with your applications AndroidManifest.xml:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
```
*Note:* 
- The `SYSTEM_ALERT_WINDOW` permission is needed only for Admin mode builds. Add this as a flavor-specific permission  in your setup to avoid having to show it as a permission change to your Play Store users.
- You can remove this permission in User mode builds by adding the following to your manifest: 
```xml 
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" tools:node="remove" />
```

### Configure and reconfigure the SDK

You can configure and reconfigure the SDK as many times as necessary. 
* The SDK does not support partial, or piecemeal, configuration. You must provide all parameters, either all valid or invalid (`empty string` or `null`).  
* When configured with invalid parameters, the SDK is set in an *unconfigured* state.

See [here](https://github.com/thunderheadone/one-sdk-android/tree/master/examples/dynamic-configuration-example) for an example app that demonstrates dynamic configuration.

#### SDK initialization not required

The Thunderhead SDK is automatically initialized in an *unconfigured* state.
* When *unconfigured*, the SDK queues end-user data locally and uploads that data to the server once the SDK is configured with valid parameters.
* You can disable this functionality, at any time, by setting the `oneOptOutConfiguration` to `true`. See more about opt out [here](#opt-an-end-user-out-of-tracking).

#### Set up the SDK in User mode

To start capturing insights and configuring orchestrations in User mode, you must first configure the Thunderhead SDK with your Thunderhead API parameters. 
You can find your Thunderhead API parameters on the _API Credentials_ page in Thunderhead ONE or Salesforce Interaction Studio.

When you have your parameters, configure the SDK. We recommend adding the following lines of code for User Mode under the Application’s subclass `onCreate()` method, though this is not required. 
You must ensure the `oneConfigure` top-level Kotlin function or `setConfiguration` Java method is invoked after `super.onCreate()` is called.

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneConfigure
import com.thunderhead.OneModes;
// The rest of the imports

class YourApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        oneConfigure {
            siteKey = SITE_KEY
            apiKey = API_KEY
            sharedSecret = SHARED_SECRET
            userId = USER_ID
            host = URI(HOST)
            touchpoint = URI(TOUCHPOINT)
            mode = OneModes.USER_MODE
        }
    }
    
    companion object {
        const val SITE_KEY = "ONE-XXXXXXXXXX-1022"
        const val API_KEY = "f713d44a-8af0-4e79-ba7e-xxxxxxxxx"
        const val SHARED_SECRET = "bb8bacb2-ffc2-4c52-aaf4-xxx"
        const val USER_ID = "yourUsername@yourCompanyName"
        const val HOST = "https://xx.thunderhead.com"
        const val TOUCHPOINT = "myAppsNameURI"
    }
}
```

`Java`
```java 
import com.thunderhead.android.api.configuration.OneConfiguration;
import com.thunderhead.One;
import com.thunderhead.OneModes;
// The rest of the imports

public class YourApplication extends Application {
  
  private static final String siteKey = "ONE-XXXXXXXXXX-1022";
  private static final String apiKey = "f713d44a-8af0-4e79-ba7e-xxxxxxxxx";
  private static final String sharedSecret = "bb8bacb2-ffc2-4c52-aaf4-xxx";
  private static final String userId = "yourUsername@yourCompanyName";
  private static final String host = "https://xx.thunderhead.com";
  private static final String touchpointURI = "myAppsNameURI";
  
    @Override
    public void onCreate() {
      super.onCreate();
      final OneConfiguration oneConfiguration = new OneConfiguration.Builder()
        .siteKey(siteKey)
        .apiKey(apiKey)
        .sharedSecret(sharedSecret)
        .userId(userId)
        .host(URI.create(host))
        .touchpoint(URI.create(touchpointURI))
        .mode(OneModes.USER_MODE)
        .build();

        One.setConfiguration(oneConfiguration);
    }
}
```

*Note:* 
- Set up the User mode SDK build as part of the release build you plan to publish to the Play Store.
- Dynamic configuration of both Admin and User mode is supported.

#### Set up the SDK in Admin mode

To use the SDK in Admin mode, change the `OneModes` parameter to `ADMIN_MODE`.

*Note:* 
- If you are running in Admin mode on Android 6.0+, you must enable the “draw over other apps” permission through your OS settings. 
- Dynamic configuration of both Admin and User mode is supported.

**You have now successfully integrated the codeless Thunderhead SDK for Android.**

## Considerations

### Additional configuration required for apps configured with push messaging

When the Thunderhead SDK is the *only* push message provider in your application and you enable codeless push notification support, the SDK automatically gets the push token and handles the receiving of push notifications on behalf of your app, and therefore the below additional configuration instructions would not be needed.

When the Thunderhead SDK is integrated into an app configured with Firebase Cloud Messaging (FCM), or utilizes a third-party library using FCM, additional configuration is required to ensure push messaging continues to work for all SDKs using FCM.
*Note:*
- This is still required even if Thunderhead push notifications are not enabled.

You must forward the `onNewToken` and `onMessageReceived` callbacks to *all* SDK message APIs from the service that extends `FirebaseMessagingService`.

If using the Thunderhead SDK for push messaging, forward the callbacks as shown below:

```kotlin
// Call when a new FCM token is retrieved:
One.setMessagingToken(newToken);

// Call when a new message is received from Firebase:
One.processMessage(message);
```

An example of a service extending `FirebaseMessagingService` that calls the SDK messaging APIs:

`Kotlin`
```kotlin
class FirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        try {
            One.processMessage(remoteMessage)
            // Call other Push Message SDKs.
        } catch (e: Exception) {
            Log.e(TAG, e.message)
        }
    }

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        try {
            One.setMessagingToken(newToken)
            // Call other Push Message SDKs.
        } catch (e: Exception) {
            Log.e(TAG, e.message)
        }
    }
    companion object {
        private const val TAG = "FirebaseService"
    }
}
```

`Java`
```java
public final class FirebaseService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseService";

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        try {
            One.processMessage(remoteMessage);
            // Call other Push Message SDKs.
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onNewToken(final String newToken) {
        super.onNewToken(newToken);
        try {
            One.setMessagingToken(newToken);
            // Call other Push Message SDKs.
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
```

Do not forget to register the service in the manifest, if required:

```xml
<!-- The priority should be set to a high value in order to ensure this service receives the intent vs the other push provider SDKs -->
 <service
    android:name="com.example.FirebaseService">
        <intent-filter android:priority="100">
            <action android:name="com.google.firebase.MESSAGING_EVENT" />
        </intent-filter>
</service>
```

## Additional features

Follow any of the steps below to access further functions of the SDK.

### Opt an end-user out of tracking

To opt an end-user out of tracking, when the end-user does not give permission to be tracked in the client app, call the `oneConfigureOptOut` top-level Kotlin function or the `One.setOptOutConfiguration` Java method as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneConfigureOptOut

oneConfigureOptOut {
    optOut = true
}
```

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.optout.OneOptOutConfiguration;

final OneOptOutConfiguration optOutConfiguration = new OneOptOutConfiguration.Builder()
            .optOut(true)
            .build();

One.setOptOutConfiguration(optOutConfiguration);
```

*Note:* 
- When a user is opted out, tracking stops and locally queued data is removed.
- You can opt a user back in, at any point, by setting the `optOut` parameter to `false` using the same method. 
- For instructions on completely removing a user's data from Thunderhead ONE or Salesforce Interaction Studio, see our [API Documentation](https://thunderheadone.github.io/one-api/#operation/delete).

### Exclude an Interaction

Exclude a specific view from being automatically recognized as an Interaction, using the `excludeAutomaticInteraction` Kotlin extension function or `One.excludeAutomaticInteraction` Java method in an Activity's `onCreate` method or a Fragment's `onCreateView`, as shown below. 

`Kotlin`
```kotlin
import com.thunderhead.android.api.excludeAutomaticInteraction
// rest of imports

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    findViewById<LinearLayout>(R.id.root)
        .excludeAutomaticInteraction()
}
```

`Java`
```java
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.fragment_layout_2, null);
    final OneAutomaticInteractionExclusion rootViewExclusion = new OneAutomaticInteractionExclusion.Builder()
                        .view(rootView)
                        .build();
    One.excludeAutomaticInteraction(rootViewExclusion);
    return rootView;
}
```

### Disable automatic Interaction detection

You can disable automatic Interaction detection by calling the `oneConfigureCodelessInteractionTracking` Kotlin top-level function or the `One.setCodelessInteractionTrackingConfiguration` Java method with the appropriate configuration, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneConfigureCodelessInteractionTracking

oneConfigureCodelessInteractionTracking {
    disableCodelessInteractionTracking = true
}
```

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.codeless.OneCodelessInteractionTrackingConfiguration;

final OneCodelessInteractionTrackingConfiguration codelessInteractionTrackingConfiguration =
    new OneCodelessInteractionTrackingConfiguration.Builder()
        .disableCodelessInteractionTracking(true)
        .build();
One.setCodelessInteractionTrackingConfiguration(codelessInteractionTrackingConfiguration);
```

When automatic Interaction detection is disabled, the SDK does not automatically send Interaction requests. 
You must send Interaction requests manually, as and when needed, using the methods provided in the sections below.

Set this back to `false` at any point to restart automatic Interaction detection.

### Programmatic Interactions and Properties API

You can manually send Interaction Requests and Properties to ONE or Interaction Studio using 
the `oneSendInteraction` Kotlin top-level function or the `One.sendInteraction` Java method.

The `oneSendInteraction` Kotlin top-level function uses Kotlin [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html). The `One.sendInteraction` Java method follows a Call/Request/Response model similar to [Retrofit](https://square.github.io/retrofit/).

#### Send Interactions

##### Send an Interaction request

Send an Interaction request programmatically by calling the `oneSendInteraction` Kotlin top-level function in a Coroutine, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.oneSendInteraction
// rest of imports

scope.launch {
    oneSendInteraction {
        interactionPath = OneInteractionPath(URI("/interactionPath"))
    }
}
```

To capture errors, set the `throwErrors` parameter to `true` and wrap the method in a `try/catch` block, as shown below:

```kotlin
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.oneSendInteraction
import com.thunderhead.android.api.responsetypes.OneAPIError
import com.thunderhead.android.api.responsetypes.OneSDKError
// rest of imports

scope.launch {
    try {
        oneSendInteraction(throwErrors = true) {
            interactionPath = OneInteractionPath(URI("/interactionPath"))
        }
    } catch (error: OneSDKError) {
        Log.e(TAG, "SDK Error: ${error.errorMessage}")
    } catch (error: OneAPIError) {
        Log.e(TAG, "Api Error: ${error.errorMessage}")
    }
}
```

Send an Interaction request programmatically by calling the `One.sendInteraction` Java method and enqueue with a `null` callback, as shown below:

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.interactions.OneCall;
import com.thunderhead.android.api.interactions.OneInteractionPath;
import com.thunderhead.android.api.interactions.OneRequest;
// rest of imports

final OneRequest sendInteractionRequest = new OneRequest.Builder()
        .interactionPath(new OneInteractionPath(URI.create("/interactionPath")))
        .build();
final OneCall sendInteractionCall = One.sendInteraction(sendInteractionRequest);
sendInteractionCall.enqueue(null);
```

*Note:* 
- Sends a `POST` request to Thunderhead ONE or Salesforce Interaction Studio. Only the `tid` from the response isused by the SDK; all other response objects are ignored.
- When sending Interaction requests programmatically please ensure the Interaction starts with a `/` and contains only letters, numbers, and/or dashes.

##### Send an Interaction request and retrieve the response

Send an Interaction request programmatically, access its response, and then process that response by calling the `oneSendInteraction` Kotlin top-level function in a Coroutine, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.oneSendInteraction
import com.thunderhead.android.api.process
// rest of imports

scope.launch {
    val response = oneSendInteraction {
        interactionPath = OneInteractionPath(URI("/interactionPath"))
    }
    response?.process()
}
```

To capture errors, set the `throwErrors` parameter to `true` and wrap the method in a `try/catch` block, as shown below:

```kotlin
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.oneSendInteraction
import com.thunderhead.android.api.process
import com.thunderhead.android.api.responsetypes.OneAPIError
import com.thunderhead.android.api.responsetypes.OneSDKError
// rest of imports

scope.launch {
    try {
        val response = oneSendInteraction(throwErrors = true) {
            interactionPath = OneInteractionPath(URI("/interactionPath"))
        }
        response?.process()
    } catch (error: OneSDKError) {
        Log.e(TAG, "SDK Error: ${error.errorMessage}")
    } catch (error: OneAPIError) {
        Log.e(TAG, "Api Error: ${error.errorMessage}")
    }
}
```

Send an Interaction request programmatically and process the response by calling the `One.sendInteraction` Java method and enqueue with a callback, as shown below:

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.interactions.OneCall;
import com.thunderhead.android.api.interactions.OneCallback;
import com.thunderhead.android.api.interactions.OneInteractionPath;
import com.thunderhead.android.api.interactions.OneRequest;
// rest of imports

final OneRequest sendInteractionRequest = new OneRequest.Builder()
        .interactionPath(new OneInteractionPath(URI.create("/interactionPath")))
        .build();

final OneCall sendInteractionCall = One.sendInteraction(sendInteractionRequest);

sendInteractionCall.enqueue(new OneCallback() {
    @Override
    public void onSuccess(@NotNull OneResponse response) {
        One.processResponse(response);
    }

    @Override
    public void onError(@NotNull OneSDKError error) {
        Log.e(TAG, "SDK Error: " +  error.getErrorMessage());
    }

    @Override
    public void onFailure(@NotNull OneAPIError error) {
        Log.e(TAG, "Api Error: " +  error.getErrorMessage());
    }
});
```

The response can be passed to the `One.processResponse` method, as shown above. This method returns the response to the SDK to process, attaching any activity capture, attribute capture, or optimize instructions to the interaction.

*Note:* 
- Sends a `POST` request to Thunderhead ONE or Salesforce Interaction Studio.
- When sending Interaction requests programmatically, please ensure the Interaction starts with a `/` and contains only letters, numbers, and/or dashes.

#### Send Properties

Properties in the form of key/value pair strings can be sent to Thunderhead ONE or Salesforce Interaction Studio using the SDK's public methods. 

##### Send an Interaction request with Properties

Send an Interaction request with Properties, programmatically, and ignore the response
by calling the `oneSendInteraction` Kotlin top-level function in a Coroutine, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.oneSendInteraction
// rest of imports

scope.launch {
    oneSendInteraction {
        interactionPath = OneInteractionPath(URI("/interactionPath"))
        properties = mapOf("keyA" to "valueA", "keyB" to "valueB")
    }
}
```

To capture errors, set the `throwErrors` parameter to `true` and wrap the method in a `try/catch` block, as shown below:

```kotlin
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.oneSendInteraction
import com.thunderhead.android.api.responsetypes.OneAPIError
import com.thunderhead.android.api.responsetypes.OneSDKError
// rest of imports

scope.launch {
    try {
        oneSendInteraction(throwErrors = true) {
            interactionPath = OneInteractionPath(URI("/interactionPath"))
            properties = mapOf("keyA" to "valueA", "keyB" to "valueB")
        }
    } catch (error: OneSDKError) {
        Log.e(TAG, "SDK Error: ${error.errorMessage}")
    } catch (error: OneAPIError) {
        Log.e(TAG, "Api Error: ${error.errorMessage}")
    }
}
```

Send an Interaction request with Properties, programmatically, and ignore the response
by calling the `One.sendInteraction` Java method and enqueue with a `null` callback, as shown below:

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.interactions.OneCall;
import com.thunderhead.android.api.interactions.OneInteractionPath;
import com.thunderhead.android.api.interactions.OneRequest;
// rest of imports

final Map<String, String> properties = new HashMap<>();
properties.put("keyA", "valueA");
properties.put("keyB", "valueB");

final OneRequest sendInteractionRequest = new OneRequest.Builder()
        .interactionPath(new OneInteractionPath(URI.create("/interactionPath")))
        .properties(properties)
        .build();

final OneCall sendInteractionCall = One.sendInteraction(sendInteractionRequest);

sendInteractionCall.enqueue(null);
```

##### Send Properties to a base Touchpoint

Send Properties programmatically and ignore the response by calling the `oneSendProperties` Kotlin top-level function in a Coroutine, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.oneSendProperties
// rest of imports

scope.launch {
    oneSendProperties {
        properties = mapOf("keyA" to "valueA", "keyB" to "valueB")
    }
}
```

To capture errors, set the `throwErrors` parameter to `true` and wrap the method in a `try/catch` block, as shown below:

```kotlin
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.oneSendProperties
import com.thunderhead.android.api.responsetypes.OneAPIError
import com.thunderhead.android.api.responsetypes.OneSDKError
// rest of imports

scope.launch {
    try {
        oneSendProperties(throwErrors = true) {
            properties = mapOf("keyA" to "valueA", "keyB" to "valueB")
        }
    } catch (error: OneSDKError) {
        Log.e(TAG, "SDK Error: ${error.errorMessage}")
    } catch (error: OneAPIError) {
        Log.e(TAG, "Api Error: ${error.errorMessage}")
    }
}
```

Send Properties programmatically and ignore the response by calling the `One.sendProperties` Java method and enqueue with a null callback, as shown below:

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.interactions.OneCall;
import com.thunderhead.android.api.interactions.OneRequest;
// rest of imports

final Map<String, String> properties = new HashMap<>();
properties.put("keyA", "valueA");
properties.put("keyB", "valueB");

final OneRequest sendPropertiesRequest = new OneRequest.Builder()
        .properties(properties)
        .build();

final OneCall sendPropertiesCall = One.sendProperties(sendPropertiesRequest);

sendPropertiesCall.enqueue(null);
```

*Note:* 
- Sends a `PUT` request to Thunderhead ONE or Salesforce Interaction Studio.
- Properties sent to a base Touchpoint are captured under a base (`/`) or wildcard (`/*`) Interaction in Thunderhead ONE or Salesforce Interaction Studio. The Attribute Capture Point API name in Thunderhead ONE, or Salesforce Interaction Studio, must match the key name sent above.

#### Send a response code

Send a response code by calling the `oneSendResponseCode` Kotlin top-level function, with the response code and the corresponding Interaction path as parameters, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.oneSendResponseCode
// rest of imports

scope.launch {
    oneSendResponseCode {
        interactionPath = OneInteractionPath(URI("/interactionPath"))
        code = ResponseCode("code")
    }
}
```

To capture errors, set the `throwErrors` parameter to `true` and wrap the method in a `try/catch` block, as shown below:

```kotlin
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.oneSendResponseCode
import com.thunderhead.android.api.responsetypes.OneAPIError
import com.thunderhead.android.api.responsetypes.OneSDKError
// rest of imports

scope.launch {
    try {
        oneSendResponseCode(throwErrors = true) {
            interactionPath = OneInteractionPath(URI("/interactionPath"))
            code = ResponseCode("code")
        }
    } catch (error: OneSDKError) {
        Log.e(TAG, "SDK Error: ${error.errorMessage}")
    } catch (error: OneAPIError) {
        Log.e(TAG, "Api Error: ${error.errorMessage}")
    }
}
```

Send a response code, by calling the `One.sendResponseCode` Java method, with the response code
and the corresponding interaction path as parameters, as shown below:

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.interactions.OneResponseCodeRequest;
import com.thunderhead.android.api.interactions.OneInteractionPath;
import com.thunderhead.android.api.interactions.OneResponseCode;

final OneResponseCodeRequest responseCodeRequest = new OneResponseCodeRequest.Builder()
        .responseCode(new ResponseCode("code"))
        .interactionPath(new OneInteractionPath("/interactionPath"))
        .build()
One.sendResponseCode(responseCodeRequest).enqueue(null);
```

*Note:* 
- Use this method when you are displaying optimizations programmatically and need to capture the user's response.
- Sends a `PUT` request to Thunderhead ONE or Salesforce Interaction Studio.
- When sending Interaction requests programmatically, please ensure the Interaction starts with a `/` and contains only letters, numbers, and/or dashes.

### Retrieve a response for an automatically triggered Interaction request 

Retrieve a response for an automatically triggered Interaction request by setting an Interaction callback, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneSetAutomaticInteractionCallback
import com.thunderhead.android.api.interactions.OneInteractionPath
// rest of imports

oneSetAutomaticInteractionCallback(OneInteractionPath(URI("https://server.com"))) {
    onSuccess { response ->
        // perform custom action
        response.process()
    }

    onError { sdkError ->
        Log.d(TAG, "SdkError: ${sdkError.errorMessage}")
    }

    onFailure { apiError ->
        Log.d(TAG, "ApiError: ${apiError.errorMessage}")
    }
}
```

`Java`
```java
One.setAutomaticInteractionCallback(new OneInteractionPath(URI.create(TestConstants.test_triggered_interaction_1)), new OneCallback() {
    @Override
    public void onFailure(@NotNull OneAPIError error) {
        Log.e(TAG, "ApiError: " + error.getErrorMessage());
    }

    @Override
    public void onError(@NotNull OneSDKError error) {
        Log.e(TAG, "SdkError: " + error.getErrorMessage());
    }

    @Override
    public void onSuccess(@NotNull OneResponse response) {
        One.processResponse(response);
    }
});
```

The response can be passed to the `processResponse` method, as shown above. By calling this method the response is returned to the SDK to process, attaching any activity capture, attribute capture, or optimize instructions to the Interaction.

*Note:* 
- If you set a callback for an automatically triggered Interaction, you are advised to remove that callback as soon as it is no longer needed under your activity or fragment’s `onStop` method.

`Kotlin`
```kotlin   
import com.thunderhead.android.api.oneRemoveAutomaticInteractionCallback
import com.thunderhead.android.api.interactions.OneInteractionPath

protected fun onStop() {
    super.onStop()
    oneRemoveAutomaticInteractionCallback(OneInteractionPath(URI.create("/interaction")))
}
```

`Java`
```java
protected void onStop() {
    super.onStop();
    One.removeAutomaticInteractionCallback(new OneInteractionPath(URI.create(TestConstants.test_triggered_interaction_1)));
}
```
    
### Assign an Interaction to a View

Explicitly define a view as an Interaction by calling the `assignInteractionPath` Kotlin extension function
or the `One.assignInteractionPath` Java method with a valid desired Interaction path, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.assignInteractionPath
import com.thunderhead.android.api.interactions.OneInteractionPath

findViewById<LinearLayout>(R.id.linear_layout)
    .assignInteractionPath(OneInteractionPath(URI("/viewAsInteraction")))
```

`Java`
```java
LinearLayout linearLayout = findViewById<LinearLayout>(R.id.linear_layout);
final OneInteractionPathAssignment oneInteractionPathAssignment = new OneInteractionPathAssignment.Builder()
        .view(linearLayout)
        .interactionPath(new OneInteractionPath(URI.create("/viewAsInteraction")))
        .build();

One.assignInteractionPath(oneInteractionPathAssignment);
```

This can be useful in the following cases:

1. If an activity with the same layout implements generic functionality and is used to represent various Interactions within the same application. 
For example, you may have a list view, that is reused across the application to display branch locations in one use case and cash point locations in a second use case.

`Kotlin`
```kotlin
import com.thunderhead.android.api.assignInteractionPath
import com.thunderhead.android.api.interactions.OneInteractionPath
// rest of imports

class LocationsList : ListActivity(), GISDataPresenter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val interactionPath = if (presenterType == CASH_POINT_LOCATION) {
            "/cashPointList"
        } else {
           "/branchList" 
        }
        getListView().assignInteractionPath(OneInteractionPath(URI(interactionPath)))
    }
}
```

`Java`
```java
public class LocationsList extends ListActivity implements GISDataPresenter {
  @Override
  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final OneInteractionPathAssignment.Builder assignmentBuilder = 
            new OneInteractionPathAssignment.Builder()
                .view(getListView());
        if (presenterType == CASH_POINT_LOCATION) {
            assignmentBuilder
                .interactionPath(new OneInteractionPath(URI.create("/cashPointList")));
        } else {
            assignmentBuilder
                .interactionPath(new OneInteractionPath(URI.create("/branchList")));
        }
        One.assignInteractionPath(assignmentBuilder.build());
    }
}
```

2. If a fragment implements generic functionality and may represent various Interactions. 
For example, in one case it may show a screen containing laptops and, in another, a screen containing cameras.

`Java`
```java 
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.products_tiles_view, container, false);
    final OneInteractionPathAssignment.Builder assignmentBuilder = 
        new OneInteractionPathAssignment.Builder()
            .view(view);
    if (category == Product.Category.LAPTOP) {
        assignmentBuilder
            .interactionPath(new OneInteractionPath(URI.create("/laptopsList")));
    } else if (category == Product.Category.CAMERA) {
        assignmentBuilder
            .interactionPath(new OneInteractionPath(URI.create("/camerasList")));
    }
    One.assignInteractionPath(assignmentBuilder.build());
    return view;
}
```

3. If an Interaction is represented by a custom view.

`Kotlin`
```kotlin
import com.thunderhead.android.api.assignInteractionPath
import com.thunderhead.android.api.interactions.OneInteractionPath
// rest of imports

private fun showVariants() {
    if (varientsView == null) {
        variantsView = inflater.inflate(R.layout.variants_slide, mainPanelView, false)
        variantsView.assignInteractionPath(OneInteractionPath(URI("/variants")))
    }
}
```

`Java`
```java
private void showVariants() {
    if (variantsView == null) {
        variantsView = inflater.inflate(R.layout.variants_slide, mainPaneView, false);
        final OneInteractionPathAssignment assignment = 
            new OneInteractionPathAssignment.Builder()
                .view(variantsView)
                .interactionPath(new OneInteractionPath(URI.create("/variants")))
                .build();

        One.assignInteractionPath(assignment);
    }
}
```

### Ability to whitelist identity transfer links

The SDK appends a `one-tid` `URL` parameter to all links opened from a mobile app. To limit this behaviour, and allow the SDK to append a `one-tid` `URL` parameter only to a specific set of links, whitelist that set of links by calling the `java.net.URI.setIdentityTransferLinksWhiteList` Kotlin extension function or `whitelistIdentityTransferLinks` Java method, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.setIdentityTransferLinksWhiteList
// rest of imports

// This example shows how to whitelist links under specific domain names
// www.google.com and www.uber.com. For example,
// https://www.google.com, https://www.uber.com/en/,
// https://www.uber.com/en/ride/, etc.
val whiteList = setOf(URI("www.google.com"), URI("www.uber.com"))
    .setIdentityTransferLinksWhiteList()

// This example shows how to whitelist the main domain name
// wikipedia.org and any subdomain. For example,
// https://en.wikipedia.org, https://simple.wikipedia.org, etc.
val whiteList = setOf(URI("*.wikipedia.org"))
    .setIdentityTransferLinksWhiteList()

// This example show how to retrieve the whitelist currently in use
val whitelist = oneGetIdentityTransferLinksWhiteList()
```

`Java`
```java
// This example shows how to whitelist links under specific domain names
// www.google.com and www.uber.com. For example,
// https://www.google.com, https://www.uber.com/en/,
// https://www.uber.com/en/ride/, etc.
HashSet<URI> whitelist = new HashSet<>();
try {
    whitelist.add(new URI("www.google.com"));
    whitelist.add(new URI("www.uber.com"));
} catch (URISyntaxException e) {
    e.printStackTrace();
}
One.setIdentityTransferLinksWhiteList(whitelist);

// This example shows how to whitelist the main domain name
// wikipedia.org and any subdomain. For example,
// https://en.wikipedia.org, https://simple.wikipedia.org, etc.
HashSet<URI> whitelist = new HashSet<>();
try {
    whitelist.add("*.wikipedia.org");
} catch (URISyntaxException e) {
    e.printStackTrace();
}
One.setIdentityTransferLinksWhiteList(whitelist);

// This example show how to retrieve the whitelist currently in use
HashSet<URI> whitelist = One.getIdentityTransferLinksWhiteList()
```

*Note:* 
- When a link is whitelisted, a `one-tid` is appended only to the whitelisted links.
- Setting the whitelist to an `empty list` or `null` clears the existing whitelist.

### Ability to blacklist identity transfer links

The SDK appends a `one-tid` `URL` parameter to all links opened from a mobile app. To limit this behaviour, and allow the SDK to append a `one-tid` `URL` parameter only to a specific set of links, blacklist the links to which the SDK should not append a `one-tid` by calling the `java.net.URI.setIdentityTransferLinksBlackList` Kotlin extension function or `blacklistIdentityTransferLinks` Java method, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.setIdentityTransferLinksBlackList
// rest of imports

// This example shows how to blacklist links under specific domain names
// www.google.com and www.uber.com. For example,
// https://www.google.com, https://www.uber.com/en/,
// https://www.uber.com/en/ride/, etc.
val blackList = setOf(URI("www.google.com"), URI("www.uber.com"))
    .setIdentityTransferLinksBlackList()

// This example shows how to whitelist the main domain name
// wikipedia.org and any subdomain. For example,
// https://en.wikipedia.org, https://simple.wikipedia.org, etc.
val blackList = setOf(URI("*.wikipedia.org"))
    .setIdentityTransferLinksBlackList()

// This example show how to retrieve the whitelist currently in use
val blackList = oneGetIdentityTransferLinksBlackList()
```

`Java`
```java
// This example shows how to blacklist links under specific domain names
// www.google.com and www.uber.com. For example,
// https://www.google.com, https://www.uber.com/en/,
// https://www.uber.com/en/ride/, etc.
HashSet<URI> blacklist = new HashSet<>();
try {
    blacklist.add(new URI("www.google.com"));
    blacklist.add(new URI("www.uber.com"));
} catch (URISyntaxException e) {
    e.printStackTrace();
}
One.setIdentityTransferLinksBlackList(blacklist);

// This example shows how to blacklist the main domain name
// wikipedia.org and any subdomain. For example,
// https://en.wikipedia.org, https://simple.wikipedia.org, etc.
HashSet<URI> blacklist = new HashSet<>();
try {
    blacklist.add("*.wikipedia.org");
} catch (URISyntaxException e) {
    e.printStackTrace();
}
One.setIdentityTransferLinksBlackList(blacklist);

// This example show how to retrieve the blacklist currently in use
HashSet<URI> blacklist = One.getIdentityTransferLinksBlackList()
```

*Note:* 
- If a link is blacklisted, a `one-tid` is appended to all links other than the blacklisted link. 
- Setting the blacklist to an `empty list` or `null` clears the existing blacklist.

### Disable automatic identity transfer

If the Orchestration Plugin is enabled, the SDK adds a `one-tid` as a `URL` query parameter to web links opened in `WebView`, `CustomTabs` and external browsers (via `Intent`). 
To disable this functionality, call the `oneConfigureIdentityTransfer` Kotlin top-level function or the `One.setIdentityTransferConfiguration` Java method, as shown below:  

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneConfigureIdentityTransfer

oneConfigureIdentityTransfer {
    disableIdentityTransfer = true
}
```

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.identitytransfer.OneIdentityTransferConfiguration;

final OneIdentityTransferConfiguration identityTransferConfiguration =
    new OneIdentityTransferConfiguration.Builder()
        .disableIdentityTransfer(true)
        .build();
One.setIdentityTransferConfiguration(identityTransferConfiguration);
```

*Note:* 
- This also disables the ability to pick up parameters, automatically, from deep links that open the app and prevents the SDK from adding a `one-tid` as a `URL` query parameter to web links opened from the app, resulting in the customer's identity not being transferred as they move across channels.

#### Send deep link properties programmatically

If you have disabled automatic identity transfer, you can still send all `URL` parameters received as part of a deep link by calling the `java.net.URI.processDeepLink` or `android.net.Uri.processDeepLink` Kotlin extension function or the `One.processDeepLink` Java method, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.processDeepLink
// rest of imports

URI("myapp://MainActivity?customerKey=1").processDeepLink()
Uri.parse("myapp://MainActivity?customerKey=1").processDeepLink()
```

`Java`
```java
One.processDeepLink(URI.create("myapp://MainActivity?customerKey=1"));
```

*Note:* 
- Sends a `PUT` request to Thunderhead ONE or Salesforce Interaction Studio.

#### Create a `URL` with a `one-tid` parameter to facilitate identity transfer 

If you have disabled automatic identity transfer, you can still create a `URL` with a `one-tid` parameter to use in the app programmatically, by calling the `java.net.URL.createUrlWithTid()` Kotlin extension function or the `One.createUrlWithTid(URL)` Java method, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.createUrlWithTid
// rest of imports

val urlWithOneTid = URL("http://mysite.com").createUrlWithTid()
```

`Java`
```java
URL url = new URL("http://mysite.com");
URL urlWithOneTid = One.createUrlWithTid(url);
```

Once you have the `urlWithOneTid`, pass this into the method which handles the opening of the `URL`.

*Note:* The above methods return `null` if the SDK is not configured or is in Admin Mode.

#### Create an `android.net.Uri` or `java.net.URI` with a `one-tid` parameter to facilitate identity transfer 

If you have disabled automatic identity transfer, you can still create an `android.net.Uri` or `java.net.URI` with a `one-tid` parameter to use in the app programmatically, by calling the `java.net.URI.createUriWithTid()` or `android.net.Uri.createUriWithTid()` Kotlin extension functions or the `One.createUriWithTid(Uri|URI)` Java method as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.createUriWithTid
// rest of imports

val androidUriWithOneTid = Uri.parse("http://mysite.com").createUriWithTid()
val javaUriWithOneTid = URI("http://mysite.com").createUriWithTid()
```

`Java`
```java
Uri uri = Uri.parse("http://mysite.com");
Uri uriWithOneTid = One.createUriWithTid(uri);

URI javaUri = URI.create("http://mysite.com");
URI javaUriWithOneTid = One.createUriWithTid(javaUri);
```

Once you have the `uriWithOneTid`, pass this into the method which handles the opening of the `Uri`.

*Note:* The above methods return `null` if the SDK is not configured or is in Admin Mode.

### Disable automatic outbound link tracking

If the Orchestration Plugin is enabled, the SDK automatically sends an Interaction request to `/one-click` when a `URL` is opened in a `WebView`, `CustomTab` or external browser to facilitate last click attribution.

To disable this functionality, use the code below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneConfigureCodelessInteractionTracking

oneConfigureCodelessInteractionTracking {
    disableOutboundLinkTracking = true
}
```

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.codeless.OneCodelessInteractionTrackingConfiguration;

final OneCodelessInteractionTrackingConfiguration codelessInteractionTrackingConfiguration =
    new OneCodelessInteractionTrackingConfiguration.Builder()
        .disableOutboundLinkTracking(true)
        .build();

One.setCodelessInteractionTrackingConfiguration(codelessInteractionTrackingConfiguration);
```

#### Programmatically trigger an outbound link tracking Interaction call

If you have disabled automatic outbound link tracking, you can still track a `URL` or `Uri`, by calling the `java.net.URI.sendInteractionForOutboundLink` or `android.net.Uri.sendInteractionForOutboundLink` Kotlin extension functions or the `One.sendInteractionForOutboundLink` Java method, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.sendInteractionForOutboundLink

URI("https://www.yourfullurl.com/").sendInteractionForOutboundLink()
Uri.parse("https://www.yourfullurl.com/").sendInteractionForOutboundLink()

URL("https://www.yourfullurl.com/").sendInteractionForOutboundLink()
```


`Java`
```java
// URL example
try {
    One.sendInteractionForOutboundLink(new URL("https://www.yourfullurl.com/"));
} catch (MalformedURLException e) {
    e.printStackTrace();
}

// URI example
try {
     One.sendInteractionForOutboundLink(Uri.parse("https://www.yourfullurl.com/"));
} catch (MalformedURLException e) {
    e.printStackTrace();
}

```
Pass the `URL` or `Uri`, to send an Interaction request to `/one-click` using the same logic as is available automatically.

*Note:* 
- Sends a `POST` request to Thunderhead ONE or Salesforce Interaction Studio.
- Set up the `/one-click` Interaction request in Thunderhead ONE or Salesforce Interaction Studio, to capture the appropriate attributes and activity.

### Enable push notifications

To receive push notifications from Thunderhead ONE or Salesforce Interaction Studio, configure Firebase Cloud Messaging (FCM) by following the FCM setup instructions. At a minimum the app must be configured in Firebase and the `google-services.json` needs to be in the root of the app project.

**Important:** For apps configured with Firebase Cloud Messaging (FCM), or utilizes a third-party library using FCM, additional configuration is required.  See more [here](#additional-configuration-required-for-apps-configured-with-push-messaging).

#### Minimum Gradle configuration 

To use the codeless push notifications functionality without using FCM directly, you must at least have the `google-services` plugin applied to your app build.gradle. 

1. Add the Google Services Plugin to your classpath in the top-level build.gradle, located in the root project directory, as shown below:

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

2.  Apply the Google Messaging Service plugin to the app-level build.gradle, as shown below:

```gradle
// place this at the bottom of your app build.gradle
apply plugin: 'com.google.gms.google-services'
```
    
- The `Warning: The app gradle file must have a dependency on com.google.firebase:firebase-core for Firebase services to work as intended.` can safely be ignored as this is not required for push notification support.
    
#### Enable codeless push notification support programmatically

For Firebase Cloud Messaging, enable push notifications as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneConfigureMessaging

oneConfigureMessaging {
    enabled = true
}
```
  
`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.messaging.OneMessagingConfiguration;

final OneMessagingConfiguration oneMessagingConfiguration = new OneMessagingConfiguration.Builder()
    .enabled(true)
    .build();

One.setMessagingConfiguration(oneMessagingConfiguration);
```

*Note:* 
- When the Thunderhead SDK is the only push message provider in your application and you enable codeless push notification support, the SDK automatically gets the push token and handles the receiving of push notifications on behalf of your app.

##### Set a non adaptive fallback

Android (O)reo, API 26, shipped with a platform bug relating to Adaptive Icons and Notifications. The bug can be seen [here](https://issuetracker.google.com/issues/68716460).
The issue was resolved in API 27. It was not, however, back ported to the original Oreo API 26 platform.

The Thunderhead SDK will optimize your user's App experience by sending Push Notifications with _your_ application's icon when appropriate. In order to avoid the infinite crash loop that the above Android bug causes, the Thunderhead SDK will not show the message if a fallback *NON ADAPTIVE* icon is not set at initialization time on API 26 devices.
Changing your application's icon to a non adaptive icon is not required and the fall back is **only required for API 26**.

The Thunderhead SDK will warn you at init if the icon has not been set by logging the `14019` error. For more information, see the [Troubleshooting guide](TROUBLESHOOTING-GUIDE.md)

Here is an example of setting the fallback for API 26 devices using the built in Android "Star On" non adaptive drawable.  *Important: The icon set must not be adaptive!*

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneConfigureMessaging

oneConfigureMessaging {
    enabled = true
    nonAdaptiveSmallIcon = android.R.drawable.star_on
}
```

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.messaging.OneMessagingConfiguration;

final OneMessagingConfiguration oneMessagingConfiguration = new OneMessagingConfiguration.Builder()
    .nonAdaptiveSmallIcon(android.R.drawable.star_on)
    .enabled(true)
    .build();

One.setMessagingConfiguration(oneMessagingConfiguration);
```

### Get a push token

To get the push token codelessly retrieved by the SDK, call the `One.getMessagingToken` Java method as shown below:

```java  
String pushToken = One.getMessagingToken();
// work with the push token
```
*Note:*
- This can be useful for testing and debugging, or to retrieve the token and pass it to another push notification provider. 

### Send a push token

To send a push token, call the `One.setMessagingToken` method by passing a push token, as shown below:

```java
import com.thunderhead.One;

One.setMessagingToken("DUI03F379S1UUIDA6DADF8DFQPZ");
```

### Send a location object

To send a location object, pass the location object as a parameter to the `updateLocation` method, as shown below:

```java
One.processLocation(location);
```

Use the `LocationListener` callback method to call `updateLocation`, as shown below:

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
One.getTid();
```

*Note:*
- Returns the `tid` assigned to the current user as a `String`.
- Retrieving the current `tid` can be useful if you want to monitor the current user in Thunderhead ONE or Salesforce Interaction Studio, or if you need to pass the identity of the current user to another system that sends data to Thunderhead ONE or Salesforce Interaction Studio.

### Access debug information

The Thunderhead SDK for Android provides 4 distinct debugging levels that can be enabled after the SDK has been initialized, as shown below:

1. `NONE` - if set, no messages will be displayed in the console.
  
```java
One.setLogLevel(OneLogLevel.NONE);
```

2. `ALL` - if set, all log messages will be displayed in the console.
  
```java
One.setLogLevel(OneLogLevel.ALL);
```

3. `WEB_SERVICE` - if set, only web service logs will be displayed in the console.

```java
One.setLogLevel(OneLogLevel.WEB_SERVICE);
```

4. `FRAMEWORK` - if set, only framework logs will be displayed in the console.
  
```java
One.setLogLevel(OneLogLevel.FRAMEWORK);
```

*Note:* 
- By default, the Thunderhead SDK for Android does not display any debug log messages. However, exception messages are printed in the console, when these occur.

### Identify the SDK version

Find the current version of the SDK by calling:

```java
One.getVersion();
```

### Clear the user profile

Programmatically erase the user profile data by calling:

```java
One.clearUserProfile();
```

*Note:* 
- Removes the stored `tid` only from local storage.
- For instructions on how completely remove a user's data from Thunderhead ONE or Salesforce Interaction Studio, see our [API Documentation](https://thunderheadone.github.io/one-api/#operation/delete).

## Further integration details

### How to disable the codeless identity transfer support

To completely remove the codeless identity transfer functionality for Android, make the following updates:

1. Open the **top-level** `build.gradle` file and remove the following dependency reference.

```gradle 
classpath 'com.thunderhead.android:orchestration-plugin:2.0.0'
```

2. Open the **app-level** `build.gradle` file and remove the following references.

```gradle 
apply plugin: 'com.thunderhead.android.orchestration-plugin'
```

## Troubleshooting guide
[Troubleshooting guide](TROUBLESHOOTING-GUIDE.md)

## Questions or need help

### Salesforce Interaction Studio support
_For Salesforce Marketing Cloud Interaction Studio questions, please submit a support ticket via https://help.salesforce.com/home_

### Thunderhead ONE support
_The Thunderhead team is available 24/7 to answer any questions you have. Just email onesupport@thunderhead.com or visit our docs page for more detailed installation and usage information._

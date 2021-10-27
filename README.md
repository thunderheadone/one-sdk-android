![Thunderhead SDK](https://i.imgur.com/gfizURy.png "Thunderhead")

## Resources

* [Migration Guides](https://github.com/thunderheadone/one-sdk-android/tree/master/migration-guides)
* [API docs](https://thunderheadone.github.io/one-sdk-android/)

## Table of Contents

* [Requirements](#requirements)
* [Installation](#installation)
* [Configure the codeless Thunderhead SDK for Android](#configure-the-codeless-thunderhead-sdk-for-android)
    * [Set up the SDK in User mode for Play Store builds](#set-up-the-sdk-in-user-mode-for-play-store-builds)
    * [Set up the SDK in Admin mode for internal distribution](#set-up-the-sdk-in-admin-mode-for-internal-distribution)
    * [Further codeless integration considerations](#further-codeless-integration-considerations)
        * [Sending codeless Interactions based on the list of Interactions created under a Touchpoint](#sending-codeless-interactions-based-on-the-list-of-interactions-created-under-a-touchpoint)
        * [Thunderhead Application Manifest file permissions](#thunderhead-application-manifest-file-permissions)
        * [Configure and reconfigure the SDK](#configure-and-reconfigure-the-sdk)
        * [SDK initialization not required](#sdk-initialization-not-required)
* [Additional features](#additional-features)
    * [Opt an end-user out of tracking](#opt-an-end-user-out-of-tracking)
    * [Opt an end user out of city country level tracking](#opt-an-end-user-out-of-city-country-level-tracking)
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
        * [Example: Retrieve a response for an automatic Activity Interaction](#example-retrieve-a-response-for-an-automatic-activity-interaction)
        * [Example: Retrieve a response for an automatic Fragment Interaction](#example-retrieve-a-response-for-an-automatic-fragment-interaction)
        * [Example: Retrieve a response for a manually assigned Interaction](#example-retrieve-a-response-for-a-manually-assigned-interaction)
        * [Optional response processing](#optional-response-processing)
    * [Assign an Interaction to a View](#assign-an-interaction-to-a-view)
    * [Ability to whitelist identity transfer links](#ability-to-whitelist-identity-transfer-links)
    * [Ability to blacklist identity transfer links](#ability-to-blacklist-identity-transfer-links)
    * [Disable automatic identity transfer](#disable-automatic-identity-transfer)
        * [Send deep link properties programmatically](#send-deep-link-properties-programmatically)
        * [Create a `URL` with a `one-tid` parameter to facilitate identity transfer](#create-a-url-with-a-one-tid-parameter-to-facilitate-identity-transfer)
        * [Create an `android.net.Uri` or `java.net.URI` with a `one-tid` parameter to facilitate identity transfer](#create-an-androidneturi-or-javaneturi-with-a-one-tid-parameter-to-facilitate-identity-transfer)
    * [Disable automatic outbound link tracking](#disable-automatic-outbound-link-tracking)
        * [Programmatically trigger an outbound link tracking Interaction call](#programmatically-trigger-an-outbound-link-tracking-interaction-call)
     * [Send a location object](#send-a-location-object)
     * [Get Tid](#get-tid)
     * [Configuring Logging](#configuring-logging)
        * [Turn all logs on](#turn-all-logs-on)
        * [Turn specific logs on](#turn-specific-logs-on)
        * [Turn logs for the Thunderhead SDK initialization process on](#turn-logs-for-the-thunderhead-sdk-initialization-process-on)
        * [Turn all logs off](#turn-all-logs-off)
     * [Identify the SDK version](#identify-the-sdk-version)
     * [Clear the user profile](#clear-the-user-profile)
* [Further integration details](#further-integration-details)
    * [How to disable the codeless identity transfer support](#how-to-disable-the-codeless-identity-transfer-support)
* [Troubleshooting guide](#troubleshooting-guide)
* [Questions or need help](#questions-or-need-help)
    * [Salesforce Interaction Studio support](#salesforce-interaction-studio-support)
    * [Thunderhead ONE support](#thunderhead-one-support)

## Requirements

+ [Android Gradle Plugin](https://developer.android.com/studio/releases/gradle-plugin) 3.6.x
+ Android 5.0+ (API 21) and above
+ [Gradle](https://gradle.org/releases/) 5.6.4

## Installation

1. Open your existing Android application in Android Studio.
2. Include the Thunderhead SDK as a dependency in your project:
    Navigate to your **app-level** build.gradle.
    
    Add the following, under the dependencies section:
    
    For **Thunderhead ONE** integrations:

    ```gradle
    dependencies {     
      implementation "com.thunderhead.android:one-sdk:11.0.0"
    }
    ```
    
    For **Salesforce Interaction Studio** integrations:
    
    ```gradle
    dependencies {     
      implementation "com.thunderhead.android:is-sdk:11.0.0"
    }
    ```

3. Add the Thunderhead SDK configuration within the same **app-level** `build.gradle` file. 

    Add `RenderScript` support under the `defaultConfig` section:

    ```gradle
    defaultConfig {
        renderscriptTargetApi 22
        renderscriptSupportModeEnabled true
    }
    ```

    Add the following, under the repositories section:

    ``` gradle 
    repositories {
        maven {
            url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
        }
    }
    ```

    Append the following configuration, for both **Thunderhead ONE** and **Salesforce Interaction Studio** integrations:

    ``` gradle 
    apply plugin: 'com.thunderhead.android.orchestration-plugin'
    ```
  
4. Add Java 8 Support

    Add the following, under the `android` section

    ```groovy
    compileOptions {
        sourceCompatibility 1.8
        targetCompatibility 1.8
    }
    ```

5. Update your `build.gradle` file to add codeless identity transfer support.

    Navigate to the **top-level** `build.gradle` file and add both a maven repository url and class path dependencies as shown below:

    ``` gradle 
    buildscript {
        repositories {
            google()
            mavenCentral()
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
            classpath 'com.android.tools.build:gradle:4.2.0'
            classpath 'com.thunderhead.android:orchestration-plugin:6.0.0'
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
        mavenCentral()
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
        classpath 'com.android.tools.build:gradle:4.2.0'
        classpath 'com.thunderhead.android:orchestration-plugin:6.0.0'
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
```

###### Example of the **app-level** `build.gradle` file after integration

``` gradle
apply plugin: 'com.android.application'
apply plugin: 'com.thunderhead.android.orchestration-plugin'

android {
    compileSdkVersion 29
    compileOptions {
        sourceCompatibility 1.8
        targetCompatibility 1.8
    }

    defaultConfig {
        applicationId "com.thunderhead.android.demo"
        minSdkVersion 21
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"

        renderscriptTargetApi 22
        renderscriptSupportModeEnabled true
    }
}

dependencies {     
  implementation "com.thunderhead.android:one-sdk:11.0.0"
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
        mavenCentral()
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
        classpath 'com.android.tools.build:gradle:4.2.0'
        classpath 'com.thunderhead.android:orchestration-plugin:6.0.0'
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
```

###### Example of the **app-level** `build.gradle` file after integration

``` gradle
apply plugin: 'com.android.application'
apply plugin: 'com.thunderhead.android.orchestration-plugin'

android {
    compileSdkVersion 29
    compileOptions {
        sourceCompatibility 1.8
        targetCompatibility 1.8
    }
    defaultConfig {
        applicationId "com.thunderhead.android.demo"
        minSdkVersion 21
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"

        renderscriptTargetApi 22
        renderscriptSupportModeEnabled true
    }
}

dependencies {     
  implementation "com.thunderhead.android:is-sdk:11.0.0"
}

repositories {
    maven {
       url 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
    }
}

```

For further documentation on the `orchestration-plugin` please see the [Orchestration Plugin Readme](https://github.com/thunderheadone/one-android-orchestration-plugin/blob/master/README.md).

## Configure the codeless Thunderhead SDK for Android

Enable your app to automatically recognize **Interactions** in your app, by executing the following steps:

*Developer note:* Android Studio `Instant Run` is not currently supported and must be disabled.

### Set up the SDK in User mode for Play Store builds

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

### Set up the SDK in Admin mode for internal distribution

To use the SDK in Admin mode, change the `OneModes` parameter to `OneModes.ADMIN_MODE`.

*Note:* 
- If you are running in Admin mode on Android 6.0+, you must enable the “draw over other apps” permission through your OS settings. 
- Dynamic configuration of both Admin and User mode is supported.

**You have now successfully integrated the codeless Thunderhead SDK for Android.**

### Further codeless integration considerations

#### Sending codeless Interactions based on the list of Interactions created under a Touchpoint

In order to reduce the number of unnecessary Interaction requests sent automatically by the SDK, only codeless Interactions with explicit Interaction paths created under a Touchpoint and configured with at least one point are sent to Thunderhead ONE or Salesforce Interaction Studio. This configuration change has been introduced in version 8.1.0 of the Android SDK.

*Note:*
- The SDK will only send codeless Interactions if they have been created under a Touchpoint and/or if they match wildcard rules defined under a Touchpoint.
- For a codeless Interaction to be sent by the SDK this Interaction needs to contain at least one Activity Capture Point, Attribute Capture Point, and/or Optimization Point.
- If you are running the SDK in [User mode](#set-up-the-sdk-in-user-mode), you need to ensure that all Interactions and related points have been fully published, before the SDK will trigger a request.

#### Thunderhead Application Manifest file permissions

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

#### Configure and reconfigure the SDK

You can configure and reconfigure the SDK as many times as necessary. 
* The SDK does not support partial, or piecemeal, configuration. You must provide all parameters, either all valid or invalid (`empty string` or `null`).  
* When configured with invalid parameters, the SDK is set in an *unconfigured* state.

See [here](https://github.com/thunderheadone/one-sdk-android/tree/master/examples/dynamic-configuration-example) for an example app that demonstrates dynamic configuration.

#### SDK initialization not required

The Thunderhead SDK is automatically initialized in an *unconfigured* state.
* When *unconfigured*, the SDK queues end-user data locally and uploads that data to the server once the SDK is configured with valid parameters.
* You can disable this functionality, at any time, by setting the `oneOptOutConfiguration` to `true`. See more about opt out [here](#opt-an-end-user-out-of-tracking).

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

### Opt an end user out of city country level tracking

Use this option to opt an end-user out or in of all city/country level tracking.

Examples of how to opt in to city/country level tracking

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneConfigureOptOut
import com.thunderhead.android.api.optout.OptInOptions

val options = EnumSet.noneOf(OptInOptions::class.java)
options.add(OptInOptions.CITY_COUNTRY_DETECTION)
oneConfigureOptOut {
    optOut = false
    optInOptions = options
}
```

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.optout.OneOptOutConfiguration;
import com.thunderhead.android.api.optout.OptInOptions;

Set<OptInOptions> options = EnumSet.noneOf(OptInOptions.class);
options.add(OptInOptions.CITY_COUNTRY_DETECTION);
final OneOptOutConfiguration optOutConfiguration = new OneOptOutConfiguration.Builder()
            .optOut(false)
            .optInOptions(options)
            .build();

One.setOptOutConfiguration(optOutConfiguration);
```

Examples of how to opt out of city/country level tracking

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneConfigureOptOut
import com.thunderhead.android.api.optout.OptInOptions

val options = EnumSet.noneOf(OptInOptions::class.java)
oneConfigureOptOut {
    optOut = false
    optInOptions = options
}
```

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.optout.OneOptOutConfiguration;
import com.thunderhead.android.api.optout.OptInOptions;

Set<OptInOptions> options = EnumSet.noneOf(OptInOptions.class);
final OneOptOutConfiguration optOutConfiguration = new OneOptOutConfiguration.Builder()
            .optOut(false)
            .optInOptions(options)
            .build();

One.setOptOutConfiguration(optOutConfiguration);
```

*Note:*
- By default a user is opted in and would need to be specifically opted out using the method mentioned above, depending on your specific privacy requirements.
- When a user is opted out, all opt in options are ignored.

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

You can disable automatic Interaction detection by calling the `oneConfigureCodelessInteractionTracking` Kotlin top-level function or 
the `One.setCodelessInteractionTrackingConfiguration` Java method with the appropriate configuration, as shown below:

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneConfigureCodelessInteractionTracking

oneConfigureCodelessInteractionTracking {
    // disables Fragment/Activity Interaction Tracking
    disableCodelessInteractionTracking = true 
    // disables WebView URL Interaction Tracking
    disableWebViewInteractionTracking = true
    // disables Outbound Link Tracking
    disableOutboundLinkTracking = true
}
```

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.codeless.OneCodelessInteractionTrackingConfiguration;

final OneCodelessInteractionTrackingConfiguration codelessInteractionTrackingConfiguration =
    new OneCodelessInteractionTrackingConfiguration.Builder()
        // disables Fragment/Activity Interaction Tracking
        .disableCodelessInteractionTracking(true)
        // disables WebView URL Interaction Tracking
        .disableWebViewInteractionTracking(true)
        // disables Outbound Link Tracking
        .disableOutboundLinkTracking(true)
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

The Thunderhead SDK considers Android Activities and Fragments as Interactions. When configured correctly the SDK will _automatically_
send an Interaction request to ONE and process the response which may contain points (optimizations, capture, etc). If desired,
you can be notified of these automatic Interactions to take additional action on each Interaction request, by using the
automatic Interaction callback API.

**Notes**
* It is incumbent on you to then process the response in order for the Thunderhead SDK to perform automatic capture and optimization.
* Assigning a manual/custom Interaction to a view should be done _before_ setting an automatic Interaction callback.
* If you set a callback for an automatically triggered Interaction, you are advised to remove that callback as soon as it is no longer needed under your Activity's `onStop` method.

#### Example: Retrieve a response for an automatic Activity Interaction

`Kotlin`
```kotlin
import com.thunderhead.android.api.process
import com.thunderhead.android.api.setAutomaticInteractionCallback
import com.thunderhead.android.api.removeAutomaticInteractionCallback
// rest of imports

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setAutomaticInteractionCallback {
            onError { error ->
                Log.e(TAG, "SDK Error", error)
            }

            onFailure { error ->
                Log.e(TAG, "API Error", error)
            }

            onSuccess { response ->
                Log.d(TAG, "Success: ${response.tid}")
                // Do something with response
                response.process()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        removeAutomaticInteractionCallback()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
```

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.interactions.OneCallback;
import com.thunderhead.android.api.responsetypes.OneAPIError;
import com.thunderhead.android.api.responsetypes.OneResponse;
import com.thunderhead.android.api.responsetypes.OneSDKError;
// rest of imports

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        One.setAutomaticInteractionCallback(this, new OneCallback() {
            @Override
            public void onError(@NotNull OneSDKError error) {
                Log.e(TAG, "SDK Error", error);
            }

            @Override
            public void onFailure(@NotNull OneAPIError error) {
                Log.e(TAG, "API Error", error);
            }

            @Override
            public void onSuccess(@NotNull OneResponse response) {
                Log.d(TAG, response.getTid());
                // Do something with response
                One.processResponse(response);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        One.removeAutomaticInteractionCallback(this);
    }
}
```

#### Example: Retrieve a response for an automatic Fragment Interaction

`Kotlin`
```kotlin
import com.thunderhead.android.api.process
import com.thunderhead.android.api.setAutomaticInteractionCallback
import com.thunderhead.android.api.removeAutomaticInteractionCallback
// rest of imports

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, TestFragment())
                .setReorderingAllowed(true)
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager
            .findFragmentById(R.id.fragment_container)
            ?.setAutomaticInteractionCallback {
                onError { error ->
                    Log.e(TAG, "SDK Error", error)
                }

                onFailure { error ->
                    Log.e(TAG, "API Error", error)
                }

                onSuccess { response ->
                    Log.d(TAG, "Success: ${response.tid}")
                    // Do something with response
                    response.process()
                }
            }
    }

    override fun onStop() {
        super.onStop()
        supportFragmentManager
            .findFragmentById(R.id.fragment_container)
            ?.removeAutomaticInteractionCallback()
    }

    class TestFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = inflater.inflate(R.layout.fragment_test, container, false)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
```

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.interactions.OneCallback;
import com.thunderhead.android.api.responsetypes.OneAPIError;
import com.thunderhead.android.api.responsetypes.OneResponse;
import com.thunderhead.android.api.responsetypes.OneSDKError;
// rest of imports

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new TestFragment());
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        One.setAutomaticInteractionCallback(fragment, new OneCallback() {
            @Override

            @Override
            public void onError(@NotNull OneSDKError error) {
                Log.e(TAG, "SDK Error", error);
            }

            @Override
            public void onFailure(@NotNull OneAPIError error) {
                Log.e(TAG, "API Error", error);
            }

            public void onSuccess(@NotNull OneResponse response) {
                Log.d(TAG, response.getTid());
                // Do something with response
                One.processResponse(response);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        One.removeAutomaticInteractionCallback(fragment);
    }
}
```

#### Example: Retrieve a response for a manually assigned Interaction

`Kotlin`
```kotlin
import com.thunderhead.android.api.oneSetAutomaticInteractionCallback
import com.thunderhead.android.api.interactions.OneInteractionPath
import com.thunderhead.android.api.oneRemoveAutomaticInteractionCallback
import com.thunderhead.android.api.process
// rest of imports

oneSetAutomaticInteractionCallback(OneInteractionPath(URI("/ManualInteraction"))) {
    onError { error ->
        Log.e(TAG, "SDK Error", error)
    }

    onFailure { error ->
        Log.e(TAG, "API Error", error)
    }

    onSuccess { response ->
        Log.d(TAG, "Success: ${response.tid}")
        // Do something with response
        response.process()
    }
}

  override fun onStop() {
      super.onStop()
      oneRemoveAutomaticInteractionCallback(OneInteractionPath(URI("/ManualInteraction")))
```

`Java`
```java
import com.thunderhead.One;
import com.thunderhead.android.api.interactions.OneInteractionPath;
import com.thunderhead.android.api.responsetypes.OneAPIError;
import com.thunderhead.android.api.responsetypes.OneResponse;
import com.thunderhead.android.api.responsetypes.OneSDKError;
// rest of imports

One.setAutomaticInteractionCallback(new OneInteractionPath(URI.create("/ManualInteraction")), new OneCallback() {
    @Override
    public void onFailure(@NotNull OneAPIError error) {
        Log.e(TAG, "ApiError", error);
    }

    @Override
    public void onError(@NotNull OneSDKError error) {
        Log.e(TAG, "SdkError", error);
    }

    @Override
    public void onSuccess(@NotNull OneResponse response) {
        Log.d(TAG, "Success: ${response.tid}");
        // Do something with response
        One.processResponse(response);
    }
});

    @Override
    protected void onStop() {
        super.onStop();
        One.removeAutomaticInteractionCallback(new OneInteractionPath(URI.create("/ManualInteraction")));
    }
```
#### Optional response processing 

The response can be passed to the `processResponse` method, as shown above. By calling this method the response is returned to the SDK to process, attaching any captures, trackers, and/or optimizations to the Interaction.

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

### Configuring Logging

The Thunderhead SDK for Android provides an extensible logging configuration API for debug or reporting purposes. The API can be configured to 
log any combination of Components (features or technical concepts such as Networking or Databases) to Log Levels (Verbose, Debug, etc). In addition,
custom log writers can be added to facilitate reporting if desired (ex. sending errors to Google Console).

By default, the Thunderhead SDK for Android logs ERROR and WARN messages for ANY component. Below are examples of other logging configurations.

#### Turn all logs on

*Example of configuring logging to VERBOSE Log Level for ANY Components of the Thunderhead SDK.*

`Kotlin`
```kotlin
import com.thunderhead.android.api.logging.Component
import com.thunderhead.android.api.logging.LogLevel
import com.thunderhead.android.api.oneConfigureLogging
// rest of imports

oneConfigureLogging {
    levels = mutableSetOf(LogLevel.VERBOSE) 
    components = mutableSetOf(LogLevel.ANY)
}
```

`Java`
```java
import com.thunderhead.android.api.logging.Component;
import com.thunderhead.android.api.logging.LogLevel;
import com.thunderhead.android.api.logging.OneLoggingConfiguration;
// rest of imports

final OneLoggingConfiguration oneLoggingConfiguration = OneLoggingConfiguration.builder()
    .log(LogLevel.VERBOSE)
    .log(Component.ANY)
    .build();

One.setLoggingConfiguration(oneLoggingConfiguration);
```

#### Turn specific logs on

*Example of configuring logging to combination of ERROR and WARN levels for just NETWORKING and DATABASE Components of the Thunderhead SDK.*

`Kotlin`
```kotlin
import com.thunderhead.android.api.logging.Component
import com.thunderhead.android.api.logging.LogLevel
import com.thunderhead.android.api.logging.and
import com.thunderhead.android.api.oneConfigureLogging
// rest of imports

oneConfigureLogging {
    levels = LogLevel.ERROR and LogLevel.WARN
    components = Component.NETWORKING and Component.DATABASE
}
```

`Java`
```java
import com.thunderhead.android.api.logging.Component;
import com.thunderhead.android.api.logging.LogLevel;
import com.thunderhead.android.api.logging.OneLoggingConfiguration;
// rest of imports

final OneLoggingConfiguration oneLoggingConfiguration = OneLoggingConfiguration.builder()
    .log(LogLevel.ERROR)
    .log(LogLevel.WARN)
    .log(Component.NETWORKING)
    .log(Component.DATABASE)
    .build();

One.setLoggingConfiguration(oneLoggingConfiguration);
```

*Example of using a custom logger*

`Kotlin`
```kotlin
import com.thunderhead.android.api.logging.Component
import com.thunderhead.android.api.logging.LogLevel
import com.thunderhead.android.api.logging.and
import com.thunderhead.android.api.oneConfigureLogging
import com.thunderhead.android.api.logging.LogWriter
// rest of imports

oneConfigureLogging {
    levels = mutableSetOf(LogLevel.VERBOSE) 
    components = Component.NETWORKING and Component.DATABASE
    logWriters = mutableSetOf(CustomLogger())
}

// custom logger
class CustomLogger : LogWriter() {
    override fun log(
        logLevel: LogLevel,
        component: Component,
        message: String,
        throwable: Throwable?
    ) {
        Log.d("CustomLogger", "Component: ${component.name}\nMessage: $message", throwable)
    }
}
```

`Java`
```java
import com.thunderhead.android.api.logging.Component;
import com.thunderhead.android.api.logging.LogLevel;
import com.thunderhead.android.api.logging.OneLoggingConfiguration;
import com.thunderhead.android.api.logging.LogWriter;
// rest of imports

final OneLoggingConfiguration oneLoggingConfiguration = OneLoggingConfiguration.builder()
    .log(LogLevel.VERBOSE)
    .log(Component.NETWORKING)
    .log(Component.DATABASE)
    .logTo(new CustomLogger())
    .build();

One.setLoggingConfiguration(oneLoggingConfiguration);

// custom logger
static class CustomLogger extends LogWriter {
        @Override
        public void log(
                @NotNull LogLevel logLevel,
                @NotNull Component component,
                @NotNull String message,
                @Nullable Throwable throwable
        ) {
            Log.d("CustomLogger", "Component: " + component.name() + "\nMessage: "  + message, throwable);
        }
    }
```

#### Turn logs for the Thunderhead SDK initialization process on

The Thunderhead SDK performs initialization processes in an Android Content Provider which is instantiated before
the Application is created. This means the log configuration API cannot be invoked before the Thunderhead SDK
has finished its initialization process. To turn on logging for the initialization process of the Thunderhead SDK
a meta data element must be added to the android manifest. If the metadata element is not set no logging is configured.

*Metadata Info:*

`name` :  `com.thunderhead.android.InitLogLevel`
`value`: Comma separated list of `com.thunderhead.android.api.logging.LogLevel`

*Example for logging VERBOSE and above logs:*

```xml
<application>
    <!--Other application elements-->

    <meta-data
        android:name="com.thunderhead.android.InitLogLevel"
        android:value="VERBOSE" />
</application>
```

**Recommendation**
We recommend including the above metadata only in `DEBUG` builds to ensure no unnecessary logging
occurs in release. Therefore, only include this metadata in the `DEBUG` variant
`AndroidManifest.xml` and _NOT_ in the main `AndroidManifest.xml`. To learn more about how manifests
are merged, please see the [Android Documentation](https://developer.android.com/studio/build/manifest-merge).

#### Turn all logs off

To turn off logging, pass a set to logLevel and a set to logComponent with the values of NONE
*Example of turning logging off*

`Kotlin`
```kotlin
import com.thunderhead.android.api.logging.Component
import com.thunderhead.android.api.logging.LogLevel
import com.thunderhead.android.api.logging.and
import com.thunderhead.android.api.oneConfigureLogging
// rest of imports

oneConfigureLogging {
    levels = mutableSetOf(LogLevel.NONE)
    components = mutableSetOf(Component.NONE)
}
```

`Java`
```java
import com.thunderhead.android.api.logging.Component;
import com.thunderhead.android.api.logging.LogLevel;
import com.thunderhead.android.api.logging.OneLoggingConfiguration;
// rest of imports

final OneLoggingConfiguration oneLoggingConfiguration = OneLoggingConfiguration.builder()
    .log(LogLevel.NONE)
    .log(Component.NONE)
    .build();

One.setLoggingConfiguration(oneLoggingConfiguration);
```

*Note:* 
- The `com.thunderhead.android.InitLogLevel` `AndroidManifest.xml` metadata value is only honored 
for the Thunderhead SDK initialization process. After initialization has finished, the logging 
configuration reverts to a default configuration mentioned above. If more logging is desired then use the logging
configuration APIs to turn on logging as shown above.
- When setting a single `LogLevel`, the SDK will log any messages of that level and above.
    - The order from the bottom is: VERBOSE, DEBUG, ERROR, WARN, INFO, ASSERT
    - Example: Setting VERBOSE will log all messages.
    - Example: Setting INFO will log only INFO and ASSERT messages.
- When setting multiple `LogLevel`(s), the SDK will log only messages of those specific levels.
    - Example: Setting ERROR and WARN will only log message of ERROR and WARN levels and nothing else.
- When setting `Component` to _only_ `ANY` all components will be logged in conjunction with the log level.
- When setting multiple `Component`(s), the SDK will log only messages for those specific components.
    - Do not set set multiple `Components`(s) along side the `ANY` component. Choose only the components
        required or just use `ANY` but not both.

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
classpath 'com.thunderhead.android:orchestration-plugin:6.0.0'
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


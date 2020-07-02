# Upgrading from older versions of the SDK to 6.*

Version 6 of the Thunderhead Android SDK introduces breaking public API changes.

The new APIs, still available under the `One` class, are now static APIs as opposed to 
object instance APIs.  These APIs aim to be consistent, easily discoverable, and semantic.
First class support for Kotlin [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) has been introduced as well as updates to 
Java APIs bringing familiar and standard patterns modeled after prominent libraries
like [Retrofit](https://square.github.io/retrofit/).

For each particular feature of the SDK that has a public API, any related types can
be found in the `com.thunderhead.android.api` package.

## Overview of breaking changes

Below is a list of build migrations to follow when upgrading from older versions of the SDK to 6.0.0

### Java 8 language support

The Thunderhead SDK now targets the Java 8 language specification.  This requires
changes to the `app.gradle` -> `android` configuration.  

**Change**
An application must target and compile Java 8 byte code.

**Action Required**
Add the following snippet to the `app.gradle` -> `android` configuration.

```groovy
compileOptions { 
    sourceCompatibility JavaVersion.VERSION_1_8 
    targetCompatibility JavaVersion.VERSION_1_8 
}
``` 

## Below is a list of API migrations to follow when upgrading from older versions of the SDK to 6.0.0

### SDK initialization 

Versions prior to 6.0.0 required SDK initialization providing a `Context`, which is no longer needed.  
The SDK is automatically initialized in an *unconfigured* state when the `Application` is created, 
but will still require configuration with proper API credentials where necessary.

Debug initialization logging will be done via `android.util.Log`
class under the `ThunderheadLogger` TAG. This will be configurable in future
versions.

**Change**
`One.getInstance(Context)` has been removed.
`One::init` has been removed.

**Action Required**
Remove all calls to `One.getInstance(Context)` and access static members 
as appropriate. See [`Configure and reconfigure the SDK`](README.md#configure-and-reconfigure-the-sdk) for replacement of `One::init`.
See `Using Static Members` for accessing static members.

### SDK dynamic configuration
Version 6.0.0 introduced the idea of _Dynamic Configuration_ allowing app
developers to change SDK configuration on the fly. Such configuration
change examples are changing the ONE Touchpoint, the ONE SiteKey, 
the API credentials used to communicate with ONE, or switching between Admin and User modes. 

**Change**
`One::init` has been removed.

**Action Required**
Remove calls to `One::init` and instead use the new public configuration API.
The new public API can be invoked at any point of an apps lifecycle, it 
is no longer required to be called on `Application::onCreate`.

```kotlin
// top level builder functions are available under the com.thunderhead.android.api package

import com.thunderhead.One
import com.thunderhead.OneModes
import com.thunderhead.android.api.configureOne

// ... rest of code

configureOne {
    siteKey = "SITE_KEY"
    touchpoint = URI("android://mobiletouchpoint")
    userId = "USER_ID"
    apiKey = "API_KEY"
    sharedSecret = "SHARED_SECRET"
    host = URI("https://url.com")
    mode = OneModes.USER_MODE
}
```

```java

import com.thunderhead.One;
import com.thunderhead.OneModes;
import com.thunderhead.android.api.configuration.OneConfiguration;
import com.thunderhead.android.api.messaging.OneMessagingConfiguration;

// ... rest of code

final OneConfiguration oneConfiguration = new OneConfiguration.Builder()
        .host(URI.create("https://url.com"))
        .sharedSecret("shared_secret")
        .mode(OneModes.USER_MODE)
        .touchpoint(URI.create("android://mobiletouchpoint"))
        .userId("USER_ID")
        .siteKey("SITE_KEY")
        .apiKey("API_KEY")
        .build();

One.setConfiguration(oneConfiguration);
```

### SDK messaging

**Change**
`One::enablePushNotifications` has been removed.

**Action Required**
Remove calls to `One::enablePushNotifications` and use the new public API.


```kotlin
// top level builder functions are available under the com.thunderhead.android.api package
import com.thunderhead.One
import com.thunderhead.android.api.configureOneMessaging

// ... rest of code

oneConfigureMessaging {
    enabled = true
    nonAdaptiveSmallIcon = android.R.drawable.star_on
}
```

```java

import com.thunderhead.One;
import com.thunderhead.android.api.messaging.OneMessagingConfiguration;

// ... rest of code

final OneMessagingConfiguration oneMessagingConfiguration = new OneMessagingConfiguration.Builder()
        .nonAdaptiveSmallIcon(android.R.drawable.star_on)
        .enabled(true)
        .build();

One.setMessagingConfiguration(oneMessagingConfiguration);
```
### Using static members

Version 6.0.0 introduced the idea of static APIs to programmatically interact with the SDK.
The [Thunderhead README](README.md#additional-features) `Additional Features` section provides steps to access functions
available in the SDK.  A usage example is shown in this section of the migration guide.

**Change**
`One.getInstance(Context)` has been removed, eliminating the requirement of
obtaining a `One` instance to implement Interactions programmatically.

**Action Required**
Remove all calls to `One.getInstance(Context)` and access static members
as appropriate.  For example here is an updated api static call to programmatically
send an Interaction and process the response.

You can send an Interaction request programmatically and process the response
by calling the `One.sendInteraction` Java method and enqueue with a callback as shown below:

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

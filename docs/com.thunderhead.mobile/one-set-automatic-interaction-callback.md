//[thunderhead-sdk](../../index.md)/[com.thunderhead.mobile](index.md)/[oneSetAutomaticInteractionCallback](one-set-automatic-interaction-callback.md)

# oneSetAutomaticInteractionCallback

[androidJvm]\

@JvmName(name = setAutomaticInteractionCallback)

fun [oneSetAutomaticInteractionCallback](one-set-automatic-interaction-callback.md)(activity: Activity?, callback: [OneCallback](../com.thunderhead.mobile.interactions/-one-callback/index.md)?)

Set a callback in order to be notified of an *automatic* interaction request for thisActivity.

To remove call [removeAutomaticInteractionCallback](remove-automatic-interaction-callback.md).

**Important** This method should be called *after*[assignInteractionPath](assign-interaction-path.md)

[androidJvm]\
inline fun [oneSetAutomaticInteractionCallback](one-set-automatic-interaction-callback.md)(oneInteractionPath: [OneInteractionPath](../com.thunderhead.mobile.interactions/-one-interaction-path/index.md), init: [OneAutomaticInteractionCallback](../com.thunderhead.mobile.interactions/-one-automatic-interaction-callback/index.md).() -> Unit = { })

Set a callback in order to be notified of *automatic* interaction requests.

To remove call [oneRemoveAutomaticInteractionCallback](one-remove-automatic-interaction-callback.md).

Example:

oneSetAutomaticInteractionCallback(OneInteractionPath(URI("/MainActivity"))) {\
 onSuccess { response ->\
     // perform custom action\
     response.process()\
 }\
\
 onError { sdkError ->\
     Log.d(TAG, "SdkError: ${sdkError.errorMessage}")\
 }\
\
 onFailure { apiError ->\
     Log.d(TAG, "ApiError: ${apiError.errorMessage}")\
 }\
}

[androidJvm]\

@JvmName(name = setAutomaticInteractionCallback)

fun [oneSetAutomaticInteractionCallback](one-set-automatic-interaction-callback.md)(oneInteractionPath: [OneInteractionPath](../com.thunderhead.mobile.interactions/-one-interaction-path/index.md)?, callback: [OneCallback](../com.thunderhead.mobile.interactions/-one-callback/index.md)?)

Set a callback in order to be notified of *automatic* interaction requests.

To remove call [oneRemoveAutomaticInteractionCallback](one-remove-automatic-interaction-callback.md).

[androidJvm]\

@JvmName(name = setAutomaticInteractionCallback)

fun [oneSetAutomaticInteractionCallback](one-set-automatic-interaction-callback.md)(fragment: Fragment?, callback: [OneCallback](../com.thunderhead.mobile.interactions/-one-callback/index.md)?)

Set a callback in order to be notified of an *automatic* interaction request for thisFragment.

To remove call [removeAutomaticInteractionCallback](remove-automatic-interaction-callback.md).

**Important** This method should be called *after*[assignInteractionPath](assign-interaction-path.md)

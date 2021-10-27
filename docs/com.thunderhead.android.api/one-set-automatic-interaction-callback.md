//[thunderhead-sdk](../../index.md)/[com.thunderhead.android.api](index.md)/[oneSetAutomaticInteractionCallback](one-set-automatic-interaction-callback.md)

# oneSetAutomaticInteractionCallback

[androidJvm]\
fun [oneSetAutomaticInteractionCallback](one-set-automatic-interaction-callback.md)(oneInteractionPath: [OneInteractionPath](../com.thunderhead.android.api.interactions/-one-interaction-path/index.md), init: [OneAutomaticInteractionCallback](../com.thunderhead.android.api.interactions/-one-automatic-interaction-callback/index.md).() -> Unit)

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

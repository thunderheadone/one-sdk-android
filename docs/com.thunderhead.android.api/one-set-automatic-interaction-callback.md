[Thunderhead](../index.md) / [com.thunderhead.android.api](index.md) / [oneSetAutomaticInteractionCallback](./one-set-automatic-interaction-callback.md)

# oneSetAutomaticInteractionCallback

`fun oneSetAutomaticInteractionCallback(oneInteractionPath: `[`OneInteractionPath`](../com.thunderhead.android.api.interactions/-one-interaction-path/index.md)`, init: `[`OneAutomaticInteractionCallback`](../com.thunderhead.android.api.interactions/-one-automatic-interaction-callback/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Set a callback in order to be notified of *automatic*
interaction requests.

To remove call [oneRemoveAutomaticInteractionCallback](one-remove-automatic-interaction-callback.md).

Example:

```
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


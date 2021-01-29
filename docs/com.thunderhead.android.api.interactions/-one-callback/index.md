[Thunderhead](../../index.md) / [com.thunderhead.android.api.interactions](../index.md) / [OneCallback](./index.md)

# OneCallback

`interface OneCallback`

Interface used to handle network request results. A network call can result in three
different states: onSuccess, onError (SDK Error), and onFailure (API error).

### Functions

| Name | Summary |
|---|---|
| [onError](on-error.md) | Called after an SDK error has been encountered.`abstract fun onError(error: `[`OneSDKError`](../../com.thunderhead.android.api.responsetypes/-one-s-d-k-error/index.md)`): Unit` |
| [onFailure](on-failure.md) | Called after an API error has been encountered.`abstract fun onFailure(error: `[`OneAPIError`](../../com.thunderhead.android.api.responsetypes/-one-a-p-i-error/index.md)`): Unit` |
| [onSuccess](on-success.md) | Called after a successfully completed network call.`abstract fun onSuccess(response: `[`OneResponse`](../../com.thunderhead.android.api.responsetypes/-one-response/index.md)`): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [OneAutomaticInteractionCallback](../-one-automatic-interaction-callback/index.md) | Convenience class for building a [OneCallback](./index.md) for [com.thunderhead.One.setAutomaticInteractionCallback](#) using Type Safe Kotlin builders.`class OneAutomaticInteractionCallback : `[`OneCallback`](./index.md) |

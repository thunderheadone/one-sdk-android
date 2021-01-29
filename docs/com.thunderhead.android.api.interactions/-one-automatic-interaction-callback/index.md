[Thunderhead](../../index.md) / [com.thunderhead.android.api.interactions](../index.md) / [OneAutomaticInteractionCallback](./index.md)

# OneAutomaticInteractionCallback

`class OneAutomaticInteractionCallback : `[`OneCallback`](../-one-callback/index.md)

Convenience class for building a [OneCallback](../-one-callback/index.md) for
[com.thunderhead.One.setAutomaticInteractionCallback](#) using Type Safe Kotlin builders.

See [com.thunderhead.android.api.oneSetAutomaticInteractionCallback](../../com.thunderhead.android.api/one-set-automatic-interaction-callback.md)
See [com.thunderhead.One.setAutomaticInteractionCallback](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Convenience class for building a [OneCallback](../-one-callback/index.md) for [com.thunderhead.One.setAutomaticInteractionCallback](#) using Type Safe Kotlin builders.`OneAutomaticInteractionCallback()` |

### Functions

| Name | Summary |
|---|---|
| [onError](on-error.md) | Provide an error handler for *automatic* interaction requests.`fun onError(errorHandler: (`[`OneSDKError`](../../com.thunderhead.android.api.responsetypes/-one-s-d-k-error/index.md)`) -> Unit): Unit`<br>Called after an SDK error has been encountered.`fun onError(error: `[`OneSDKError`](../../com.thunderhead.android.api.responsetypes/-one-s-d-k-error/index.md)`): Unit` |
| [onFailure](on-failure.md) | Provide a failure handler for *automatic* interaction requests.`fun onFailure(failureHandler: (`[`OneAPIError`](../../com.thunderhead.android.api.responsetypes/-one-a-p-i-error/index.md)`) -> Unit): Unit`<br>Called after an API error has been encountered.`fun onFailure(error: `[`OneAPIError`](../../com.thunderhead.android.api.responsetypes/-one-a-p-i-error/index.md)`): Unit` |
| [onSuccess](on-success.md) | Provide a success handler for processing *automatic* interaction requests.`fun onSuccess(successHandler: (`[`OneResponse`](../../com.thunderhead.android.api.responsetypes/-one-response/index.md)`) -> Unit): Unit`<br>Called after a successfully completed network call.`fun onSuccess(response: `[`OneResponse`](../../com.thunderhead.android.api.responsetypes/-one-response/index.md)`): Unit` |

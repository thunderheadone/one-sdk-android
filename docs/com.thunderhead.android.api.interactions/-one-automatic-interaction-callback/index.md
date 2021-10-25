//[thunderhead-sdk](../../../index.md)/[com.thunderhead.android.api.interactions](../index.md)/[OneAutomaticInteractionCallback](index.md)

# OneAutomaticInteractionCallback

[androidJvm]\
class [OneAutomaticInteractionCallback](index.md) : [OneCallback](../-one-callback/index.md)

Convenience class for building a [OneCallback](../-one-callback/index.md) for com.thunderhead.One.setAutomaticInteractionCallback using Type Safe Kotlin builders.

See [com.thunderhead.android.api.oneSetAutomaticInteractionCallback](../../com.thunderhead.android.api/one-set-automatic-interaction-callback.md) See com.thunderhead.One.setAutomaticInteractionCallback

## Constructors

| | |
|---|---|
| [OneAutomaticInteractionCallback](-one-automatic-interaction-callback.md) | [androidJvm]<br>fun [OneAutomaticInteractionCallback](-one-automatic-interaction-callback.md)() |

## Functions

| Name | Summary |
|---|---|
| [onError](on-error.md) | [androidJvm]<br>open override fun [onError](on-error.md)(error: [OneSDKError](../../com.thunderhead.android.api.responsetypes/-one-s-d-k-error/index.md))<br>Called after an SDK error has been encountered.<br>[androidJvm]<br>fun [onError](on-error.md)(errorHandler: ([OneSDKError](../../com.thunderhead.android.api.responsetypes/-one-s-d-k-error/index.md)) -> Unit)<br>Provide an error handler for *automatic* interaction requests. |
| [onFailure](on-failure.md) | [androidJvm]<br>open override fun [onFailure](on-failure.md)(error: [OneAPIError](../../com.thunderhead.android.api.responsetypes/-one-a-p-i-error/index.md))<br>Called after an API error has been encountered.<br>[androidJvm]<br>fun [onFailure](on-failure.md)(failureHandler: ([OneAPIError](../../com.thunderhead.android.api.responsetypes/-one-a-p-i-error/index.md)) -> Unit)<br>Provide a failure handler for *automatic* interaction requests. |
| [onSuccess](on-success.md) | [androidJvm]<br>open override fun [onSuccess](on-success.md)(response: [OneResponse](../../com.thunderhead.android.api.responsetypes/-one-response/index.md))<br>Called after a successfully completed network call.<br>[androidJvm]<br>fun [onSuccess](on-success.md)(successHandler: ([OneResponse](../../com.thunderhead.android.api.responsetypes/-one-response/index.md)) -> Unit)<br>Provide a success handler for processing *automatic* interaction requests. |

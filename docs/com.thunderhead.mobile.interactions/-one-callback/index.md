//[thunderhead-sdk](../../../index.md)/[com.thunderhead.mobile.interactions](../index.md)/[OneCallback](index.md)

# OneCallback

[androidJvm]\
interface [OneCallback](index.md)

Interface used to handle network request results. A network call can result in three different states: onSuccess, onError (SDK Error), and onFailure (API error).

## Functions

| Name | Summary |
|---|---|
| [onError](on-error.md) | [androidJvm]<br>abstract fun [onError](on-error.md)(error: [OneSDKError](../../com.thunderhead.mobile.responsetypes/-one-s-d-k-error/index.md))<br>Called after an SDK error has been encountered. |
| [onFailure](on-failure.md) | [androidJvm]<br>abstract fun [onFailure](on-failure.md)(error: [OneAPIError](../../com.thunderhead.mobile.responsetypes/-one-a-p-i-error/index.md))<br>Called after an API error has been encountered. |
| [onSuccess](on-success.md) | [androidJvm]<br>abstract fun [onSuccess](on-success.md)(response: [OneResponse](../../com.thunderhead.mobile.responsetypes/-one-response/index.md))<br>Called after a successfully completed network call. |

## Inheritors

| Name |
|---|
| [OneAutomaticInteractionCallback](../-one-automatic-interaction-callback/index.md) |

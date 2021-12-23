//[thunderhead-sdk](../../../index.md)/[com.thunderhead.mobile.interactions](../index.md)/[OneAutomaticInteractionCallback](index.md)/[onError](on-error.md)

# onError

[androidJvm]\
fun [onError](on-error.md)(errorHandler: ([OneSDKError](../../com.thunderhead.mobile.responsetypes/-one-s-d-k-error/index.md)) -> Unit)

Provide an error handler for *automatic* interaction requests.

[androidJvm]\
open override fun [onError](on-error.md)(error: [OneSDKError](../../com.thunderhead.mobile.responsetypes/-one-s-d-k-error/index.md))

Called after an SDK error has been encountered.

## Parameters

androidJvm

| | |
|---|---|
| error | SDKError containing the message returned by the sdk error. |

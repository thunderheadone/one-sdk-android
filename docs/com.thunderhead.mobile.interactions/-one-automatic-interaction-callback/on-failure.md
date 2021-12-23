//[thunderhead-sdk](../../../index.md)/[com.thunderhead.mobile.interactions](../index.md)/[OneAutomaticInteractionCallback](index.md)/[onFailure](on-failure.md)

# onFailure

[androidJvm]\
fun [onFailure](on-failure.md)(failureHandler: ([OneAPIError](../../com.thunderhead.mobile.responsetypes/-one-a-p-i-error/index.md)) -> Unit)

Provide a failure handler for *automatic* interaction requests.

[androidJvm]\
open override fun [onFailure](on-failure.md)(error: [OneAPIError](../../com.thunderhead.mobile.responsetypes/-one-a-p-i-error/index.md))

Called after an API error has been encountered.

## Parameters

androidJvm

| | |
|---|---|
| error | APIError containing the error message and response status code. |

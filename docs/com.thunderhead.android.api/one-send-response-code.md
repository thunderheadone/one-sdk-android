//[thunderhead-sdk](../../index.md)/[com.thunderhead.android.api](index.md)/[oneSendResponseCode](one-send-response-code.md)

# oneSendResponseCode

[androidJvm]\
suspend fun [oneSendResponseCode](one-send-response-code.md)(throwErrors: Boolean = false, initializer: [OneResponseCodeRequest.Builder](../com.thunderhead.android.api.interactions/-one-response-code-request/-builder/index.md).() -> Unit): [OneResponse](../com.thunderhead.android.api.responsetypes/-one-response/index.md)?

Send response code to ONE.

## Parameters

androidJvm

| | |
|---|---|
| initializer | [OneResponseCodeRequest.Builder](../com.thunderhead.android.api.interactions/-one-response-code-request/-builder/index.md) initializer to create a [OneResponseCodeRequest](../com.thunderhead.android.api.interactions/-one-response-code-request/index.md). |
| throwErrors | Boolean true to enable throwable errors. When enabled, wrap this method in a try/catch block. |

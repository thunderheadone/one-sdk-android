//[thunderhead-sdk](../../index.md)/[com.thunderhead.android.api](index.md)/[oneSendProperties](one-send-properties.md)

# oneSendProperties

[androidJvm]\
suspend fun [oneSendProperties](one-send-properties.md)(throwErrors: Boolean = false, initializer: [OneRequest.Builder](../com.thunderhead.android.api.interactions/-one-request/-builder/index.md).() -> Unit): [OneResponse](../com.thunderhead.android.api.responsetypes/-one-response/index.md)?

Send properties to ONE.

## Parameters

androidJvm

| | |
|---|---|
| initializer | [OneRequest.Builder](../com.thunderhead.android.api.interactions/-one-request/-builder/index.md) initializer to create a [OneRequest](../com.thunderhead.android.api.interactions/-one-request/index.md). |
| throwErrors | Boolean true to enable throwable errors. When enabled, wrap this method in a try/catch block. |

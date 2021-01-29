[Thunderhead](../index.md) / [com.thunderhead.android.api](index.md) / [oneSendProperties](./one-send-properties.md)

# oneSendProperties

`suspend fun oneSendProperties(throwErrors: Boolean = false, initializer: Builder.() -> Unit): `[`OneResponse`](../com.thunderhead.android.api.responsetypes/-one-response/index.md)`?`

Send properties to ONE.

### Parameters

`initializer` - [OneRequest.Builder](../com.thunderhead.android.api.interactions/-one-request/-builder/index.md) initializer to create a [OneRequest](../com.thunderhead.android.api.interactions/-one-request/index.md).

`throwErrors` - [Boolean](#) true to enable throwable errors. When enabled, wrap this method in a `try/catch` block.
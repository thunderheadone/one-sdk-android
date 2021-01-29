[Thunderhead](../index.md) / [com.thunderhead.android.api](index.md) / [oneSendResponseCode](./one-send-response-code.md)

# oneSendResponseCode

`suspend fun oneSendResponseCode(throwErrors: Boolean = false, initializer: Builder.() -> Unit): `[`OneResponse`](../com.thunderhead.android.api.responsetypes/-one-response/index.md)`?`

Send response code to ONE.

### Parameters

`initializer` - [OneResponseCodeRequest.Builder](../com.thunderhead.android.api.interactions/-one-response-code-request/-builder/index.md) initializer to create a [OneResponseCodeRequest](../com.thunderhead.android.api.interactions/-one-response-code-request/index.md).

`throwErrors` - [Boolean](#) true to enable throwable errors. When enabled, wrap this method in a `try/catch` block.
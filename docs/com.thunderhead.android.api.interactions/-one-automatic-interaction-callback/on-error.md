[Thunderhead](../../index.md) / [com.thunderhead.android.api.interactions](../index.md) / [OneAutomaticInteractionCallback](index.md) / [onError](./on-error.md)

# onError

`fun onError(errorHandler: (`[`OneSDKError`](../../com.thunderhead.android.api.responsetypes/-one-s-d-k-error/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Provide an error handler for *automatic*
interaction requests.

`fun onError(error: `[`OneSDKError`](../../com.thunderhead.android.api.responsetypes/-one-s-d-k-error/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Called after an SDK error has been encountered.

### Parameters

`error` - SDKError containing the message returned
by the sdk error.
[Thunderhead](../../index.md) / [com.thunderhead.android.api.interactions](../index.md) / [OneAutomaticInteractionCallback](index.md) / [onFailure](./on-failure.md)

# onFailure

`fun onFailure(failureHandler: (`[`OneAPIError`](../../com.thunderhead.android.api.responsetypes/-one-a-p-i-error/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Provide a failure handler for *automatic*
interaction requests.

`fun onFailure(error: `[`OneAPIError`](../../com.thunderhead.android.api.responsetypes/-one-a-p-i-error/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Called after an API error has been encountered.

### Parameters

`error` - APIError containing the error message and
response status code.
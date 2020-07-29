[Thunderhead](../../index.md) / [com.thunderhead.android.api.responsetypes](../index.md) / [OneAPIError](./index.md)

# OneAPIError

`class OneAPIError : `[`Exception`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)

Error class used to signify that an API error was encountered.

### Parameters

`httpStatusCode` - API system error code.

`errorMessage` - API error message.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Error class used to signify that an API error was encountered.`OneAPIError(httpStatusCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, errorMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [errorMessage](error-message.md) | API error message.`val errorMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [httpStatusCode](http-status-code.md) | API system error code.`val httpStatusCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

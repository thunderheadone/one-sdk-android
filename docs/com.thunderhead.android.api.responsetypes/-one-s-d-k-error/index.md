[Thunderhead](../../index.md) / [com.thunderhead.android.api.responsetypes](../index.md) / [OneSDKError](./index.md)

# OneSDKError

`class OneSDKError : `[`Exception`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)

Error used to specify that an SDK error was encountered.

### Parameters

`systemCode` - SDK error code.

`errorMessage` - SDK error message

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Error used to specify that an SDK error was encountered.`OneSDKError(systemCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, errorMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [errorMessage](error-message.md) | SDK error message`val errorMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [systemCode](system-code.md) | SDK error code.`val systemCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

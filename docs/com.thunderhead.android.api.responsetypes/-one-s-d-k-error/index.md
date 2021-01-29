[Thunderhead](../../index.md) / [com.thunderhead.android.api.responsetypes](../index.md) / [OneSDKError](./index.md)

# OneSDKError

`class OneSDKError : Exception`

Error used to specify that an SDK error was encountered.

### Parameters

`systemCode` - SDK error code.

`errorMessage` - SDK error message

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Error used to specify that an SDK error was encountered.`OneSDKError(systemCode: Int, errorMessage: String)` |

### Properties

| Name | Summary |
|---|---|
| [errorMessage](error-message.md) | SDK error message`val errorMessage: String` |
| [systemCode](system-code.md) | SDK error code.`val systemCode: Int` |

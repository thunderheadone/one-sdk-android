//[thunderhead-sdk](../../../index.md)/[com.thunderhead.android.api.responsetypes](../index.md)/[OneAPIError](index.md)

# OneAPIError

[androidJvm]\
class [OneAPIError](index.md)(**httpStatusCode**: Int, **errorMessage**: String) : Exception

Error class used to signify that an API error was encountered.

## Parameters

androidJvm

| | |
|---|---|
| httpStatusCode | API system error code. |
| errorMessage | API error message. |

## Constructors

| | |
|---|---|
| [OneAPIError](-one-a-p-i-error.md) | [androidJvm]<br>fun [OneAPIError](-one-a-p-i-error.md)(httpStatusCode: Int, errorMessage: String)<br>API system error code. |

## Properties

| Name | Summary |
|---|---|
| [errorMessage](error-message.md) | [androidJvm]<br>val [errorMessage](error-message.md): String<br>API error message. |
| [httpStatusCode](http-status-code.md) | [androidJvm]<br>val [httpStatusCode](http-status-code.md): Int<br>API system error code. |

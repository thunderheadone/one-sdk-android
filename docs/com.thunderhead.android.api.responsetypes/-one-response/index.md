//[thunderhead-sdk](../../../index.md)/[com.thunderhead.android.api.responsetypes](../index.md)/[OneResponse](index.md)

# OneResponse

[androidJvm]\
class [OneResponse](index.md)(**httpStatusCode**: Int, **tid**: String?, **trackers**: List<Trackers>?, **captures**: List<Captures>?, **optimizations**: List<Optimizations>?, **trace**: String?, **interactionPath**: [OneInteractionPath](../../com.thunderhead.android.api.interactions/-one-interaction-path/index.md)?)

Interaction Response class used to hold data from the interaction API response.

## Parameters

androidJvm

| | |
|---|---|
| httpStatusCode | API response status code. |
| tid | TIP returned from the API. |
| trackers | Array of trackers returned from the API. |
| captures | Array of captures returned from the API. |
| optimizations | Array of optimizations returned from the API. |
| trace | String trace returned from the API. |
| interactionPath | Copy of the interaction path returned from the API response. |

## Constructors

| | |
|---|---|
| [OneResponse](-one-response.md) | [androidJvm]<br>fun [OneResponse](-one-response.md)(httpStatusCode: Int = 0, tid: String? = "", trackers: List<Trackers>? = listOf(), captures: List<Captures>? = listOf(), optimizations: List<Optimizations>? = listOf(), trace: String? = "", interactionPath: [OneInteractionPath](../../com.thunderhead.android.api.interactions/-one-interaction-path/index.md)? = OneInteractionPath(URI.create("")))<br>API response status code. |

## Properties

| Name | Summary |
|---|---|
| [captures](captures.md) | [androidJvm]<br>val [captures](captures.md): List<Captures>?<br>Array of captures returned from the API. |
| [httpStatusCode](http-status-code.md) | [androidJvm]<br>val [httpStatusCode](http-status-code.md): Int = 0<br>API response status code. |
| [interactionPath](interaction-path.md) | [androidJvm]<br>val [interactionPath](interaction-path.md): [OneInteractionPath](../../com.thunderhead.android.api.interactions/-one-interaction-path/index.md)?<br>Copy of the interaction path returned from the API response. |
| [optimizations](optimizations.md) | [androidJvm]<br>val [optimizations](optimizations.md): List<Optimizations>?<br>Array of optimizations returned from the API. |
| [tid](tid.md) | [androidJvm]<br>val [tid](tid.md): String?<br>TIP returned from the API. |
| [trace](trace.md) | [androidJvm]<br>val [trace](trace.md): String?<br>String trace returned from the API. |
| [trackers](trackers.md) | [androidJvm]<br>val [trackers](trackers.md): List<Trackers>?<br>Array of trackers returned from the API. |

## Extensions

| Name | Summary |
|---|---|
| [process](../../com.thunderhead.android.api/process.md) | [androidJvm]<br>fun [OneResponse](index.md)?.[process](../../com.thunderhead.android.api/process.md)(): Unit?<br>Process the response returned from sending an interaction. |

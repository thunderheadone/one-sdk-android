//[thunderhead-sdk](../../../index.md)/[com.thunderhead.mobile.responsetypes](../index.md)/[OneResponse](index.md)

# OneResponse

[androidJvm]\
class [OneResponse](index.md)@JvmOverloads()constructor(**interactionPath**: [OneInteractionPath](../../com.thunderhead.mobile.interactions/-one-interaction-path/index.md), **tid**: String?, **optimizationPoints**: Set<[OptimizationPoint](../-optimization-point/index.md)>, **httpResponseCode**: Int, **optimizations**: List<Optimizations>, **trackers**: List<Trackers>, **captures**: List<Captures>, **trace**: String?)

ONE interaction response data.

## Constructors

| | |
|---|---|
| [OneResponse](-one-response.md) | [androidJvm]<br>@JvmOverloads()<br>fun [OneResponse](-one-response.md)(interactionPath: [OneInteractionPath](../../com.thunderhead.mobile.interactions/-one-interaction-path/index.md) = OneInteractionPath(URI.create("")), tid: String? = "", optimizationPoints: Set<[OptimizationPoint](../-optimization-point/index.md)> = setOf(), httpResponseCode: Int = 200, optimizations: List<Optimizations> = emptyList(), trackers: List<Trackers> = emptyList(), captures: List<Captures> = emptyList(), trace: String? = "") |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: Any?): Boolean |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): Int |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): String |

## Properties

| Name | Summary |
|---|---|
| [interactionPath](interaction-path.md) | [androidJvm]<br>val [interactionPath](interaction-path.md): [OneInteractionPath](../../com.thunderhead.mobile.interactions/-one-interaction-path/index.md)<br>The interaction this response is for. |
| [optimizationPoints](optimization-points.md) | [androidJvm]<br>val [optimizationPoints](optimization-points.md): Set<[OptimizationPoint](../-optimization-point/index.md)><br>A set of [OptimizationPoint](../-optimization-point/index.md)s to optimize content. |
| [success](success.md) | [androidJvm]<br>val [success](success.md): Boolean<br>If the raw HTTP response was successful. |
| [tid](tid.md) | [androidJvm]<br>val [tid](tid.md): String?<br>The unique id for this user's interaction. |

## Extensions

| Name | Summary |
|---|---|
| [process](../../com.thunderhead.mobile/process.md) | [androidJvm]<br>@JvmOverloads()<br>@JvmName(name = processResponse)<br>fun [OneResponse](index.md)?.[process](../../com.thunderhead.mobile/process.md)(throwErrors: Boolean = false)<br>Process the response returned from sending an interaction. |

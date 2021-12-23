//[thunderhead-sdk](../../../index.md)/[com.thunderhead.mobile.responsetypes](../index.md)/[OptimizationPoint](index.md)

# OptimizationPoint

[androidJvm]\
class [OptimizationPoint](index.md)@JvmOverloads()constructor(**data**: String?, **path**: String?, **responseId**: String?, **dataMimeType**: String?, **directives**: String?, **name**: String?, **viewPointName**: String?, **viewPointId**: String?)

Represents a configuration to optimize content for a user.

## Constructors

| | |
|---|---|
| [OptimizationPoint](-optimization-point.md) | [androidJvm]<br>@JvmOverloads()<br>fun [OptimizationPoint](-optimization-point.md)(data: String? = null, path: String? = null, responseId: String? = null, dataMimeType: String? = null, directives: String? = null, name: String? = null, viewPointName: String? = null, viewPointId: String? = null) |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: Any?): Boolean |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): Int |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): String |

## Properties

| Name | Summary |
|---|---|
| [data](data.md) | [androidJvm]<br>val [data](data.md): String? = null<br>The base64 encoded actions and assets. |
| [dataMimeType](data-mime-type.md) | [androidJvm]<br>val [dataMimeType](data-mime-type.md): String? = null<br>The type of asset used in the actions (ex JSON). |
| [directives](directives.md) | [androidJvm]<br>val [directives](directives.md): String? = null<br>Where to place the optimization. |
| [name](name.md) | [androidJvm]<br>val [name](name.md): String? = null<br>The name of the optimization point. |
| [path](path.md) | [androidJvm]<br>val [path](path.md): String? = null<br>The location to optimize. |
| [responseId](response-id.md) | [androidJvm]<br>val [responseId](response-id.md): String? = null<br>The id to send in response to a user interacting with an optimization. |
| [viewPointId](view-point-id.md) | [androidJvm]<br>val [viewPointId](view-point-id.md): String? = null<br>The id of the viewpoint this optimization is for. |
| [viewPointName](view-point-name.md) | [androidJvm]<br>val [viewPointName](view-point-name.md): String? = null<br>The name of the viewpoint this optimization is for. |

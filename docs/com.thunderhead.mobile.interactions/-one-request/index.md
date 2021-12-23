//[thunderhead-sdk](../../../index.md)/[com.thunderhead.mobile.interactions](../index.md)/[OneRequest](index.md)

# OneRequest

[androidJvm]\
class [OneRequest](index.md)

An interaction request object for sending interaction data from the Thunderhead SDK.

Use the [Builder](-builder/index.md) for creating a new instance.

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [androidJvm]<br>class [Builder](-builder/index.md)<br>Builder for creating a [OneRequest](index.md). |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: Any?): Boolean |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): Int |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): String |

## Properties

| Name | Summary |
|---|---|
| [path](path.md) | [androidJvm]<br>val [path](path.md): [OneInteractionPath](../-one-interaction-path/index.md)<br>The URI value containing the interaction path. |
| [properties](properties.md) | [androidJvm]<br>val [properties](properties.md): Map<String, String>?<br>Properties map to be sent with the interaction |

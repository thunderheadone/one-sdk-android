//[thunderhead-sdk](../../../../index.md)/[com.thunderhead.android.api.interactions](../../index.md)/[OneRequest](../index.md)/[Builder](index.md)

# Builder

[androidJvm]\
class [Builder](index.md)

Builder for creating a [OneRequest](../index.md).

## Constructors

| | |
|---|---|
| [Builder](-builder.md) | [androidJvm]<br>fun [Builder](-builder.md)() |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [androidJvm]<br>fun [build](build.md)(): [OneRequest](../index.md)<br>Creates the [OneRequest](../index.md) to be sent to the Thunderhead API. |
| [interactionPath](interaction-path.md) | [androidJvm]<br>fun [interactionPath](interaction-path.md)(interactionPath: [OneInteractionPath](../../-one-interaction-path/index.md)?): [OneRequest.Builder](index.md)<br>Used to set the interaction path. |
| [properties](properties.md) | [androidJvm]<br>fun [properties](properties.md)(properties: Map<String, String>?): [OneRequest.Builder](index.md)<br>Used to set the properties map. |

## Properties

| Name | Summary |
|---|---|
| [interactionPath](interaction-path.md) | [androidJvm]<br>var [interactionPath](interaction-path.md): [OneInteractionPath](../../-one-interaction-path/index.md)<br>the string path for the interaction. |
| [properties](properties.md) | [androidJvm]<br>var [properties](properties.md): Map<String, String><br>The properties map to be sent with the interaction. |

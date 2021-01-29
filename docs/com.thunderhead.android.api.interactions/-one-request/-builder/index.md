[Thunderhead](../../../index.md) / [com.thunderhead.android.api.interactions](../../index.md) / [OneRequest](../index.md) / [Builder](./index.md)

# Builder

`class Builder`

Builder for creating a [OneRequest](../index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Builder for creating a [OneRequest](../index.md).`Builder()` |

### Properties

| Name | Summary |
|---|---|
| [interactionPath](interaction-path.md) | the string path for the interaction.`var interactionPath: `[`OneInteractionPath`](../../-one-interaction-path/index.md) |
| [properties](properties.md) | The properties map to be sent with the interaction.`var properties: Map<String, String>` |

### Functions

| Name | Summary |
|---|---|
| [build](build.md) | Creates the [OneRequest](../index.md) to be sent to the Thunderhead API.`fun build(): `[`OneRequest`](../index.md) |
| [interactionPath](interaction-path.md) | Used to set the interaction path.`fun interactionPath(interactionPath: `[`OneInteractionPath`](../../-one-interaction-path/index.md)`?): Builder` |
| [properties](properties.md) | Used to set the properties map.`fun properties(properties: Map<String, String>?): Builder` |

[Thunderhead](../../../index.md) / [com.thunderhead.android.api.interactions](../../index.md) / [OneResponseCodeRequest](../index.md) / [Builder](./index.md)

# Builder

`class Builder`

Builder for creating a [OneResponseCodeRequest](../index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Builder for creating a [OneResponseCodeRequest](../index.md).`Builder()` |

### Properties

| Name | Summary |
|---|---|
| [interactionPath](interaction-path.md) | The string path for the interaction.`var interactionPath: `[`OneInteractionPath`](../../-one-interaction-path/index.md) |
| [responseCode](response-code.md) | The value of the response code.`var responseCode: `[`OneResponseCode`](../../-one-response-code/index.md) |

### Functions

| Name | Summary |
|---|---|
| [build](build.md) | Creates the [OneResponseCodeRequest](../index.md) to be sent to the Thunderhead API.`fun build(): `[`OneResponseCodeRequest`](../index.md) |
| [interactionPath](interaction-path.md) | Used to set the interaction path.`fun interactionPath(interactionPath: `[`OneInteractionPath`](../../-one-interaction-path/index.md)`?): Builder` |
| [responseCode](response-code.md) | Used to set the response code.`fun responseCode(responseCode: `[`OneResponseCode`](../../-one-response-code/index.md)`?): Builder` |

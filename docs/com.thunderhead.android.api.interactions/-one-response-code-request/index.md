[Thunderhead](../../index.md) / [com.thunderhead.android.api.interactions](../index.md) / [OneResponseCodeRequest](./index.md)

# OneResponseCodeRequest

`class OneResponseCodeRequest`

A response code request object for sending the response code to the Thunderhead SDK.

Use the [Builder](-builder/index.md) for creating a new instance.

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | Builder for creating a [OneResponseCodeRequest](./index.md).`class Builder` |

### Properties

| Name | Summary |
|---|---|
| [interactionPath](interaction-path.md) | The URI value containing the interaction path. This could be only the interaction path, or could also include the schema and host.`val interactionPath: `[`OneInteractionPath`](../-one-interaction-path/index.md) |
| [responseCode](response-code.md) | Response Code object containing the response code value.`val responseCode: `[`OneResponseCode`](../-one-response-code/index.md) |

### Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | `fun equals(other: Any?): Boolean` |
| [hashCode](hash-code.md) | `fun hashCode(): Int` |
| [toString](to-string.md) | `fun toString(): String` |

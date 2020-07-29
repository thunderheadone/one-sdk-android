[Thunderhead](../../index.md) / [com.thunderhead.android.api.interactions](../index.md) / [OneRequest](./index.md)

# OneRequest

`class OneRequest`

An interaction request object for sending interaction data from the Thunderhead SDK.

Use the [Builder](-builder/index.md) for creating a new instance.

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | Builder for creating a [OneRequest](./index.md).`class Builder` |

### Properties

| Name | Summary |
|---|---|
| [path](path.md) | The URI value containing the interaction path. This could be only the interaction path, or could also include the schema and host.`val path: `[`OneInteractionPath`](../-one-interaction-path/index.md) |
| [properties](properties.md) | Properties map to be sent with the interaction`val properties: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>?` |

### Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | `fun equals(other: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | `fun hashCode(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

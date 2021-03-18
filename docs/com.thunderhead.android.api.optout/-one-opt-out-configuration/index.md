[Thunderhead](../../index.md) / [com.thunderhead.android.api.optout](../index.md) / [OneOptOutConfiguration](./index.md)

# OneOptOutConfiguration

`class OneOptOutConfiguration`

Configuration object for opting in and out of tracking.
Privacy compliance method to completely stop tracking a customer's actions.

Use the [Builder](-builder/index.md) to create a new instance.

### Parameters

`optOut` - [Boolean](#) true to opt out of tracking.

`optInOptions` - [Set](#) Set of options that have opt in enabled.

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | Builder to create [OneOptOutConfiguration](./index.md).`class Builder` |

### Properties

| Name | Summary |
|---|---|
| [optInOptions](opt-in-options.md) | [Set](#) Set of options that have opt in enabled.`val optInOptions: Set<`[`OptInOptions`](../-opt-in-options/index.md)`>` |
| [optOut](opt-out.md) | [Boolean](#) true to opt out of tracking.`val optOut: Boolean` |

### Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | `fun equals(other: Any?): Boolean` |
| [hashCode](hash-code.md) | `fun hashCode(): Int` |
| [toString](to-string.md) | `fun toString(): String` |

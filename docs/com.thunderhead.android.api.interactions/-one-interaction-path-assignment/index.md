[Thunderhead](../../index.md) / [com.thunderhead.android.api.interactions](../index.md) / [OneInteractionPathAssignment](./index.md)

# OneInteractionPathAssignment

`class OneInteractionPathAssignment`

Configuration class used to assign a custom Interaction path to
an activity content view or a fragment root view.

Create an instance using the [Builder](-builder/index.md).

See [com.thunderhead.One.assignInteractionPath](#)
See [com.thunderhead.android.api.assignInteractionPath](../../com.thunderhead.android.api/android.view.-view/assign-interaction-path.md)
See [com.thunderhead.android.api.oneInteractionPathAssignment](../../com.thunderhead.android.api/one-interaction-path-assignment.md)

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | Builder to create a [OneInteractionPathAssignment](./index.md).`class Builder` |

### Properties

| Name | Summary |
|---|---|
| [interactionPath](interaction-path.md) | The Interaction path to assign to a given view.`val interactionPath: `[`OneInteractionPath`](../-one-interaction-path/index.md)`?` |
| [view](view.md) | The [View](https://developer.android.com/reference/android/view/View.html) to assign an Interaction path to. This should be the content view of an activity or the root view of a fragment.`val view: `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |

### Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | `fun equals(other: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | `fun hashCode(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

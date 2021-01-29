[Thunderhead](../../index.md) / [com.thunderhead.android.api.logging](../index.md) / [OneLoggingConfiguration](./index.md)

# OneLoggingConfiguration

`class OneLoggingConfiguration`

A logging configuration object for the Thunderhead SDK.

Use the [Builder](-builder/index.md) for creating a new instance.

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | Builder to create a [OneLoggingConfiguration](./index.md).`class Builder` |

### Properties

| Name | Summary |
|---|---|
| [components](components.md) | A `Set` of [Component](../-component/index.md)s. Components are a feature or technical concept such as Networking or Database. Use the [Component.ANY](../-component/-a-n-y.md) component to log messages for all of the SDK in conjunction with the log level.`val components: Set<`[`Component`](../-component/index.md)`>` |
| [levels](levels.md) | A `Set` of [LogLevel](../-log-level/index.md)s. If a singular level is set then the SDK will log messages of that level and above in conjunction with the [components](components.md). The order from bottom up is VERBOSE, DEBUG, ERROR, WARN, INFO, ASSERT. *Example*: Setting VERBOSE will log all messages. *Example*: Setting INFO will log only INFO and ASSERT messages.`val levels: Set<`[`LogLevel`](../-log-level/index.md)`>` |
| [logWriters](log-writers.md) | A `Set` of custom loggers ([LogWriter](../-log-writer/index.md)).`val logWriters: Set<`[`LogWriter`](../-log-writer/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | `fun equals(other: Any?): Boolean` |
| [hashCode](hash-code.md) | `fun hashCode(): Int` |
| [toString](to-string.md) | `fun toString(): String` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [builder](builder.md) | Convenience Java Factory Function to create a [Builder](-builder/index.md).`fun builder(): Builder`<br>Convenience Java Factory Function to create a [Builder](-builder/index.md) that is pre-populated with values from another [OneLoggingConfiguration](./index.md).`fun builder(oneLoggingConfiguration: `[`OneLoggingConfiguration`](./index.md)`?): Builder` |

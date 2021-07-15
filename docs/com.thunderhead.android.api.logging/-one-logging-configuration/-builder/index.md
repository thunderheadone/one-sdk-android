[Thunderhead](../../../index.md) / [com.thunderhead.android.api.logging](../../index.md) / [OneLoggingConfiguration](../index.md) / [Builder](./index.md)

# Builder

`class Builder`

Builder to create a [OneLoggingConfiguration](../index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Builder to create a [OneLoggingConfiguration](../index.md).`Builder()` |

### Properties

| Name | Summary |
|---|---|
| [components](components.md) | A `Set` of [Component](../../-component/index.md)s. Components are a feature or technical concept such as Networking or Database. Use the [Component.ANY](../../-component/-a-n-y.md) component to log messages for all of the SDK in conjunction with the log level.`var components: MutableSet<`[`Component`](../../-component/index.md)`>?` |
| [levels](levels.md) | A `Set` of [LogLevel](../../-log-level/index.md)s. If a singular level is set then the SDK will log messages of that level and above in conjunction with the [components](components.md). The order from bottom up is VERBOSE, DEBUG, ERROR, WARN, INFO, ASSERT. *Example*: Setting VERBOSE will log all messages. *Example*: Setting INFO will log only INFO and ASSERT messages.`var levels: MutableSet<`[`LogLevel`](../../-log-level/index.md)`>?` |
| [logWriters](log-writers.md) | A `Set` of custom loggers ([LogWriter](../../-log-writer/index.md)).`var logWriters: MutableSet<`[`LogWriter`](../../-log-writer/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [build](build.md) | Create a [OneLoggingConfiguration](../index.md) based on builder options.`fun build(): `[`OneLoggingConfiguration`](../index.md) |
| [log](log.md) | Add a desired [Component](../../-component/index.md) to log information about.`fun log(component: `[`Component`](../../-component/index.md)`): Builder`<br>Add a desired [LogLevel](../../-log-level/index.md) to log information about.`fun log(level: `[`LogLevel`](../../-log-level/index.md)`): Builder`<br>Add multiple desired [Component](../../-component/index.md)s to log information about.`fun log(vararg component: `[`Component`](../../-component/index.md)`): Builder`<br>Add multiple desired [LogLevel](../../-log-level/index.md)s to log information about.`fun log(vararg level: `[`LogLevel`](../../-log-level/index.md)`): Builder` |
| [logTo](log-to.md) | Add a custom logger to write logs to.`fun logTo(logWriter: `[`LogWriter`](../../-log-writer/index.md)`): Builder`<br>Add a set of custom loggers to write logs to.`fun logTo(logWriters: Set<`[`LogWriter`](../../-log-writer/index.md)`>): Builder` |

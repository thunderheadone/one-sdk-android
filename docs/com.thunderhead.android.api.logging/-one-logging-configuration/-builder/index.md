//[thunderhead-sdk](../../../../index.md)/[com.thunderhead.android.api.logging](../../index.md)/[OneLoggingConfiguration](../index.md)/[Builder](index.md)

# Builder

[androidJvm]\
class [Builder](index.md)

Builder to create a [OneLoggingConfiguration](../index.md).

## Constructors

| | |
|---|---|
| [Builder](-builder.md) | [androidJvm]<br>fun [Builder](-builder.md)() |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [androidJvm]<br>fun [build](build.md)(): [OneLoggingConfiguration](../index.md)<br>Create a [OneLoggingConfiguration](../index.md) based on builder options. |
| [log](log.md) | [androidJvm]<br>fun [log](log.md)(component: [Component](../../-component/index.md)): [OneLoggingConfiguration.Builder](index.md)<br>Add a desired [Component](../../-component/index.md) to log information about.<br>[androidJvm]<br>fun [log](log.md)(level: [LogLevel](../../-log-level/index.md)): [OneLoggingConfiguration.Builder](index.md)<br>Add a desired [LogLevel](../../-log-level/index.md) to log information about.<br>[androidJvm]<br>fun [log](log.md)(vararg component: [Component](../../-component/index.md)): [OneLoggingConfiguration.Builder](index.md)<br>Add multiple desired [Component](../../-component/index.md)s to log information about.<br>[androidJvm]<br>fun [log](log.md)(vararg level: [LogLevel](../../-log-level/index.md)): [OneLoggingConfiguration.Builder](index.md)<br>Add multiple desired [LogLevel](../../-log-level/index.md)s to log information about. |
| [logTo](log-to.md) | [androidJvm]<br>fun [logTo](log-to.md)(logWriter: [LogWriter](../../-log-writer/index.md)): [OneLoggingConfiguration.Builder](index.md)<br>Add a custom logger to write logs to.<br>[androidJvm]<br>fun [logTo](log-to.md)(logWriters: Set<[LogWriter](../../-log-writer/index.md)>): [OneLoggingConfiguration.Builder](index.md)<br>Add a set of custom loggers to write logs to. |

## Properties

| Name | Summary |
|---|---|
| [components](components.md) | [androidJvm]<br>var [components](components.md): MutableSet<[Component](../../-component/index.md)>? = null<br>A Set of [Component](../../-component/index.md)s. |
| [levels](levels.md) | [androidJvm]<br>var [levels](levels.md): MutableSet<[LogLevel](../../-log-level/index.md)>? = null<br>A Set of [LogLevel](../../-log-level/index.md)s. |
| [logWriters](log-writers.md) | [androidJvm]<br>var [logWriters](log-writers.md): MutableSet<[LogWriter](../../-log-writer/index.md)><br>A Set of custom loggers ([LogWriter](../../-log-writer/index.md)). |

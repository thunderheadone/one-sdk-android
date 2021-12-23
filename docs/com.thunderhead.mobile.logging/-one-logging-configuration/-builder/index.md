//[thunderhead-sdk](../../../../index.md)/[com.thunderhead.mobile.logging](../../index.md)/[OneLoggingConfiguration](../index.md)/[Builder](index.md)

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
| [and](and.md) | [androidJvm]<br>infix inline fun <[T](and.md) : Enum<[T](and.md)>> [T](and.md).[and](and.md)(other: [T](and.md)): EnumSet<[T](and.md)><br>Create an EnumSet from one Enum<T> and another.<br>[androidJvm]<br>infix inline fun <[T](and.md) : Enum<[T](and.md)>> EnumSet<[T](and.md)>.[and](and.md)(other: [T](and.md)): EnumSet<[T](and.md)><br>Create an EnumSet with all the contents of one EnumSet<T> plus another T Ex. |
| [build](build.md) | [androidJvm]<br>fun [build](build.md)(): [OneLoggingConfiguration](../index.md)<br>Create a [OneLoggingConfiguration](../index.md) based on builder options. |
| [log](log.md) | [androidJvm]<br>fun [log](log.md)(component: [OneLogComponent](../../-one-log-component/index.md)): [OneLoggingConfiguration.Builder](index.md)<br>Add a desired [OneLogComponent](../../-one-log-component/index.md) to log information about.<br>[androidJvm]<br>fun [log](log.md)(level: [OneLogLevel](../../-one-log-level/index.md)): [OneLoggingConfiguration.Builder](index.md)<br>Add a desired [OneLogLevel](../../-one-log-level/index.md) to log information about.<br>[androidJvm]<br>fun [log](log.md)(vararg component: [OneLogComponent](../../-one-log-component/index.md)): [OneLoggingConfiguration.Builder](index.md)<br>Add multiple desired [OneLogComponent](../../-one-log-component/index.md)s to log information about.<br>[androidJvm]<br>fun [log](log.md)(vararg level: [OneLogLevel](../../-one-log-level/index.md)): [OneLoggingConfiguration.Builder](index.md)<br>Add multiple desired [OneLogLevel](../../-one-log-level/index.md)s to log information about. |
| [logTo](log-to.md) | [androidJvm]<br>fun [logTo](log-to.md)(logWriter: [OneLogWriter](../../-one-log-writer/index.md)): [OneLoggingConfiguration.Builder](index.md)<br>Add a custom logger to write logs to.<br>[androidJvm]<br>fun [logTo](log-to.md)(logWriters: Set<[OneLogWriter](../../-one-log-writer/index.md)>): [OneLoggingConfiguration.Builder](index.md)<br>Add a set of custom loggers to write logs to. |

## Properties

| Name | Summary |
|---|---|
| [components](components.md) | [androidJvm]<br>var [components](components.md): MutableSet<[OneLogComponent](../../-one-log-component/index.md)>? = null<br>A Set of [OneLogComponent](../../-one-log-component/index.md)s. |
| [levels](levels.md) | [androidJvm]<br>var [levels](levels.md): MutableSet<[OneLogLevel](../../-one-log-level/index.md)>? = null<br>A Set of [OneLogLevel](../../-one-log-level/index.md)s. |
| [logWriters](log-writers.md) | [androidJvm]<br>var [logWriters](log-writers.md): MutableSet<[OneLogWriter](../../-one-log-writer/index.md)><br>A Set of custom loggers ([OneLogWriter](../../-one-log-writer/index.md)). |

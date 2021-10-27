//[thunderhead-sdk](../../../index.md)/[com.thunderhead.android.api.logging](../index.md)/[OneLoggingConfiguration](index.md)

# OneLoggingConfiguration

[androidJvm]\
class [OneLoggingConfiguration](index.md)

A logging configuration object for the Thunderhead SDK.

Use the [Builder](-builder/index.md) for creating a new instance.

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [androidJvm]<br>class [Builder](-builder/index.md)<br>Builder to create a [OneLoggingConfiguration](index.md). |
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: Any?): Boolean |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): Int |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): String |

## Properties

| Name | Summary |
|---|---|
| [components](components.md) | [androidJvm]<br>val [components](components.md): Set<[Component](../-component/index.md)><br>A Set of [Component](../-component/index.md)s. |
| [levels](levels.md) | [androidJvm]<br>val [levels](levels.md): Set<[LogLevel](../-log-level/index.md)><br>A Set of [LogLevel](../-log-level/index.md)s. |
| [logWriters](log-writers.md) | [androidJvm]<br>val [logWriters](log-writers.md): Set<[LogWriter](../-log-writer/index.md)><br>A Set of custom loggers ([LogWriter](../-log-writer/index.md)). |

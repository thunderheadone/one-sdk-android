[Thunderhead](../index.md) / [com.thunderhead.android.api.logging](./index.md)

## Package com.thunderhead.android.api.logging

### Types

| Name | Summary |
|---|---|
| [Component](-component/index.md) | A list of components to filter the logs the Thunderhead SDK will log.`enum class Component` |
| [LogLevel](-log-level/index.md) | The Level (IE severity) of a Thunderhead SDK Log Message.`enum class LogLevel` |
| [LogWriter](-log-writer/index.md) | Required base class to write Thunderhead SDK Logs to a custom destination.`abstract class LogWriter` |
| [OneLoggingConfiguration](-one-logging-configuration/index.md) | A logging configuration object for the Thunderhead SDK.`class OneLoggingConfiguration` |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [java.util.EnumSet](java.util.-enum-set/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [and](and.md) | Create an [EnumSet](https://whatever/java/util/EnumSet.html) from one Enum and another. Ex. val set = Level.WARN and Level.ERROR`infix fun <T : Enum<T>> T.and(other: T): `[`EnumSet`](https://whatever/java/util/EnumSet.html)`<T>` |

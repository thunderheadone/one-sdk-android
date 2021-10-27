//[thunderhead-sdk](../../index.md)/[com.thunderhead.android.api.logging](index.md)

# Package com.thunderhead.android.api.logging

## Types

| Name | Summary |
|---|---|
| [Component](-component/index.md) | [androidJvm]<br>enum [Component](-component/index.md) : Enum<[Component](-component/index.md)> <br>A list of components to filter the logs the Thunderhead SDK will log. |
| [LogLevel](-log-level/index.md) | [androidJvm]<br>enum [LogLevel](-log-level/index.md) : Enum<[LogLevel](-log-level/index.md)> <br>The Level (IE severity) of a Thunderhead SDK Log Message. |
| [LogWriter](-log-writer/index.md) | [androidJvm]<br>abstract class [LogWriter](-log-writer/index.md)<br>Required base class to write Thunderhead SDK Logs to a custom destination. |
| [OneLoggingConfiguration](-one-logging-configuration/index.md) | [androidJvm]<br>class [OneLoggingConfiguration](-one-logging-configuration/index.md)<br>A logging configuration object for the Thunderhead SDK. |

## Functions

| Name | Summary |
|---|---|
| [and](and.md) | [androidJvm]<br>infix inline fun <[T](and.md) : Enum<[T](and.md)>> [T](and.md).[and](and.md)(other: [T](and.md)): EnumSet<[T](and.md)><br>Create an EnumSet from one Enum<T> and another.<br>[androidJvm]<br>infix inline fun <[T](and.md) : Enum<[T](and.md)>> EnumSet<[T](and.md)>.[and](and.md)(other: [T](and.md)): EnumSet<[T](and.md)><br>Create an EnumSet with all the contents of one EnumSet<T> plus another T Ex. |

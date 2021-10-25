//[thunderhead-sdk](../../../index.md)/[com.thunderhead.android.api.logging](../index.md)/[LogWriter](index.md)

# LogWriter

[androidJvm]\
abstract class [LogWriter](index.md)

Required base class to write Thunderhead SDK Logs to a custom destination.

See [OneLoggingConfiguration](../-one-logging-configuration/index.md)

## Constructors

| | |
|---|---|
| [LogWriter](-log-writer.md) | [androidJvm]<br>fun [LogWriter](-log-writer.md)() |

## Functions

| Name | Summary |
|---|---|
| [log](log.md) | [androidJvm]<br>abstract fun [log](log.md)(logLevel: [LogLevel](../-log-level/index.md), component: [Component](../-component/index.md), message: String, throwable: Throwable?)<br>Log messages from the Thunderhead SDK. |

//[thunderhead-sdk](../../../index.md)/[com.thunderhead.mobile.logging](../index.md)/[OneLogWriter](index.md)

# OneLogWriter

[androidJvm]\
abstract class [OneLogWriter](index.md)

Required base class to write Thunderhead SDK Logs to a custom destination.

See [OneLoggingConfiguration](../-one-logging-configuration/index.md)

## Constructors

| | |
|---|---|
| [OneLogWriter](-one-log-writer.md) | [androidJvm]<br>fun [OneLogWriter](-one-log-writer.md)() |

## Functions

| Name | Summary |
|---|---|
| [log](log.md) | [androidJvm]<br>abstract fun [log](log.md)(logLevel: [OneLogLevel](../-one-log-level/index.md), component: [OneLogComponent](../-one-log-component/index.md), message: String, throwable: Throwable?)<br>Log messages from the Thunderhead SDK. |

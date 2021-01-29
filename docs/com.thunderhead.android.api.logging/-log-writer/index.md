[Thunderhead](../../index.md) / [com.thunderhead.android.api.logging](../index.md) / [LogWriter](./index.md)

# LogWriter

`abstract class LogWriter`

Required base class to write Thunderhead SDK Logs
to a custom destination.

See [OneLoggingConfiguration](../-one-logging-configuration/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Required base class to write Thunderhead SDK Logs to a custom destination.`LogWriter()` |

### Functions

| Name | Summary |
|---|---|
| [log](log.md) | Log messages from the Thunderhead SDK.`abstract fun log(logLevel: `[`LogLevel`](../-log-level/index.md)`, component: `[`Component`](../-component/index.md)`, message: String, throwable: Throwable?): Unit` |

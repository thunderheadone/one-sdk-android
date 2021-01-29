[Thunderhead](../../index.md) / [com.thunderhead.android.api.logging](../index.md) / [OneLoggingConfiguration](index.md) / [levels](./levels.md)

# levels

`val levels: Set<`[`LogLevel`](../-log-level/index.md)`>`

A `Set` of [LogLevel](../-log-level/index.md)s. If a singular level is set
then the SDK will log messages of that level and above in conjunction
with the [components](components.md). The order from bottom up is VERBOSE, DEBUG, ERROR, WARN, INFO, ASSERT.
*Example*: Setting VERBOSE will log all messages.
*Example*: Setting INFO will log only INFO and ASSERT messages.

### Property

`levels` - A `Set` of [LogLevel](../-log-level/index.md)s. If a singular level is set
then the SDK will log messages of that level and above in conjunction
with the [components](components.md). The order from bottom up is VERBOSE, DEBUG, ERROR, WARN, INFO, ASSERT.
*Example*: Setting VERBOSE will log all messages.
*Example*: Setting INFO will log only INFO and ASSERT messages.
//[thunderhead-sdk](../../../../index.md)/[com.thunderhead.mobile.logging](../../index.md)/[OneLoggingConfiguration](../index.md)/[Builder](index.md)/[levels](levels.md)

# levels

[androidJvm]\
var [levels](levels.md): MutableSet<[OneLogLevel](../../-one-log-level/index.md)>? = null

A Set of [OneLogLevel](../../-one-log-level/index.md)s. If a singular level is set then the SDK will log messages of that level and above in conjunction with the [components](components.md). The order from bottom up is VERBOSE, DEBUG, ERROR, WARN, INFO, ASSERT. *Example*: Setting VERBOSE will log all messages. *Example*: Setting INFO will log only INFO and ASSERT messages.

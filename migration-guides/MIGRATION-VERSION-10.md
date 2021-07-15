# Upgrading from older versions of the SDK to 10+

Version 10 of the Thunderhead Android SDK introduces breaking public API changes.

## Overview of breaking changes

### Logging Configuration

Logging has been updated to provide a more clear way of ensuring the logging is off when disabled
by the developer. For further details on this [see our readme](https://github.com/thunderheadone/one-sdk-android#configuring-logging)

**Change**:
`LogLevel.NONE` and `Component.NONE` have been added as log configuration options.

**Action Required**
Update all instances where the logger is being disabled to use the new NONE levels.
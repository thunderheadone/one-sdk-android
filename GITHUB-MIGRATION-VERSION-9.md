# Upgrading from older versions of the SDK to 9+

Version 9 of the Thunderhead Android SDK introduces breaking public API changes
and has been fully migrated to AndroidX thus dropping support for the Support Libraries.

In addition the Push Notification has been removed. This requires removing calls to removed
Thunderhead SDK APIs.

## Overview of breaking changes

### AndroidX

AndroidX libraries cannot be used in conjunction with Support Libraries. If your project
is still dependent on the Support Libraries please follow the Android Instructions
for using newer AndroidX libraries found [here](https://developer.android.com/jetpack/androidx#using_androidx_libraries_in_your_project)
in order to use the newest version of the Thunderhead Android SDK.

### Codeless Updates

Updates to codeless features requires that all projects use the latest version of the Thunderhead
Orchestration Plugin. At the time of this writing the version required is 3.0.0.

For further documentation on the `orchestration-plugin` please see the 
[Orchestration Plugin Readme](https://github.com/thunderheadone/one-android-orchestration-plugin/blob/master/README.md).

## Below is a list of API migrations to follow when upgrading from older versions of the SDK to 9+

### Logging Configuration

Logging has been reworked to provide a rich extensible and filterable API following the conventions
of current Thunderhead API conventions.  More info on the APIs can be found 
[here](https://thunderheadone.github.io/one-sdk-android/).

**Change**:
`One.setLogLevel(OneLogLevel)` has been removed.

**Action Required**
Remove all calls to `One.setLogLevel(OneLogLevel)` and use the new logging APIs.
See the [documentation](README.md#configuring-logging) for more details.

### Push Notifications
**Change**:
`One.setMessagingToken(newToken)` has been removed.

**Action Required**
Remove all calls to `One.setMessagingToken(newToken)`.

**Change**:
`One.processMessage(message)` has been removed.

**Action Required**
Remove all calls to `One.processMessage(message)`.

**Change**:
`oneConfigureMessaging(builder)` has been removed.

**Action Required**
Remove all calls to `oneConfigureMessaging(builder)`.

**Change**:
`One.setMessagingConfiguration(oneMessagingConfiguration)` has been removed.

**Action Required**
Remove all calls to `One.setMessagingConfiguration(oneMessagingConfiguration)`.

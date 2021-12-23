# Upgrading from older versions of the SDK to 11.1+

Version 11.1 of the Thunderhead Android SDK deprecates public APIs.

## Overview of changes

### Public Api Package Move

The public APIs have been moved into `com.thunderhead.mobile` and the `com.thunderhead.One` class has
been deprecated and will be removed in a future release.

**Change**:
`com.thunderhead.One` has been deprecated.

**Action Required**
Update all Java usages of `com.thunderhead.One` to use the new API `com.thunderhead.mobile.One`.
Update all Kotlin usages of `com.thunderhead.One` to use the new Top Level declarations found in the
`com.thunderhead.mobile` package.

**Change**:
Function `com.thunderhead.android.api.oneConfigureCodelessInteractionTracking` has been deprecated.

**Action Required**
Update all Kotlin usages of function `com.thunderhead.android.api.oneConfigureCodelessInteractionTracking`
to instead use function `com.thunderhead.mobile.oneConfigureCodelessInteractionTracking`.

**Change**:
Function `com.thunderhead.android.api.oneCodelessInteractionTrackingConfiguration` has been deprecated.

**Action Required**
Update all Kotlin usages of function `com.thunderhead.android.api.oneCodelessInteractionTrackingConfiguration`
to instead use function `com.thunderhead.mobile.oneCodelessInteractionTrackingConfiguration`.

**Change**:
Function `com.thunderhead.android.api.oneGetCodelessInteractionTrackingConfiguration` has been deprecated.

**Action Required**
Update all Kotlin usages of function `com.thunderhead.android.api.oneGetCodelessInteractionTrackingConfiguration`
to instead use property `com.thunderhead.mobile.oneCodelessInteractionTrackingConfiguration`.

**Change**:
Class `com.thunderhead.android.api.codeless.OneCodelessInteractionTrackingConfiguration` has been deprecated.

**Action Required**
Update all usages of class `com.thunderhead.android.api.codeless.OneCodelessInteractionTrackingConfiguration`
to instead use class `com.thunderhead.mobile.codeless.OneCodelessInteractionTrackingConfiguration`.

**Change**:
Function `com.thunderhead.android.api.oneIdentityTransferConfiguration` has been deprecated.

**Action Required**
Update all Kotlin usages of function `com.thunderhead.android.api.oneIdentityTransferConfiguration`
to instead use function `com.thunderhead.mobile.oneIdentityTransferConfiguration`.

**Change**:
Function `com.thunderhead.android.api.oneConfigureIdentityTransfer` has been deprecated.

**Action Required**
Update all Kotlin usages of function `com.thunderhead.android.api.oneConfigureIdentityTransfer`
to instead use function `com.thunderhead.mobile.oneConfigureIdentityTransfer`.

**Change**:
Function `com.thunderhead.android.api.oneGetIdentityTransferConfiguration` has been deprecated.

**Action Required**
Update all Kotlin usages of function `com.thunderhead.android.api.oneGetIdentityTransferConfiguration`
to instead use property `com.thunderhead.mobile.oneIdentityTransferConfiguration`.

**Change**:
Class `com.thunderhead.android.api.identitytransfer.OneIdentityTransferConfiguration` has been deprecated.

**Action Required**
Update all usages of class `com.thunderhead.android.api.identitytransfer.OneIdentityTransferConfiguration`
to instead use class `com.thunderhead.mobile.identitytransfer.OneIdentityTransferConfiguration`.

**Change**
Extension function `com.thunderhead.android.api.processDeepLink` has been deprecated.

**Action Required**
Update all usages of extension function `com.thunderhead.android.api.processDeepLink`
to instead use `com.thunderhead.mobile.processDeepLink`.

**Change**
Extension function `com.thunderhead.android.api.createUriWithTid` has been deprecated.

**Action Required**
Update all usages of extension function `com.thunderhead.android.api.createUriWithTid`
to instead use `com.thunderhead.mobile.generateIdentityTransferUri`.

**Change**
Extension function `com.thunderhead.android.api.createUrlWithTid` has been deprecated.

**Action Required**
Update all usages of extension function `com.thunderhead.android.api.createUrlWithTid`
to instead use `com.thunderhead.mobile.generateIdentityTransferUrl`.

**Change**
Extension function `com.thunderhead.android.api.setIdentityTransferLinksWhiteList` and
top level function `com.thunderhead.android.api.oneGetIdentityTransferLinksWhiteList` have been deprecated.

**Action Required**
Update all usages of `com.thunderhead.android.api.setIdentityTransferLinksWhiteList` and
`com.thunderhead.android.api.oneGetIdentityTransferLinksWhiteList`
to instead use the top level property `com.thunderhead.mobile.oneIdentityTransferIncludeList`.

**Change**
Extension function `com.thunderhead.android.api.setIdentityTransferLinksBlackList` and
top level function `com.thunderhead.android.api.oneGetIdentityTransferLinksBlackList` have been deprecated.

**Action Required**
Update all usages of `com.thunderhead.android.api.setIdentityTransferLinksWhiteList` and
`com.thunderhead.android.api.oneGetIdentityTransferLinksWhiteList`
to instead use the top level property `com.thunderhead.mobile.oneIdentityTransferExcludeList`.

**Change**
Extension functions `com.thunderhead.android.api.removeAutomaticInteractionCallback` and
`com.thunderhead.android.api.setAutomaticInteractionCallback` have been deprecated.

**Action Required**
Update all usages of `com.thunderhead.android.api.removeAutomaticInteractionCallback` and
`com.thunderhead.android.api.setAutomaticInteractionCallback`
to instead use `com.thunderhead.mobile.removeAutomaticInteractionCallback` and
`com.thunderhead.mobile.setAutomaticInteractionCallback`.

**Change**
Functions `com.thunderhead.android.api.oneSendInteraction` and
`com.thunderhead.android.api.oneSendProperties` and
 `com.thunderhead.android.api.oneSendResponseCode` have been deprecated.

**Action Required**
Update all usages of `com.thunderhead.android.api.oneSendInteraction` and
                     `com.thunderhead.android.api.oneSendProperties` and
                      `com.thunderhead.android.api.oneSendResponseCode`
to instead use `com.thunderhead.mobile.oneSendInteraction` and
`com.thunderhead.mobile.oneSendProperties` and
`com.thunderhead.mobile.oneSendResponseCode`.

**Change**
Functions `com.thunderhead.android.api.oneConfiguration` and
`com.thunderhead.android.api.oneConfigure` and
 `com.thunderhead.android.api.oneGetConfiguration` have been deprecated.

**Action Required**
Update all usages of `com.thunderhead.android.api.oneConfiguration` and
                     `com.thunderhead.android.api.oneConfigure` and
                      `com.thunderhead.android.api.oneGetConfiguration`
to instead use `com.thunderhead.mobile.oneConfiguration` and
`com.thunderhead.mobile.oneConfigure` and
`com.thunderhead.mobile.oneConfiguration`.

**Change**
Function `com.thunderhead.android.api.oneConfigureOptOut` has been deprecated.

**Action Required**
Update all usages of `com.thunderhead.android.api.oneConfigureOptOut`
to instead use `com.thunderhead.mobile.oneConfigureOptOut`.
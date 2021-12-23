//[thunderhead-sdk](../../../../index.md)/[com.thunderhead.mobile.identitytransfer](../../index.md)/[OneIdentityTransferUriMatcher](../index.md)/[ExactMatch](index.md)

# ExactMatch

[androidJvm]\
data class [ExactMatch](index.md)(**value**: URI) : [OneIdentityTransferUriMatcher](../index.md)

If used in either [com.thunderhead.mobile.oneIdentityTransferIncludeList](../../../com.thunderhead.mobile/one-identity-transfer-include-list.md) or [com.thunderhead.mobile.oneIdentityTransferExcludeList](../../../com.thunderhead.mobile/one-identity-transfer-exclude-list.md), then only exact values (as strings) will be compared.

## Constructors

| | |
|---|---|
| [ExactMatch](-exact-match.md) | [androidJvm]<br>fun [ExactMatch](-exact-match.md)(value: URI) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): URI<br>The exact URI to match. |

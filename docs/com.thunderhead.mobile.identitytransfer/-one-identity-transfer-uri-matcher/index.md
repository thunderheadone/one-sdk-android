//[thunderhead-sdk](../../../index.md)/[com.thunderhead.mobile.identitytransfer](../index.md)/[OneIdentityTransferUriMatcher](index.md)

# OneIdentityTransferUriMatcher

[androidJvm]\
sealed class [OneIdentityTransferUriMatcher](index.md)

Represents the different ways the Thunderhead SDK will attempt to include or exclude URIs from having a one-tid query param and value appended, IE identity transfer.

See [com.thunderhead.mobile.oneIdentityTransferIncludeList](../../com.thunderhead.mobile/one-identity-transfer-include-list.md) and [com.thunderhead.mobile.oneIdentityTransferExcludeList](../../com.thunderhead.mobile/one-identity-transfer-exclude-list.md).

## Types

| Name | Summary |
|---|---|
| [ExactMatch](-exact-match/index.md) | [androidJvm]<br>data class [ExactMatch](-exact-match/index.md)(**value**: URI) : [OneIdentityTransferUriMatcher](index.md)<br>If used in either [com.thunderhead.mobile.oneIdentityTransferIncludeList](../../com.thunderhead.mobile/one-identity-transfer-include-list.md) or [com.thunderhead.mobile.oneIdentityTransferExcludeList](../../com.thunderhead.mobile/one-identity-transfer-exclude-list.md), then only exact values (as strings) will be compared. |
| [RegexMatch](-regex-match/index.md) | [androidJvm]<br>data class [RegexMatch](-regex-match/index.md)(**value**: Regex) : [OneIdentityTransferUriMatcher](index.md)<br>If used in either [com.thunderhead.mobile.oneIdentityTransferIncludeList](../../com.thunderhead.mobile/one-identity-transfer-include-list.md) or [com.thunderhead.mobile.oneIdentityTransferExcludeList](../../com.thunderhead.mobile/one-identity-transfer-exclude-list.md), then the URI values will be matched to the regex provided. |

## Inheritors

| Name |
|---|
| [OneIdentityTransferUriMatcher](-exact-match/index.md) |
| [OneIdentityTransferUriMatcher](-regex-match/index.md) |

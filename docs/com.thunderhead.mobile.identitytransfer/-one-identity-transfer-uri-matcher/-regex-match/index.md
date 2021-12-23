//[thunderhead-sdk](../../../../index.md)/[com.thunderhead.mobile.identitytransfer](../../index.md)/[OneIdentityTransferUriMatcher](../index.md)/[RegexMatch](index.md)

# RegexMatch

[androidJvm]\
data class [RegexMatch](index.md)(**value**: Regex) : [OneIdentityTransferUriMatcher](../index.md)

If used in either [com.thunderhead.mobile.oneIdentityTransferIncludeList](../../../com.thunderhead.mobile/one-identity-transfer-include-list.md) or [com.thunderhead.mobile.oneIdentityTransferExcludeList](../../../com.thunderhead.mobile/one-identity-transfer-exclude-list.md), then the URI values will be matched to the regex provided.

## Constructors

| | |
|---|---|
| [RegexMatch](-regex-match.md) | [androidJvm]<br>fun [RegexMatch](-regex-match.md)(value: Regex) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): Regex<br>The regex to match against the string representation of the URI by calling URI.toString. |

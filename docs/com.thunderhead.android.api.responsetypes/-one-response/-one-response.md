//[thunderhead-sdk](../../../index.md)/[com.thunderhead.android.api.responsetypes](../index.md)/[OneResponse](index.md)/[OneResponse](-one-response.md)

# OneResponse

[androidJvm]\
fun [OneResponse](-one-response.md)(httpStatusCode: Int = 0, tid: String? = "", trackers: List<Trackers>? = listOf(), captures: List<Captures>? = listOf(), optimizations: List<Optimizations>? = listOf(), trace: String? = "", interactionPath: [OneInteractionPath](../../com.thunderhead.android.api.interactions/-one-interaction-path/index.md)? = OneInteractionPath(URI.create("")))

## Parameters

androidJvm

| | |
|---|---|
| httpStatusCode | API response status code. |
| tid | TIP returned from the API. |
| trackers | Array of trackers returned from the API. |
| captures | Array of captures returned from the API. |
| optimizations | Array of optimizations returned from the API. |
| trace | String trace returned from the API. |
| interactionPath | Copy of the interaction path returned from the API response. |

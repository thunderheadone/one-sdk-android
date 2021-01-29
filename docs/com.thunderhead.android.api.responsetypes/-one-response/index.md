[Thunderhead](../../index.md) / [com.thunderhead.android.api.responsetypes](../index.md) / [OneResponse](./index.md)

# OneResponse

`class OneResponse`

Interaction Response class used to hold data from the interaction API response.

### Parameters

`httpStatusCode` - API response status code.

`tid` - TIP returned from the API.

`trackers` - Array of trackers returned from the API.

`captures` - Array of captures returned from the API.

`optimizations` - Array of optimizations returned from the API.

`trace` - String trace returned from the API.

`interactionPath` - Copy of the interaction path returned from the API response.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Interaction Response class used to hold data from the interaction API response.`OneResponse(httpStatusCode: Int = 0, tid: String? = "", trackers: List<Trackers>? = listOf(), captures: List<Captures>? = listOf(), optimizations: List<Optimizations>? = listOf(), trace: String? = "", interactionPath: `[`OneInteractionPath`](../../com.thunderhead.android.api.interactions/-one-interaction-path/index.md)`? = OneInteractionPath(URI.create("")))` |

### Properties

| Name | Summary |
|---|---|
| [captures](captures.md) | Array of captures returned from the API.`val captures: List<Captures>?` |
| [httpStatusCode](http-status-code.md) | API response status code.`val httpStatusCode: Int` |
| [interactionPath](interaction-path.md) | Copy of the interaction path returned from the API response.`val interactionPath: `[`OneInteractionPath`](../../com.thunderhead.android.api.interactions/-one-interaction-path/index.md)`?` |
| [optimizations](optimizations.md) | Array of optimizations returned from the API.`val optimizations: List<Optimizations>?` |
| [tid](tid.md) | TIP returned from the API.`val tid: String?` |
| [trace](trace.md) | String trace returned from the API.`val trace: String?` |
| [trackers](trackers.md) | Array of trackers returned from the API.`val trackers: List<Trackers>?` |

### Extension Functions

| Name | Summary |
|---|---|
| [process](../../com.thunderhead.android.api/process.md) | Process the response returned from sending an interaction.`fun `[`OneResponse`](./index.md)`?.process(): Unit?` |

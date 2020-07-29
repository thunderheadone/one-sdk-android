[Thunderhead](../../index.md) / [com.thunderhead.android.api.responsetypes](../index.md) / [OneResponse](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`OneResponse(httpStatusCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, tid: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "", trackers: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Trackers>? = listOf(), captures: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Captures>? = listOf(), optimizations: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Optimizations>? = listOf(), trace: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "", interactionPath: `[`OneInteractionPath`](../../com.thunderhead.android.api.interactions/-one-interaction-path/index.md)`? = OneInteractionPath(URI.create("")))`

Interaction Response class used to hold data from the interaction API response.

### Parameters

`httpStatusCode` - API response status code.

`tid` - TIP returned from the API.

`trackers` - Array of trackers returned from the API.

`captures` - Array of captures returned from the API.

`optimizations` - Array of optimizations returned from the API.

`trace` - String trace returned from the API.

`interactionPath` - Copy of the interaction path returned from the API response.
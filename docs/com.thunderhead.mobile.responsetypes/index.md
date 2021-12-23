//[thunderhead-sdk](../../index.md)/[com.thunderhead.mobile.responsetypes](index.md)

# Package com.thunderhead.mobile.responsetypes

## Types

| Name | Summary |
|---|---|
| [OneAPIError](-one-a-p-i-error/index.md) | [androidJvm]<br>class [OneAPIError](-one-a-p-i-error/index.md)(**httpStatusCode**: Int, **errorMessage**: String) : RuntimeException<br>Error class used to signify that an API error was encountered. |
| [OneResponse](-one-response/index.md) | [androidJvm]<br>class [OneResponse](-one-response/index.md)@JvmOverloads()constructor(**interactionPath**: [OneInteractionPath](../com.thunderhead.mobile.interactions/-one-interaction-path/index.md), **tid**: String?, **optimizationPoints**: Set<[OptimizationPoint](-optimization-point/index.md)>, **httpResponseCode**: Int, **optimizations**: List<Optimizations>, **trackers**: List<Trackers>, **captures**: List<Captures>, **trace**: String?)<br>ONE interaction response data. |
| [OneSDKError](-one-s-d-k-error/index.md) | [androidJvm]<br>class [OneSDKError](-one-s-d-k-error/index.md)(**systemCode**: Int, **errorMessage**: String) : RuntimeException<br>Error used to specify that an SDK error was encountered. |
| [OptimizationPoint](-optimization-point/index.md) | [androidJvm]<br>class [OptimizationPoint](-optimization-point/index.md)@JvmOverloads()constructor(**data**: String?, **path**: String?, **responseId**: String?, **dataMimeType**: String?, **directives**: String?, **name**: String?, **viewPointName**: String?, **viewPointId**: String?)<br>Represents a configuration to optimize content for a user. |

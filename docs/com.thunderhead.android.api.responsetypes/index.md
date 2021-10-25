//[thunderhead-sdk](../../index.md)/[com.thunderhead.android.api.responsetypes](index.md)

# Package com.thunderhead.android.api.responsetypes

## Types

| Name | Summary |
|---|---|
| [OneAPIError](-one-a-p-i-error/index.md) | [androidJvm]<br>class [OneAPIError](-one-a-p-i-error/index.md)(**httpStatusCode**: Int, **errorMessage**: String) : Exception<br>Error class used to signify that an API error was encountered. |
| [OneResponse](-one-response/index.md) | [androidJvm]<br>class [OneResponse](-one-response/index.md)(**httpStatusCode**: Int, **tid**: String?, **trackers**: List<Trackers>?, **captures**: List<Captures>?, **optimizations**: List<Optimizations>?, **trace**: String?, **interactionPath**: [OneInteractionPath](../com.thunderhead.android.api.interactions/-one-interaction-path/index.md)?)<br>Interaction Response class used to hold data from the interaction API response. |
| [OneSDKError](-one-s-d-k-error/index.md) | [androidJvm]<br>class [OneSDKError](-one-s-d-k-error/index.md)(**systemCode**: Int, **errorMessage**: String) : Exception<br>Error used to specify that an SDK error was encountered. |

[Thunderhead](../index.md) / [com.thunderhead.android.api.interactions](./index.md)

## Package com.thunderhead.android.api.interactions

### Types

| Name | Summary |
|---|---|
| [OneAutomaticInteractionCallback](-one-automatic-interaction-callback/index.md) | Convenience class for building a [OneCallback](-one-callback/index.md) for [com.thunderhead.One.setAutomaticInteractionCallback](#) using Type Safe Kotlin builders.`class OneAutomaticInteractionCallback : `[`OneCallback`](-one-callback/index.md) |
| [OneAutomaticInteractionExclusion](-one-automatic-interaction-exclusion/index.md) | A class to configure an exclusion of an Automatic Interaction.`class OneAutomaticInteractionExclusion` |
| [OneCall](-one-call/index.md) | Interface for executing an interaction API call.`interface OneCall` |
| [OneCallback](-one-callback/index.md) | Interface used to handle network request results. A network call can result in three different states: onSuccess, onError (SDK Error), and onFailure (API error).`interface OneCallback` |
| [OneInteractionPath](-one-interaction-path/index.md) | Represents a ONE interaction path.`data class OneInteractionPath` |
| [OneInteractionPathAssignment](-one-interaction-path-assignment/index.md) | Configuration class used to assign a custom Interaction path to an activity content view or a fragment root view.`class OneInteractionPathAssignment` |
| [OneRequest](-one-request/index.md) | An interaction request object for sending interaction data from the Thunderhead SDK.`class OneRequest` |
| [OneResponseCode](-one-response-code/index.md) | Response code object containing the response code value. This is used as part of the [OneResponseCodeRequest](-one-response-code-request/index.md) object. Response code values is sent to the One API as a property value.`data class OneResponseCode` |
| [OneResponseCodeRequest](-one-response-code-request/index.md) | A response code request object for sending the response code to the Thunderhead SDK.`class OneResponseCodeRequest` |

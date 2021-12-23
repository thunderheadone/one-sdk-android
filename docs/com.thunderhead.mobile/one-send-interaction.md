//[thunderhead-sdk](../../index.md)/[com.thunderhead.mobile](index.md)/[oneSendInteraction](one-send-interaction.md)

# oneSendInteraction

[androidJvm]\
inline suspend fun [oneSendInteraction](one-send-interaction.md)(throwErrors: Boolean = false, initializer: [OneRequest.Builder](../com.thunderhead.mobile.interactions/-one-request/-builder/index.md).() -> Unit = { }): [OneResponse](../com.thunderhead.mobile.responsetypes/-one-response/index.md)?

Send interaction to the Thunderhead API.

#### Return

response [OneResponse](../com.thunderhead.mobile.responsetypes/-one-response/index.md) result of request

## Parameters

androidJvm

| | |
|---|---|
| initializer | [OneRequest.Builder](../com.thunderhead.mobile.interactions/-one-request/-builder/index.md) initializer to create a [OneRequest](../com.thunderhead.mobile.interactions/-one-request/index.md). |
| throwErrors | Boolean true to enable throwable errors. When enabled, wrap this method in a try/catch block. |

#### Throws

| | |
|---|---|
| [com.thunderhead.mobile.responsetypes.OneAPIError](../com.thunderhead.mobile.responsetypes/-one-a-p-i-error/index.md) | or [OneSDKError](../com.thunderhead.mobile.responsetypes/-one-s-d-k-error/index.md) if [throwErrors](one-send-interaction.md) == true. |

[androidJvm]\
suspend fun [oneSendInteraction](one-send-interaction.md)(throwErrors: Boolean = false, oneRequest: [OneRequest](../com.thunderhead.mobile.interactions/-one-request/index.md)): [OneResponse](../com.thunderhead.mobile.responsetypes/-one-response/index.md)?

Send interaction to the Thunderhead API.

#### Return

response [OneResponse](../com.thunderhead.mobile.responsetypes/-one-response/index.md) result of request

## Parameters

androidJvm

| | |
|---|---|
| oneRequest | [OneRequest](../com.thunderhead.mobile.interactions/-one-request/index.md) to send. |
| throwErrors | Boolean true to enable throwable errors. When enabled, wrap this method in a try/catch block. |

#### Throws

| | |
|---|---|
| [com.thunderhead.mobile.responsetypes.OneAPIError](../com.thunderhead.mobile.responsetypes/-one-a-p-i-error/index.md) | or [OneSDKError](../com.thunderhead.mobile.responsetypes/-one-s-d-k-error/index.md) if [throwErrors](one-send-interaction.md) == true. |

[androidJvm]\

@JvmOverloads()

@JvmName(name = sendInteraction)

@RequiresApi(value = 24)

fun [oneSendInteraction](one-send-interaction.md)(throwErrors: Boolean = false, request: [OneRequest](../com.thunderhead.mobile.interactions/-one-request/index.md)?): CompletableFuture<[OneResponse](../com.thunderhead.mobile.responsetypes/-one-response/index.md)?>

Java api to send interactions. Please use [oneSendInteraction](one-send-interaction.md) for Kotlin.

Will throw either [com.thunderhead.mobile.responsetypes.OneAPIError](../com.thunderhead.mobile.responsetypes/-one-a-p-i-error/index.md) or [com.thunderhead.mobile.responsetypes.OneSDKError](../com.thunderhead.mobile.responsetypes/-one-s-d-k-error/index.md) if [throwErrors](one-send-interaction.md) is true.

To inspect the error, use a try/catch block and catch an java.util.concurrent.ExecutionException and retrieve the cause as demonstrated in the following example using CompletableFuture.get:

try {\
  One.sendInteraction(true, oneRequest).get(5000, TimeUnit.SECONDS);\
} catch (ExecutionException error) {\
    Log.e(TAG, error.getCause());\
} catch(OneSDKError error) {\
    Log.e(TAG, error.getErrorMessage());\
} catch(OneAPIError error) {\
    Log.e(TAG, error.getErrorMessage());\
}

Or alternatively to wait indefinitely:

To inspect the error, use a try/catch block and catch an java.util.concurrent.CompletionException and retrieve the cause as demonstrated in the following example using CompletableFuture.join:

try {\
  One.sendInteraction(true, oneRequest).join();\
} catch (CompletionException error) {\
    Log.e(TAG, error.getCause());\
} catch(OneSDKError error) {\
    Log.e(TAG, error.getErrorMessage());\
} catch(OneAPIError error) {\
    Log.e(TAG, error.getErrorMessage());\
}

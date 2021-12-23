//[thunderhead-sdk](../../index.md)/[com.thunderhead.mobile](index.md)/[oneSendProperties](one-send-properties.md)

# oneSendProperties

[androidJvm]\
inline suspend fun [oneSendProperties](one-send-properties.md)(throwErrors: Boolean = false, initializer: [OneRequest.Builder](../com.thunderhead.mobile.interactions/-one-request/-builder/index.md).() -> Unit = { }): [OneResponse](../com.thunderhead.mobile.responsetypes/-one-response/index.md)?

Send properties to ONE.

## Parameters

androidJvm

| | |
|---|---|
| initializer | [OneRequest.Builder](../com.thunderhead.mobile.interactions/-one-request/-builder/index.md) initializer to create a [OneRequest](../com.thunderhead.mobile.interactions/-one-request/index.md). |
| throwErrors | Boolean true to enable throwable errors. When enabled, wrap this method in a try/catch block. |

[androidJvm]\
suspend fun [oneSendProperties](one-send-properties.md)(throwErrors: Boolean = false, oneRequest: [OneRequest](../com.thunderhead.mobile.interactions/-one-request/index.md)): [OneResponse](../com.thunderhead.mobile.responsetypes/-one-response/index.md)?

Send properties to ONE.

## Parameters

androidJvm

| | |
|---|---|
| oneRequest | [OneRequest](../com.thunderhead.mobile.interactions/-one-request/index.md) to send. |
| throwErrors | Boolean true to enable throwable errors. When enabled, wrap this method in a try/catch block. |

[androidJvm]\

@JvmOverloads()

@JvmName(name = sendProperties)

@RequiresApi(value = 24)

fun [oneSendProperties](one-send-properties.md)(throwErrors: Boolean = false, request: [OneRequest](../com.thunderhead.mobile.interactions/-one-request/index.md)?): CompletableFuture<[OneResponse](../com.thunderhead.mobile.responsetypes/-one-response/index.md)?>

Java api to send properties. Please use [oneSendProperties](one-send-properties.md) for kotlin. Will throw either [com.thunderhead.mobile.responsetypes.OneAPIError](../com.thunderhead.mobile.responsetypes/-one-a-p-i-error/index.md) or [com.thunderhead.mobile.responsetypes.OneSDKError](../com.thunderhead.mobile.responsetypes/-one-s-d-k-error/index.md) if [throwErrors](one-send-properties.md) is true.

To inspect the error, use a try/catch block and catch an java.util.concurrent.ExecutionException and retrieve the cause as demonstrated in the following example using CompletableFuture.get:

try {\
  One.sendProperties(true, oneRequest).get(5000, TimeUnit.SECONDS);\
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
  One.sendProperties(true, oneRequest).join();\
} catch (CompletionException error) {\
    Log.e(TAG, error.getCause());\
} catch(OneSDKError error) {\
    Log.e(TAG, error.getErrorMessage());\
} catch(OneAPIError error) {\
    Log.e(TAG, error.getErrorMessage());\
}

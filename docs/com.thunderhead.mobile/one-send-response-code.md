//[thunderhead-sdk](../../index.md)/[com.thunderhead.mobile](index.md)/[oneSendResponseCode](one-send-response-code.md)

# oneSendResponseCode

[androidJvm]\
inline suspend fun [oneSendResponseCode](one-send-response-code.md)(throwErrors: Boolean = false, initializer: [OneResponseCodeRequest.Builder](../com.thunderhead.mobile.interactions/-one-response-code-request/-builder/index.md).() -> Unit = { }): [OneResponse](../com.thunderhead.mobile.responsetypes/-one-response/index.md)?

Send response code to ONE.

## Parameters

androidJvm

| | |
|---|---|
| initializer | [OneResponseCodeRequest.Builder](../com.thunderhead.mobile.interactions/-one-response-code-request/-builder/index.md) initializer to create a [OneResponseCodeRequest](../com.thunderhead.mobile.interactions/-one-response-code-request/index.md). |
| throwErrors | Boolean true to enable throwable errors. When enabled, wrap this method in a try/catch block. |

[androidJvm]\
suspend fun [oneSendResponseCode](one-send-response-code.md)(throwErrors: Boolean = false, oneResponseCodeRequest: [OneResponseCodeRequest](../com.thunderhead.mobile.interactions/-one-response-code-request/index.md)): [OneResponse](../com.thunderhead.mobile.responsetypes/-one-response/index.md)?

Send response code to ONE.

## Parameters

androidJvm

| | |
|---|---|
| oneResponseCodeRequest | [OneResponseCodeRequest](../com.thunderhead.mobile.interactions/-one-response-code-request/index.md) to send. |
| throwErrors | Boolean true to enable throwable errors. When enabled, wrap this method in a try/catch block. |

[androidJvm]\

@JvmOverloads()

@JvmName(name = sendResponseCode)

@RequiresApi(value = 24)

fun [oneSendResponseCode](one-send-response-code.md)(throwErrors: Boolean = false, request: [OneResponseCodeRequest](../com.thunderhead.mobile.interactions/-one-response-code-request/index.md)?): CompletableFuture<[OneResponse](../com.thunderhead.mobile.responsetypes/-one-response/index.md)?>

Java api to send properties. Please use [oneSendResponseCode](one-send-response-code.md) for kotlin. Will throw either [com.thunderhead.mobile.responsetypes.OneAPIError](../com.thunderhead.mobile.responsetypes/-one-a-p-i-error/index.md) or [com.thunderhead.mobile.responsetypes.OneSDKError](../com.thunderhead.mobile.responsetypes/-one-s-d-k-error/index.md) if [throwErrors](one-send-response-code.md) is true.

To inspect the error, use a try/catch block and catch an java.util.concurrent.ExecutionException and retrieve the cause as demonstrated in the following example using CompletableFuture.get:

try {\
  One.sendResponseCode(true, oneRequest).get(5000, TimeUnit.SECONDS);\
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
  One.sendResponseCode(true, oneRequest).join();\
} catch (CompletionException error) {\
    Log.e(TAG, error.getCause());\
} catch(OneSDKError error) {\
    Log.e(TAG, error.getErrorMessage());\
} catch(OneAPIError error) {\
    Log.e(TAG, error.getErrorMessage());\
}

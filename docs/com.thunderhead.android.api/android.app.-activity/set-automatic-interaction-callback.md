[Thunderhead](../../index.md) / [com.thunderhead.android.api](../index.md) / [android.app.Activity](index.md) / [setAutomaticInteractionCallback](./set-automatic-interaction-callback.md)

# setAutomaticInteractionCallback

`fun `[`Activity`](https://whatever/android/app/Activity.html)`?.setAutomaticInteractionCallback(init: `[`OneAutomaticInteractionCallback`](../../com.thunderhead.android.api.interactions/-one-automatic-interaction-callback/index.md)`.() -> Unit): Unit`

Set a callback in order to be notified of an *automatic*
interaction request for `this` [Activity](https://whatever/android/app/Activity.html).

To remove call [removeAutomaticInteractionCallback](remove-automatic-interaction-callback.md).

**Important**
This method should be called *after* [assignInteractionPath](../android.view.-view/assign-interaction-path.md)

Example:

```
class MainActivity : Activity() {
 override fun onCreate(savedInstanceState: Bundle?) {
   // activity setup
   setAutomaticInteractionCallback {
       onError { err ->
           Log.d("TAG", "error: ${err.errorMessage}")
       }

       onFailure { err ->
           Log.d("TAG", "failure: ${err.errorMessage}")
       }

       onSuccess { resp ->
           Log.d("TAG", "success: ${resp.interactionPath?.value?.toString()}")
       }
   }
 }
}
```


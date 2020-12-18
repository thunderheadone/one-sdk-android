[Thunderhead](../../index.md) / [com.thunderhead.android.api](../index.md) / [android.support.v4.app.Fragment](index.md) / [setAutomaticInteractionCallback](./set-automatic-interaction-callback.md)

# setAutomaticInteractionCallback

`fun Fragment?.setAutomaticInteractionCallback(init: `[`OneAutomaticInteractionCallback`](../../com.thunderhead.android.api.interactions/-one-automatic-interaction-callback/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Set a callback in order to be notified of an *automatic*
interaction request for `this` [Fragment](#).

To remove call [removeAutomaticInteractionCallback](../android.app.-activity/remove-automatic-interaction-callback.md).

**Important**
This method should be called *after* [assignInteractionPath](../android.view.-view/assign-interaction-path.md)

Example:

```
class MainActivity : FragmentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
   // activity setup
   if (savedInstanceState == null) {
     supportFragmentManager.commit {
         setReorderingAllowed(true)
         add<TestFragment>(R.id.fragment_container)
     }
     supportFragmentManager.findFragmentById(R.id.fragment_container)
         ?.setAutomaticInteractionCallback {
             onError { err ->
                 Log.d("TAG", "error: ${err.errorMessage}")
             }

             onFailure { err ->
                 Log.d("TAG", "failure: ${err.errorMessage}")
             }

             onSuccess { resp ->
                 Log.d("TAG", "success: ${resp.interactionPath?.value?.toString()}")
                 resp.process()
             }
         }
   }
 }
}
```


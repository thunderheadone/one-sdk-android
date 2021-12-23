//[thunderhead-sdk](../../index.md)/[com.thunderhead.mobile](index.md)/[setAutomaticInteractionCallback](set-automatic-interaction-callback.md)

# setAutomaticInteractionCallback

[androidJvm]\
inline fun Activity?.[setAutomaticInteractionCallback](set-automatic-interaction-callback.md)(init: [OneAutomaticInteractionCallback](../com.thunderhead.mobile.interactions/-one-automatic-interaction-callback/index.md).() -> Unit = { })

Set a callback in order to be notified of an *automatic* interaction request for thisActivity.

To remove call [removeAutomaticInteractionCallback](remove-automatic-interaction-callback.md).

**Important** This method should be called *after*[assignInteractionPath](assign-interaction-path.md)

Example:

class MainActivity : Activity() {\
 override fun onCreate(savedInstanceState: Bundle?) {\
   // activity setup\
   setAutomaticInteractionCallback {\
       onError { err ->\
           Log.d("TAG", "error: ${err.errorMessage}")\
       }\
\
       onFailure { err ->\
           Log.d("TAG", "failure: ${err.errorMessage}")\
       }\
\
       onSuccess { resp ->\
           Log.d("TAG", "success: ${resp.interactionPath?.value?.toString()}")\
       }\
   }\
 }\
}

[androidJvm]\
inline fun Fragment?.[setAutomaticInteractionCallback](set-automatic-interaction-callback.md)(init: [OneAutomaticInteractionCallback](../com.thunderhead.mobile.interactions/-one-automatic-interaction-callback/index.md).() -> Unit = { })

Set a callback in order to be notified of an *automatic* interaction request for thisFragment.

To remove call [removeAutomaticInteractionCallback](remove-automatic-interaction-callback.md).

**Important** This method should be called *after*[assignInteractionPath](assign-interaction-path.md)

Example:

class MainActivity : FragmentActivity() {\
 override fun onCreate(savedInstanceState: Bundle?) {\
   // activity setup\
   if (savedInstanceState == null) {\
     supportFragmentManager.commit {\
         setReorderingAllowed(true)\
         add<TestFragment>(R.id.fragment_container)\
     }\
     supportFragmentManager.findFragmentById(R.id.fragment_container)\
         ?.setAutomaticInteractionCallback {\
             onError { err ->\
                 Log.d("TAG", "error: ${err.errorMessage}")\
             }\
\
             onFailure { err ->\
                 Log.d("TAG", "failure: ${err.errorMessage}")\
             }\
\
             onSuccess { resp ->\
                 Log.d("TAG", "success: ${resp.interactionPath?.value?.toString()}")\
                 resp.process()\
             }\
         }\
   }\
 }\
}

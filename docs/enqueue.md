[Thunderhead](../../index.md) / [com.thunderhead.android.api.interactions](../index.md) / [OneCall](index.md) / [enqueue](./enqueue.md)

# enqueue

`abstract fun enqueue(callback: `[`OneCallback`](../-one-callback/index.md)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Method used to perform network call off the main thread.

### Parameters

`callback` - object used to listen for a callback. Can be null
if no notification of call results is needed.
//[thunderhead-sdk](../../index.md)/[com.thunderhead.android.api.logging](index.md)/[and](and.md)

# and

[androidJvm]\
infix inline fun <[T](and.md) : Enum<[T](and.md)>> [T](and.md).[and](and.md)(other: [T](and.md)): EnumSet<[T](and.md)>

Create an EnumSet from one Enum<T> and another. Ex. val set = Level.WARN and Level.ERROR

[androidJvm]\
infix inline fun <[T](and.md) : Enum<[T](and.md)>> EnumSet<[T](and.md)>.[and](and.md)(other: [T](and.md)): EnumSet<[T](and.md)>

Create an EnumSet with all the contents of one EnumSet<T> plus another T Ex. val set = Level.WARN and Level.ERROR and Level.INFO

//[thunderhead-sdk](../../../index.md)/[com.thunderhead.android.api.optout](../index.md)/[OneOptOutConfiguration](index.md)

# OneOptOutConfiguration

[androidJvm]\
class [OneOptOutConfiguration](index.md)

Configuration object for opting in and out of tracking. Privacy compliance method to completely stop tracking a customer's actions.

Use the [Builder](-builder/index.md) to create a new instance.

## Parameters

androidJvm

| | |
|---|---|
| optOut | Boolean true to opt out of tracking. |
| optInOptions | Set<OptInOptions> Set of options that have opt in enabled. |

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [androidJvm]<br>class [Builder](-builder/index.md)<br>Builder to create [OneOptOutConfiguration](index.md). |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: Any?): Boolean |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): Int |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): String |

## Properties

| Name | Summary |
|---|---|
| [optInOptions](opt-in-options.md) | [androidJvm]<br>val [optInOptions](opt-in-options.md): Set<[OptInOptions](../-opt-in-options/index.md)><br>Set<OptInOptions> Set of options that have opt in enabled. |
| [optOut](opt-out.md) | [androidJvm]<br>val [optOut](opt-out.md): Boolean = false<br>Boolean true to opt out of tracking. |

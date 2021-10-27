//[thunderhead-sdk](../../../../index.md)/[com.thunderhead.android.api.optout](../../index.md)/[OneOptOutConfiguration](../index.md)/[Builder](index.md)

# Builder

[androidJvm]\
class [Builder](index.md)

Builder to create [OneOptOutConfiguration](../index.md).

## Constructors

| | |
|---|---|
| [Builder](-builder.md) | [androidJvm]<br>fun [Builder](-builder.md)() |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [androidJvm]<br>fun [build](build.md)(): [OneOptOutConfiguration](../index.md)<br>Create a [OneOptOutConfiguration](../index.md) from provided configuration. |
| [optInOptions](opt-in-options.md) | [androidJvm]<br>fun [optInOptions](opt-in-options.md)(optInOptions: Set<[OptInOptions](../../-opt-in-options/index.md)>?): [OneOptOutConfiguration.Builder](index.md) |
| [optOut](opt-out.md) | [androidJvm]<br>fun [optOut](opt-out.md)(optOut: Boolean?): [OneOptOutConfiguration.Builder](index.md) |

## Properties

| Name | Summary |
|---|---|
| [optInOptions](opt-in-options.md) | [androidJvm]<br>var [optInOptions](opt-in-options.md): Set<[OptInOptions](../../-opt-in-options/index.md)><br>Set Set of options that have opt in enabled. |
| [optOut](opt-out.md) | [androidJvm]<br>var [optOut](opt-out.md): Boolean = false<br>Boolean true to opt out of tracking. |

//[thunderhead-sdk](../../../index.md)/[com.thunderhead.mobile.configuration](../index.md)/[OneConfiguration](index.md)

# OneConfiguration

[androidJvm]\
class [OneConfiguration](index.md)

A configuration object for the Thunderhead SDK.

Use the [Builder](-builder/index.md) for creating a new instance.

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [androidJvm]<br>class [Builder](-builder/index.md)<br>Builder to create a [OneConfiguration](index.md). |
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: Any?): Boolean |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): Int |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): String |

## Properties

| Name | Summary |
|---|---|
| [apiKey](api-key.md) | [androidJvm]<br>val [apiKey](api-key.md): String? = null<br>The API key to authenticate with when making requests. |
| [host](host.md) | [androidJvm]<br>val [host](host.md): URI? = null<br>The ONE host to send data to. |
| [mode](mode.md) | [androidJvm]<br>val [mode](mode.md): [OneMode](../-one-mode/index.md)?<br>The Mode of the Thunderhead SDK. |
| [sharedSecret](shared-secret.md) | [androidJvm]<br>val [sharedSecret](shared-secret.md): String? = null<br>The shared secret to authenticate with when making requests. |
| [siteKey](site-key.md) | [androidJvm]<br>val [siteKey](site-key.md): String? = null<br>The ONE workspace to configure the SDK with. |
| [touchpoint](touchpoint.md) | [androidJvm]<br>val [touchpoint](touchpoint.md): URI? = null<br>The ONE touchpoint to configure the SDK with. |
| [userId](user-id.md) | [androidJvm]<br>val [userId](user-id.md): String? = null<br>The user ID that the [apiKey](api-key.md) & [sharedSecret](shared-secret.md) represent. |

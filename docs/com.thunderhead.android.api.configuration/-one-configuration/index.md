[Thunderhead](../../index.md) / [com.thunderhead.android.api.configuration](../index.md) / [OneConfiguration](./index.md)

# OneConfiguration

`class OneConfiguration`

A configuration object for the Thunderhead SDK.

Use the [Builder](-builder/index.md) for creating a new instance.

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | Builder to create a [OneConfiguration](./index.md).`class Builder` |

### Properties

| Name | Summary |
|---|---|
| [apiKey](api-key.md) | The API key to authenticate with when making requests. This can be found in `ONE Admin -> API Credentials -> OAuth 1.0 Credentials` in the Admin UI.`val apiKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [host](host.md) | The ONE host to send data to.`val host: `[`URI`](https://docs.oracle.com/javase/6/docs/api/java/net/URI.html)`?` |
| [mode](mode.md) | The Mode of the Thunderhead SDK. [OneModes.ADMIN_MODE](#) should be used when updating the ONE workspace. [OneModes.USER_MODE](#) should be used in production when uploaded to the play store.`val mode: OneModes?` |
| [sharedSecret](shared-secret.md) | The shared secret to authenticate with when making requests. This can be found in `ONE Admin -> API Credentials -> OAuth 1.0 Credentials` in the Admin UI.`val sharedSecret: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [siteKey](site-key.md) | The ONE workspace to configure the SDK with. This can be found in `ONE Config -> One Tag` in the Admin UI.`val siteKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [touchpoint](touchpoint.md) | The ONE touchpoint to configure the SDK with. This can be found in `ONE Collect -> Touchpoints` in the Admin UI.`val touchpoint: `[`URI`](https://docs.oracle.com/javase/6/docs/api/java/net/URI.html)`?` |
| [userId](user-id.md) | The user ID that the [apiKey](api-key.md) &amp; [sharedSecret](shared-secret.md) represent.`val userId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |

### Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | `fun equals(other: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | `fun hashCode(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [builder](builder.md) | Convenience Java Factory Function to create a [Builder](-builder/index.md).`fun builder(): Builder`<br>Convenience Java Factory Function to create a [Builder](-builder/index.md) that is pre-populated with values from another [OneConfiguration](./index.md).`fun builder(configuration: `[`OneConfiguration`](./index.md)`?): Builder` |

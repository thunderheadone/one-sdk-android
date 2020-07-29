[Thunderhead](../../../index.md) / [com.thunderhead.android.api.configuration](../../index.md) / [OneConfiguration](../index.md) / [Builder](./index.md)

# Builder

`class Builder`

Builder to create a [OneConfiguration](../index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Builder to create a [OneConfiguration](../index.md).`Builder()` |

### Properties

| Name | Summary |
|---|---|
| [apiKey](api-key.md) | The API key to authenticate with when making requests. This can be found in `ONE Admin -> API Credentials -> OAuth 1.0 Credentials` in the Admin UI.`var apiKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [host](host.md) | The ONE host to send data to.`var host: `[`URI`](https://docs.oracle.com/javase/6/docs/api/java/net/URI.html)`?` |
| [mode](mode.md) | The Mode of the Thunderhead SDK. [OneModes.ADMIN_MODE](#) should be used when updating the ONE workspace. [OneModes.USER_MODE](#) should be used in production when uploaded to the play store.`var mode: OneModes?` |
| [sharedSecret](shared-secret.md) | The shared secret to authenticate with when making requests. This can be found in `ONE Admin -> API Credentials -> OAuth 1.0 Credentials` in the Admin UI.`var sharedSecret: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [siteKey](site-key.md) | The ONE workspace to configure the SDK with. This can be found in `ONE Config -> One Tag` in the Admin UI.`var siteKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [touchpoint](touchpoint.md) | The ONE touchpoint to configure the SDK with. This can be found in `ONE Collect -> Touchpoints` in the Admin UI.`var touchpoint: `[`URI`](https://docs.oracle.com/javase/6/docs/api/java/net/URI.html)`?` |
| [userId](user-id.md) | The user ID that the [apiKey](api-key.md) &amp; [sharedSecret](shared-secret.md) represent.`var userId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |

### Functions

| Name | Summary |
|---|---|
| [apiKey](api-key.md) | `fun apiKey(apiKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): Builder` |
| [build](build.md) | Creates the [OneConfiguration](../index.md) used to configure the SDK.`fun build(): `[`OneConfiguration`](../index.md) |
| [host](host.md) | `fun host(host: `[`URI`](https://docs.oracle.com/javase/6/docs/api/java/net/URI.html)`?): Builder` |
| [mode](mode.md) | `fun mode(mode: OneModes?): Builder` |
| [sharedSecret](shared-secret.md) | `fun sharedSecret(sharedSecret: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): Builder` |
| [siteKey](site-key.md) | `fun siteKey(siteKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): Builder` |
| [touchpoint](touchpoint.md) | `fun touchpoint(touchpoint: `[`URI`](https://docs.oracle.com/javase/6/docs/api/java/net/URI.html)`?): Builder` |
| [userId](user-id.md) | `fun userId(userId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): Builder` |

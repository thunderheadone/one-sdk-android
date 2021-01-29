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
| [apiKey](api-key.md) | The API key to authenticate with when making requests. This can be found in `ONE Admin -> API Credentials -> OAuth 1.0 Credentials` in the Admin UI.`var apiKey: String?` |
| [host](host.md) | The ONE host to send data to.`var host: `[`URI`](https://whatever/java/net/URI.html)`?` |
| [mode](mode.md) | The Mode of the Thunderhead SDK. [OneModes.ADMIN_MODE](#) should be used when updating the ONE workspace. [OneModes.USER_MODE](#) should be used in production when uploaded to the play store.`var mode: OneModes?` |
| [sharedSecret](shared-secret.md) | The shared secret to authenticate with when making requests. This can be found in `ONE Admin -> API Credentials -> OAuth 1.0 Credentials` in the Admin UI.`var sharedSecret: String?` |
| [siteKey](site-key.md) | The ONE workspace to configure the SDK with. This can be found in `ONE Config -> One Tag` in the Admin UI.`var siteKey: String?` |
| [touchpoint](touchpoint.md) | The ONE touchpoint to configure the SDK with. This can be found in `ONE Collect -> Touchpoints` in the Admin UI.`var touchpoint: `[`URI`](https://whatever/java/net/URI.html)`?` |
| [userId](user-id.md) | The user ID that the [apiKey](api-key.md) &amp; [sharedSecret](shared-secret.md) represent.`var userId: String?` |

### Functions

| Name | Summary |
|---|---|
| [apiKey](api-key.md) | `fun apiKey(apiKey: String?): Builder` |
| [build](build.md) | Creates the [OneConfiguration](../index.md) used to configure the SDK.`fun build(): `[`OneConfiguration`](../index.md) |
| [host](host.md) | `fun host(host: `[`URI`](https://whatever/java/net/URI.html)`?): Builder` |
| [mode](mode.md) | `fun mode(mode: OneModes?): Builder` |
| [sharedSecret](shared-secret.md) | `fun sharedSecret(sharedSecret: String?): Builder` |
| [siteKey](site-key.md) | `fun siteKey(siteKey: String?): Builder` |
| [touchpoint](touchpoint.md) | `fun touchpoint(touchpoint: `[`URI`](https://whatever/java/net/URI.html)`?): Builder` |
| [userId](user-id.md) | `fun userId(userId: String?): Builder` |

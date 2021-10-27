//[thunderhead-sdk](../../../../index.md)/[com.thunderhead.android.api.configuration](../../index.md)/[OneConfiguration](../index.md)/[Builder](index.md)

# Builder

[androidJvm]\
class [Builder](index.md)

Builder to create a [OneConfiguration](../index.md).

## Constructors

| | |
|---|---|
| [Builder](-builder.md) | [androidJvm]<br>fun [Builder](-builder.md)() |

## Functions

| Name | Summary |
|---|---|
| [apiKey](api-key.md) | [androidJvm]<br>fun [apiKey](api-key.md)(apiKey: String?): [OneConfiguration.Builder](index.md) |
| [build](build.md) | [androidJvm]<br>fun [build](build.md)(): [OneConfiguration](../index.md)<br>Creates the [OneConfiguration](../index.md) used to configure the SDK. |
| [host](host.md) | [androidJvm]<br>fun [host](host.md)(host: URI?): [OneConfiguration.Builder](index.md) |
| [mode](mode.md) | [androidJvm]<br>fun [mode](mode.md)(mode: OneModes?): [OneConfiguration.Builder](index.md) |
| [sharedSecret](shared-secret.md) | [androidJvm]<br>fun [sharedSecret](shared-secret.md)(sharedSecret: String?): [OneConfiguration.Builder](index.md) |
| [siteKey](site-key.md) | [androidJvm]<br>fun [siteKey](site-key.md)(siteKey: String?): [OneConfiguration.Builder](index.md) |
| [touchpoint](touchpoint.md) | [androidJvm]<br>fun [touchpoint](touchpoint.md)(touchpoint: URI?): [OneConfiguration.Builder](index.md) |
| [userId](user-id.md) | [androidJvm]<br>fun [userId](user-id.md)(userId: String?): [OneConfiguration.Builder](index.md) |

## Properties

| Name | Summary |
|---|---|
| [apiKey](api-key.md) | [androidJvm]<br>var [apiKey](api-key.md): String? = null<br>The API key to authenticate with when making requests. |
| [host](host.md) | [androidJvm]<br>var [host](host.md): URI? = null<br>The ONE host to send data to. |
| [mode](mode.md) | [androidJvm]<br>var [mode](mode.md): OneModes?<br>The Mode of the Thunderhead SDK. |
| [sharedSecret](shared-secret.md) | [androidJvm]<br>var [sharedSecret](shared-secret.md): String? = null<br>The shared secret to authenticate with when making requests. |
| [siteKey](site-key.md) | [androidJvm]<br>var [siteKey](site-key.md): String? = null<br>The ONE workspace to configure the SDK with. |
| [touchpoint](touchpoint.md) | [androidJvm]<br>var [touchpoint](touchpoint.md): URI? = null<br>The ONE touchpoint to configure the SDK with. |
| [userId](user-id.md) | [androidJvm]<br>var [userId](user-id.md): String? = null<br>The user ID that the [apiKey](api-key.md) & [sharedSecret](shared-secret.md) represent. |

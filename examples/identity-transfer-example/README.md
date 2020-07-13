# Identity transfer example

This example app demonstrates how to use the identity transfer feature in the Thunderhead SDK.  

### How to run

The parameters found in the `gradle.properties` file is used as configuration parameters for the Thunderhead SDK, which is configured in [ExampleApplication.kt](https://github.com/thunderheadone/one-sdk-android/blob/master/examples/identity-transfer-example/app/src/main/java/com/thunderhead/identitytransferexample/ExampleApplication.kt).

* Clone this repo or download the source directly.
* Update configuration values in `gradle.properties` with your Thunderhead credentials:
```java
thunderheadAdminMode=false
thunderheadUser="user@tenant"
thunderheadApiKey="my-key"
thunderheadSharedSecret="my-secret"
thunderheadSiteKey="my-site-key"
thunderheadTouchpoint="android://thunderheadDemo"
thunderheadHost="https://xx.thunderhead.com"
```
* Open project in Android Studio
* Clean and Build the Project
* Play the "App" on an emulator

App Flow Summary:
1. On initial app start, [MainActivity.kt](https://github.com/thunderheadone/one-sdk-android/blob/master/examples/identity-transfer-example/app/src/main/java/com/thunderhead/identitytransferexample/MainActivity.kt) is presented. 
2. Enter the `URL` of your choice in the `TextView`, the default is https://www.thunderhead.com.
3. Press the `Transfer Identity To Web`  button.

This will open the entered `URL` in the mobile browser and the `URL` will have an appended query parameter of `one-tid=<GUID>` which will transfer the users identity to the consuming website.

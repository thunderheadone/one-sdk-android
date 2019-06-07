# Thunderhead SDK and Orchestration Optimization Example

### How to run
* Clone this repo or download the source directly.
* Update the values in `gradle.properties` with your Thunderhead Information:
  * thunderheadAdminMode=false 
  * thunderheadUser="user@tenant"
  * thunderheadApiKey="my-key"
  * thunderheadSharedSecret="my-secret"
  * thunderheadSiteKey="my-site-key"
  * thunderheadTouchpoint="android://thunderheadDemo"
  * thunderheadHost="https://na2.thunderhead.com"
* Open the project with Android Studio
* Clean and Build the Project
* Play the "App" on an emulator.

### What's happening
The information entered in the `gradle.properties` file is used as as the initialization parameters for the Thunderhead SDK.
The ThunderheadSDK is initialized in the `ExampleApplication.kt` class using the information you entered in the `gradle.properties` file.
If your information is correct and there are no network errors the `MainActivity.kt` will be started when the App is opened.

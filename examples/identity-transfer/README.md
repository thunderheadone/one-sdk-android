# Thunderhead SDK and Orchestration Plugin Example

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
The information entered in the `gradle.properties` file is used as as the configuration parameters for the Thunderhead SDK.
The ThunderheadSDK is configured in the `ExampleApplication.kt` class using the information you entered in the `gradle.properties` file.
If your information is correct and there are no network errors the `MainActivity.kt` will be started when the App is opened.
Enter the url of your choice, the default is https://www.thunderhead.com, and press the `Transfer Identity To Web`  button.
This will open the URL you entered in whatever browser the phone currently has.  If all things go as planned the url 
will have a new query parameter of `one-tid=<GUID>` appended which will successfully transfer the users identity to the consuming website.

# Thunderhead SDK and Orchestration Optimization Example

### How to run
* Clone this repo or download the source directly.
* Update the values in `gradle.properties` with your Thunderhead Information:
  * thunderheadAdminMode=true 
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

The app is a simple TabView based app with 2 fragment screens:
 * First Tab `FirstFragment`
   * Contains a Recycler View which displays a list of product images
   * 3 products are displayed in the list and are linked to images found under the app res folder 
   * This will be the area which we will optimize based on JSON content received from ONE
   * Tapping a recycler item cell in the second tab will send a response code to ONE to record the user sentiment for 
     the optimization asset shown
 * Second Tab `SecondFragment`
   * Contains a simple set of buttons which will help us record the app user interest similar to how this could be 
     achieved in your app

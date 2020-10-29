#### Version 8.0.0
* [NEW] Changed the SDK target to API 29 and updated it to be compatible with the network connection checker.
* [UPDATE] Added support for Admin and User mode views to be presented correctly on devices with large screens.
* [UPDATE] Use modern Android SDK network monitoring callbacks to ensure offline and online interactions are correctly identified.
* [UPDATE] Removed unnecessary loading indicator in Preview mode to better provide a real world experience.
* [BUGFIX] Fixed an issue where mini notifications were not displayed after the Airplane mode is on and off.
* [BUGFIX] Fixed an issue where a user was logged out of Preview mode when the Airplane mode is on.

#### Version 7.0.2
* [BUGFIX] Added additional guards around opt-out to further prevent sending data after opting out of tracking in the `Application` class.

#### Version 7.0.1
* [BUGFIX] Addressed an issue where, in some cases, the SDK would continue to send some data after opting out of tracking in the `Application` class.

#### Version 7.0.0
* [BREAKING] Supported minimum SDK version increased to 21.
* [BREAKING] Requires the [Orchestration Plugin version 2.0.0](https://github.com/thunderheadone/one-android-orchestration-plugin/releases).
* [NEW] Added support for Android Gradle Plugin 3.6.x.
* [UPDATE] Removed use of redirects when opening an optimization url. Previously url redirects were used to pass the response codes between the app and external channel. Response codes are now sent automatically by the SDK and the target url is opened as normal without a redirect being used.
* [UPDATE] Added additional setup instructions on how to configure the SDK with apps already configured with push messaging. For further details on this [see our readme](https://github.com/thunderheadone/one-sdk-android#additional-configuration-required-for-apps-configured-with-push-messaging).
* [BUGFIX] Resolved an issue to avoid offline requests being sent incorrectly.

#### Version 6.0.2
* [BUGFIX] Addressed an issue where URI/URL APIs returned null instead of original input if the SDK is not configured correctly.

#### Version 6.0.1
* [BUGFIX] Addressed an issue where an NPE could occur in Admin mode as a result of hiding or showing the Poker Chip when an app moves into background.

#### Version 6.0.0
* [BREAKING] Removed the need to provide a `Context` on configuration. For further details on this [see our migration guide](https://github.com/thunderheadone/one-sdk-android/blob/master/MIGRATION-VERSION-6.md#sdk-initialization).
* [BREAKING] All methods have now been updated and can be accessed via static APIs. For further details on this [see our readme](https://github.com/thunderheadone/one-sdk-android/blob/master/MIGRATION-VERSION-6.md#using-static-members).
* [NEW] Added Kotlin-first API access. For further details on this [see our readme](https://github.com/thunderheadone/one-sdk-android#additional-features).
* [NEW] Added ability to reconfigure the SDK in different regions and spaces. For further details on this [see our sample app on how to reconfigure the SDK](https://github.com/thunderheadone/one-sdk-android/tree/master/examples/dynamic-configuration-example#configure-and-reconfigure-the-sdk).
* [NEW] Added ability to change the SDK mode on the fly. For further details on this [see our readme](https://github.com/thunderheadone/one-sdk-android#set-up-the-sdk-in-user-mode).
* [BUGFIX] Resolved all `StrictMode` violations.

#### Version 5.0.1
* [BUGFIX] Addressed an issue where a NPE could occur as a result of hiding or showing the the Poker Chip on Activity Resume.

#### Version 5.0.0
* [BREAKING] SDK `compileOptions` now require Java 8 (`JavaVersion.VERSION_1_8`) for both `sourceCompatibility` and `targetCompatibility`. See the ["Use Java 8 language"](https://developer.android.com/studio/write/java8-support) section of the Android Developers site, for further details.
* [UPDATE] Moved the properties caching under the same database as the offline interaction caching. All requests made via send properties and all interactions calls will be stored offline when the server is down or an internet connection cannot be established. This will help ensure you have a complete picture of the user profile regardless connectivity state.
* [UPDATE] Improved the Admin mode login credentials storage to save these by site key. This allows business users to use Admin mode easily when multiple site keys are used by an app.
* [UPDATE] Fixed a crash that was occurring when entering and exiting Preview mode.
* [BUGFIX] Fixed an issue where the username field was not properly displayed at a horizontal login screen.

#### Version 4.2.11
* [BUGFIX] Fixed a race condition that could produce an Application Not Responding (ANR) error.

#### Version 4.2.10
* [BUGFIX] Fixed an issue where push notifications were not received when there are multiple Firebase services integrated into an application.

#### Version 4.2.9
* [UPDATE] Improved Admin mode login encryption to match Google guidelines.

#### Version 4.2.8
* [BUGFIX] Fixed an issue where a null view may crash the app while trying to send an interaction.

#### Version 4.2.7
* [BUGFIX] Fixed an issue where Fragment Interactions were not detected for AndroidX Fragments in `androidx.fragment:fragment:1.2.0-rc03` library.

#### Version 4.2.6
* [BUGFIX] Fixed the push notification implementation to use the icon set under the `meta-data` tag. If no `meta-data` is set, the implementation will use the default icon, as outlined [here](https://firebase.google.com/docs/cloud-messaging/android/receive).

#### Version 4.2.5
* [BUGFIX] Fixed an issue where a dismissed mini-notification prevented subsequent mini-notifications from appearing to the user.

#### Version 4.2.4
* [BUGFIX] Fixed an issue where adaptive icons can't be inflated by the system UI into a push notification on API 26, which can lead to a crash. This fix is a workaround for an Android platform bug - about which you can read more [here](https://issuetracker.google.com/issues/68716460).

#### Version 4.2.3
* [BUGFIX] Addressed an issue where a NullPointerException could happen as a result of Firebase being initialized before the SDK has been initialized.

#### Version 4.2.2
* [BUGFIX] Fixed an issue where a socket timeout could lead to in-app notifications not being shown for a period of time until the connection pool would be reset.
* [BUGFIX] Fixed an issue where an in-app notification could fail to be flagged as shown, leading to subsequent notifications not being shown as a result.

#### Version 4.2.1
* [BUGFIX] Fixed a crash that was occurring when the push token was received from Firebase before the ONE SDK has been initialized. 

#### Version 4.2.0
* [UPDATE] Changed `renderscriptTargetApi` from 20 to 22.
* [BUGFIX] Fixed an issue where adding a `RadioButton` dynamically would lead to a stack overflow crash.
* [BUGFIX] Fixed an issue where an application implementing `com.squareup.okhttp3` version >= 3.9.0 would lead to a crash with an `IllegalStateException` in the SDK when accessing `OptOutInterceptor`.
* [BUGFIX] Fixed an issue when fragments were not recognized on the SDK interactions.
* [BUGFIX] Fixed an issue when optimization points count was not updated on the admin popover and preview pane.
* [BUGFIX] Fixed an issue when "No results" was displayed on the "Existing Customer Attributes" page while searching for an attribute.
* [BUGFIX] Fixed an issue when "SIGN IN" button and "Remember Me" toggle were overlapped by the keyboard.
* [BUGFIX] Fixed the background color of the admin popover's inactive tabs that was inconsistent within the popover.
* [BUGFIX] Fixed the description text of the "Choose a proposition" field that was inconsistent between Android and iOS platforms.
* [BUGFIX] Fixed an issue with inconsistent text colors on the Capture Activity screen in the Interaction Studio theme.
* [BUGFIX] Fixed an issue when the search and close icons had different margins.

#### Version 4.1.0 
* [NEW] Changed the SDK target to API 28 and updated it to be compatible with v3.4.2 of the Android Gradle Plugin.
* [UPDATE] Improved our push notifications delivery framework to ensure push messages can be delivered in apps targeting API 26 and above.
* [UPDATE] Updated the SDK proguard file to cover AndroidX implementations. 
* [UPDATE] Added improvements to the Admin mode login screen to avoid text overlapping views.
* [BUGFIX] Fixed an issue where push notifications were not shown when an app was removed from the recent apps list.

#### Version 4.0.1 
* [UPDATE] Updated our logger api and documentation to resolve logger warnings reported by some of our customers.
* [UPDATE] Updated the SDK proguard file to better handle warnings thrown when using `TabLayout` implementation with jetified apps.
* [UPDATE] Updated the SDK integration documentation to clarify how to migrate from the old Aspect J plugin to our new Orchestration plugin.
* [BUGFIX] Fixed a memory leak that was occurring occasionally in Admin mode, when removing an interaction from the stack.

#### Version 4.0.0
* [BREAKING] New Orchestration Plugin required for integration, to facilitate codeless identity transfer and outbound link tracking.
* [BREAKING] Aspects removed from AAR.

#### Version 3.0.0
* [BREAKING] Removed support for identity sync from the SDK. `identitySync` api calls are no longer available.
* [BREAKING] Removed Retrofit 1 support.
* [BREAKING] Removed Google Cloud Messaging (GCM) support.

#### Version 2.22.0
* [UPDATE] Changed documentation to support Android Gradle Plugin 3.2+.

#### Version 2.21.1
* [UPDATE] Changed documentation to support Java8 enabled apps.
* [BUGFIX] Fixed an issue where the list selection was cleared in Admin mode when clearing the search text.

#### Version 2.21.0
* [NEW] Added support for Interaction Studio Theme.
* [NEW] Updated Admin mode Entity Names to match the naming in the web application. 
* [BUGFIX] Resolved an issue where the Border outline covered system buttons while in Admin mode.
* [BUGFIX] Resolved an issue where incorrect labels displayed with incorrect text size and formatting.

#### Version 2.20.1
* [BUGFIX] Updated the SDK to work with TLS 1.1 and 1.2 on devices that run Android API 19 or older.

#### Version 2.20.0
* [NEW] Ability to integrate the SDK using Maven dependencies.
* [NEW] Ability to create a touchpoint in ONE automatically in Admin Active mode on login.
* [BUGFIX] Fixed a Preview mode issue, where pressing the back button would lead to a crash while using Android API level 19.
* [BUGFIX] Fixed an issue where clicking a mini notification while in airplane mode would lead to a crash.
* [BUGFIX] Fixed highlighting and preview panel misalignment issue while using Android API level 19.
* [BUGFIX] Fixed an issue where having a single capture point set on a navigation drawer would not trigger a request to ONE.

#### Version 2.19.0
* [BREAKING] Minimum SDK version has now been set to API 16.
* [NEW] Ability to opt an end-user out of tracking, if the end-user does not give permission to be tracked in the client app.
* [UPDATE] Updated the SDK to use the Android compile SDK version 27, min SDK version to 16, build tools version 27.0.3, target SDK version to 27 and support library to 27.0.2
* [UPDATE] Improved SDK proguard rules file to include support for `org.xmlpull.v1.** { *; }`. This improvement fixes crashes in Admin mode seen when creating tracking or capture points if obfuscation is turned on in the client app.
* [BUGFIX] Fixed an issue where repeatedly hitting the Apply button when creating tracking point in Admin mode, would lead to a crash.
* [BUGFIX] Fixed an issue where mini-notifications could be pressed multiple times by a user, which would lead to multiple responses being sent to ONE.

#### Version 2.18.1
* [BUGFIX] Resolved "java.lang.ClassNotFoundException: Didn't find class "com.jakewharton.retrofit.Ok3Client". Documentation updated as well.

#### Version 2.18.0
* [NEW] Switched from a cookie based login in Admin mode to OAuth 2.
* [UPDATE] Updated the SDK support library to 26.1.0.
* [UPDATE] Removed support for android.support.v7.app.ActionBarActivity 
* [BUGFIX] Resolved an issue where not all capture points were sent on scrolling a list or recycler view that contained capture points against its items. 
* [BUGFIX] Resolved an issue where an incorrect alert was being shown to users who have logged in following a session expiry. 
* [BUGFIX] Fixed an issue where the SDK was crashing in Admin mode if the network connection was slow. 
* [BUGFIX] Resolved an issue where the user was unable to dismiss the preview panel after a full screen notification was presented.
* [BUGFIX] Resolved an issue where the app was restarting when returning to the view that presents a full screen notification in Preview mode.
* [BUGFIX] Fixed an issue where items in TabLayout ViewPager are not always selectable.
* [BUGFIX] Fixed an issue where the mini notification was not preserving its background colour in some apps. 
* [BUGFIX] Resolved an issues where the mini notification was not fully collapsed when returning from a linked opened from a mini notification. 
* [BUGFIX] Fixed an issue where the Preview mode highlight was not correctly shown around the whole screen in some apps.  
* [BUGFIX] Resolved an issue where the app install interaction was being sent when running the SDK in Admin mode.
* [BUGFIX] Resolved an issue where the Admin mode search text field was incorrectly clearing on back press events.
* [BUGFIX] Resolved an issue where the Poker chip used to disappear after an element capture point was created.
* [BUGFIX] Resolved an issue where the login screen wasn't brought up when long pressing the poker chip.

#### Version 2.17.2
* [BUGFIX] Fixed an issue where fullscreen notifications were not scaled appropriately.
* [BUGFIX] Fixed an issue where the tid was incorrectly set in Preview mode when a response code requests was being sent.

#### Version 2.17.1
* [UPDATE] Updated the SDK proguard rules to keep RenderScript required for full screen notifications.
* [BUGFIX] Fixed an issue with the proguard rules file where a close bracket was missing. 
* [BUGFIX] Added a fix to catch all exceptions from `getPackageInfo`.

#### Version 2.17.0

* [NEW] Ability to display the mini notification from the top.
* [NEW] Ability to track and capture tabs using the groups functionality. 
* [UPDATE] Several improvements have been added to the Admin mode highlighting implementation.
* [UPDATE] Updated the Retrofit 1 implementation to send the SDK version with each request made by the SDK.
* [BUGFIX] Resolved an issue where Admin users were unable to select and track overlapping interactions.
* [BUGFIX] Resolved an issue where the poker chip would not appear immediately after the "draw over app" permissions switched on.
* [BUGFIX] Added a fix to prevent the install tracking interaction to be sent in Admin mode.
* [BUGFIX] Fixed a NPE which was occurring in Admin mode after a business user would add a capture or tracking point.
* [BUGFIX] Resolved an issue where analytics data was not being sent when tapping a tracked or captured tab.
* [BUGFIX] Resolved an issue where business users were unable to select elements from a navigation drawer in Admin mode.
* [BUGFIX] Resolved an issue where the poker chip would disappear during a session as the business user navigates across an app.
* [BUGFIX] Resolved an issue where analytics data was not sent when a group tracking setup was used to track multiple list items.
* [BUGFIX] Resolved an issue where business users were unable to select a proposition attribute when using the search functionality in Admin mode.
* [BUGFIX] Resolved socket timeout issues seen when sending offline interaction requests by adding a connection pool flushing.
* [BUGFIX] Resolved an issue where could not find class 'android.app.FragmentController' were being thrown.
* [BUGFIX] Resolved an issue where if a business user would hit the back button in Preview mode the SDK would hide the poker chip.
* [BUGFIX] Resolved an issue where the one-tid was no longer appended to urls opened from an app when building the app using gradle 3+.
* [BUGFIX] Resolved an issue where java.lang.ClassCastException were being thrown when running the SDK in User mode. 

#### Version 2.16.1

* [UPDATE] Updated the proguard rules to cover previously unkept classes which should have been kept. 

#### Version 2.16.0

* [NEW] Ability to store and send offline interactions. Any interactions triggered when a connection is not available or ONE was offline, will now be stored locally and sent later when a connection has been established.
* [NEW] All API headers will now contain the SDK version. 
* [UPDATE] Updated the Admin mode implementation to work when running on an Android O device.
* [UPDATE] Improved the thread creation implementation to avoid creating too many threads.
* [UPDATE] Admin mode dialogs now use the activity rather than the application context.
* [BUGFIX] Resolved all StrictMode violations.
* [BUGFIX] Resolved an issue where the poker chip stayed on screen after pausing the application in Admin mode.
* [BUGFIX] Resolved an issue where the Login activity crashed and leaked when the app was put into background in Admin mode.
* [BUGFIX] Resolved an issue where the preview panel was shown under the device hardware buttons in Admin mode.
* [BUGFIX] Resolved several highlighting logic issues. 
* [BUGFIX] Resolved an issue where the Poker Chip would leak in Admin mode when rotating the device. 
* [BUGFIX] Resolved an issue where the poker chip disappeared in Admin mode  when re-logging after the session has expired.
* [BUGFIX] Resolved an issue where a CookieManager NoClassDefFoundError was being thrown.
* [BUGFIX] Resolved a crash which was occurring when pressing the Exit Preview button in Admin mode. 


#### Version 2.15.5

* [UPDATE] Added support for tracking and capturing data from custom TabLayout.
* [UPDATE] Added function to parse TabLayout.SlidingTabStrip view.
* [UPDATE] Added function to parse TabLayout.TabView view.
* [UPDATE] Added support for SparseArray support for native fragments on API 26.
* [UPDATE] Added support to track interactions which have no trackable elements, but have a collection view inside them.
* [BUGFIX] Fixed issue with wrong SparseArray copy in FragmentInteractionViewDetectors. 
* [BUGFIX] Implemented SparseArray fix for nested fragments.


#### Version 2.15.4

* [UPDATE] Updated the hierarchy change listeners to be re-injected after onResume() is called. 
* [UPDATE] Added behaviour to search for visible fragments onChildViewAdded and onChildViewRemoved.

#### Version 2.15.3

* [UPDATE] Added support for tracking and capturing data from TabLayout.
 	
#### Version 2.15.2

* [BUGFIX] Resolved cast exceptions seen in some apps that target API 26 and use Fragments in their implementation.
* [BUGFIX] Fixed a null pointer exception which could be observed in some apps when running the SDK in Admin mode.

#### Version 2.15.1

* [UPDATE] Updated the SDK to allow the Poker Chip to be displayed in apps targeting API 26.
	
#### Version 2.15.0

* [NEW] Added codeless tracking support for views which contain a click listener.
* [NEW] Added ability to send interaction paths which contain UTF8 characters. 
* [UPDATE] Updated the SDK to support integrations with appcompat and support library version 23.
* [BUGFIX] Fixed an issue where the incorrect snapshot type was added to the monitor link. 
* [BUGFIX] Resolved a crash which was seen when initializing ONE against apps built with Android 6.

#### Version 2.14.0

* [NEW] Ability to retrieve a response from automatically triggered interaction callback.
* [UPDATE] Improved touchpoint uri validation.
* [UPDATE] Improved nullability checks within the SDK. 
* [UPDATE] Added some Admin mode usability improvements around how the keyboard is displayed and updated placeholder text copy.
* [UPDATE] Added VisibleForTesting annotations to methods required for testing purposes only. 
* [BUGFIX] Added Context theming to SDK inflated view and dialogs which are shown within target's app activity to ensure the correct SDK UI is used.
* [BUGFIX] Updated the clearUserProfile method to clear the tid from all the places where this is stored by the SDK.
* [BUGFIX] Resolved unnecessary toast errors from being shown in Admin mode.
* [BUGFIX] Resolved an issue where the Admin mode highlighting used to disappear when switching between the element and  group option in the Admin popover.
* [BUGFIX] Resolved an issue where the Admin popover was incorrectly displayed when running the SDK in Android 4.
* [BUGFIX] Fixed a data capture issue related to capture point data not sending on click if no tracking point was created for a button.
* [BUGFIX] Fixed a crash that was occurring in some apps when running the SDK in Admin mode. 

#### Version 2.13.1

* [BUGFIX] We have updated our proguard.txt file to prevent a build error which was seen when integrating the v2.12.0 of the ONE SDK for Android.

#### Version 2.13.0

 * [NEW] Improve install/identity tracking to allow identity sync with web touchpoints programmatically.
 * [NEW] Added a watchdog service which listens for boot events to allow for push notifications to be displayed even after an app has been terminated. This will only run if push notifications have been enabled.
 * [NEW] Ability to capture text from a Button.
 * [UPDATE] Updated the one-install implementations to not send this interaction for SDK updates.
 * [UPDATE] Updated all the SDK dependencies to the latest available version. See the integration document for further details.
 * [UPDATE] Improve the application state tracking in Admin mode to avoid bugs when editing interaction names.
 * [UPDATE] Added improvements to how the interaction response is retrieved.
 * [BUGFIX] Resolved an Android O issues where the Admin mode highlighting was not covering the whole screen.
 * [BUGFIX] Resolved an issue where declared whitelist and blacklist links didn't fully work as expected.
 * [BUGFIX] Fixed a memory leak which was occurring when running the Android SDK in Preview Mode.
 * [BUGFIX] Fixed how properties stored in cache are retrieved to ensure the implementation works with React Native implementations. 


#### Version 2.12.0

* [NEW] Ability to whitelist links eligible for identity transfer.
* [NEW] Ability to blacklist links not eligible for identity transfer.
* [BUGFIX] Resolved an issue where the Admin mode poker chip would not appear on screen when a proxy was used.
* [BUGFIX] Resolved an issue where LeakCanary was interfering with the push notification implementation.
* [BUGFIX] Resolved an issue where the poker chip disappeared when re-logging after 'Session Expired' alert was presented.
* [BUGFIX] Resolved networking queuing issues which could lead to OOM when multiple 401 or a connection can not fully be established.
* [BUGFIX] Moved all network code on another thread to avoid StrictMode policy violation errors.
	
#### Version 2.11.0

* [NEW] Ability to codelessly send a push token.
* [NEW] Ability to codelessly show push notifications.
* [NEW] Codeless push message tracking on receival and open. 
* [NEW] Ability to codelessly open deep link passed on a push notification.
* [NEW] Added SDK proguard rules so that the codeless SDK can run in apps which have proguard enabled. 
* [NEW] Ability to track installs using ONE pixel tracking URL. 
* [UPDATE] Updated the properties caching logic to stop sending invalid properties after 2 failed attempts.
* [BUGFIX] Resolved a crash that was occurring when integrating the SDK with React Native apps which use a ViewPager. 

#### Version 2.10.0

* [NEW] Ability to select any elements visible on screen and interactions if no element is found on tap, in Admin mode. 
* [NEW] Ability to retrieve the push token passed to the SDK.
* [BUGFIX] Resolved an issue where the Preview panel could end up showing the incorrect number of points. 
* [BUGFIX] Resolved an issue where the "Group" tab was disappearing in the Admin popover after a rotation event. 
* [BUGFIX] Added a fix to ensure "about:blank" schemes are processed when the codeless identity transfer and link tracking is used. Also we have improved the uri processing by wrapping it into a try-catch block to prevent crashes from occurring.
* [BUGFIX] Added null check support for the childFragment manager to prevent NPE traces during fragments parsing.
* [BUGIFX] Fixed various leaks identified under Admin mode.
* [BUGFIX] Resolved a cast exception triggered when apps use a Navigation Listener.
* [BUGFIX] Resolved a crash which could be experienced when the SDK would run in Admin mode.
* [BUGFIX] Resolved an issue where the poker chip would appear in other apps if the current app was loading a request and the app context was switched.
* [BUGFIX] Fixed an Admin mode issue where the poker chip would appear on screen during request loads, leading to unexpected behaviour. 
* [BUGFIX] Fixed an Admin mode issue where the highlighting wasn’t shown after returning from another app. 
* [BUGFIX] Resolved an Admin mode issue where the ‘Remember Me’ toggle logic was functioning incorrectly. 
* [BUGFIX] Fixed an Admin mode issue where the highlighting was still showing after an error message was displayed.

#### Version 2.9.0

* [NEW] Cross-channel and install tracking using Chrome in apps running on Android 4.0.3.
* [UPDATE] The SDK now stores the thunderhead anonymous identifier depending on the space to which an app belongs to.
* [UPDATE] Updated the SDK to use the Android compile SDK version 25, build tools version 25.0.1, target SDK version to 25 and support library to 25.0.1.
* [UPDATE] Updated the View Pager tracking API to better track child views added and removed from a View Pager.
* [BUGFIX] Resolved attributes list incompatibility with the new design time api.

#### Version 2.8.0

* [NEW] Ability to automatically append a one-tid parameter to http/https links opened from an app to support identity transfer across channels. This feature is achieved using AspectJ.
* [NEW] Ability to automatically send one click interaction to support attribution tracking. This feature is achieved using AspectJ.
* [BUGFIX] Resolved an issue where the poker chip appears in other apps during an Admin mode login session.
* [BUGFIX] Resolved an issue where the loading dialog was showing endlessly after the no internet connection error appeared in Admin mode. 
* [BUGFIX] Fixed a crash which was happening in Admin mode if switching to another app whilst data was being retrieved. 
* [BUGFIX] Resolved an issue where the poker chip would become invisible after a network error would occur in Admin mode, in certain apps. 
* [BUGFIX] Resolved an issue where java.lang.NoSuchFieldException would appear if an app was using nested fragments on an API version lower than 21.
* [BUGIFX] Fixed an issue where some methods would still send runtime data to ONE in Admin mode. 
* [BUGFIX] Resolved an issue where the region popover was not fully displayed on Android 7. 
* [BUGFIX] Fixed an Admin mode crash which was occurring on device rotation.
* [BUGFIX] Resolved several memory leaks which were occurring when running the app in Admin mode.  

#### Version 2.7.6

* [BUGFIX] Removed support for android.support.v4.widget.DrawerLayout.

#### Version 2.7.5

* [UPDATE] Added codeless tracking and capture point support for Sticky Header List Views. 
* [BUGFIX] Resolved an issue where the application underlying interaction blocked the UI.
* [BUGFIX] Resolved an issue where highlighting was not working as expected in Admin mode when the keyboard was up. 
* [BUGFIX] Resolved a crash that was occurring in Admin mode if the credentials where not available.
* [BUGFIX] Resolved an issue where alert messaged could appear unexpectedly on top of the Admin mode popover. 
* [BUGFIX] Resolved an Admin mode issue where the poker chip would end up in an incorrect state. 
* [BUGFIX] Resolved an issue where action bar items were not highlighted correctly in some apps.

#### Version 2.7.4

* [UPDATE] Updated the overlapping elements in cells algorithm to better deal with cases where multiple elements sit on top of each other and require Admin mode access. 
* [UPDATED] Updated the List, Grid and Recycler view data capture algorithms to resolve several data capture bugs observed during a recent integration.
* [BUGFIX] Resolved an User mode bug where the tid was not persisted after an app was terminated.
* [BUGFIX] Resolved an Admin mode bug where the popover was not being presented correctly on screen.
* [BUGFIX] Resolved a crash which was occurring in apps using a particular type of scroll implementation.
* [BUGFIX] Resolved an issue which was preventing Admin users from logging in.
* [BUGFIX] Resolved an issue where the action bar was not correctly highlighted in Admin mode. 
* [BUGFIX] Resolved an issue where the tab bars in some apps where not correctly recognised by the SDK.
* [BUGFIX] Resolved an issue where an Admin user was unable to select navigation drawers items.
* [BUGFIX] Resolved a crash which was occurring sometimes in Admin mode when the device was rotated.
* [BUGFIX] Resolved an issue where the Admin mode window was disappearing on device rotation.

#### Version 2.7.3

* [NEW] Ability to send a push token to ONE. This feature simply exposes the ability to programmatically pass the push token to ONE and will become available as an engagement feature in future ONE releases.
* [UPDATE] Ability to call the SDK public methods from any thread.
* [UPDATE] Ability to capture/tracking views from toolbar and action bar.
* [BUGFIX] Resolved an issue where the SDK could crash when switching from Admin to Preview mode.
* [BUGFIX] Resolved an issue where the Admin mode poker chip was displayed incorrectly in the app.
* [BUGFIX] Resolved an issue where the Admin popover was shown incorrectly on screen. 
* [BUGFIX] Resolved an issue where the keyboard was not hidden when the poker chip was tapped, when running the SDK in Admin mode.

#### Version 2.7.2

* [NEW] Ability to track list items where the label matches the list item width and height.
* [NEW] Ability to send a 'one-click' interaction to ONE for outbound links.
* [UPDATE] Added the clearUserProfile method as part of the queued requests.
* [BUGFIX] Resolved an issue where the the group highlighting wasn’t working correctly when deleting group tracking or capture points on Android 4.1.
* [BUGFIX] Resolved an issue where the Admin mode region popover could end up in an empty state.
* [BUGFIX] Resolved an issue where Admin popover was incorrectly positioned in some apps.
* [BUGFIX] Resolved an issue where the incorrect dialog style was being used, in some apps, when running the SDK in Admin mode.

#### Version 2.7.1

* [NEW] Ability to store username and passwords securely on the device for Admin mode, in order to allow easy access back once a session expires. 
* [UPDATE] Allow developers to pass a URI object into getUriWithOneTid, to enable identity transfer when the Android Uri API is used. 
* [BUGFIX] Resolved an issue where the mini notification was still visible after leaving an interaction.
* [BUGFIX] Resolved an issue where the poker chip remained active when the app lost connectivity and the authentication session expired. 
* [BUGFIX] Resolved an issue where manually triggered interactions were not correctly queued. 
* [BUGFIX] Resolved an issue where the tid was not correctly saved. 

#### Version 2.7.0

* [NEW] Ability to send capture point request prior to tracking point request for recycler views. This helps the SDK align with ONE’s dynamic proposition requirements.
* [NEW] Ability to enter preview mode on long press.
* [UPDATE] Updated the getURLWithOneTid method to only update the ‘one-tid’ parameter value should this already exist in the URL provided.
* [UPDATE] When running the SDK in User mode, view hierarchies will only be automatically traversed if tracking or capture points are returned for an interaction request. 
* [UPDATE] Improved the Preview mode TID view to be automatically updated when a new TID is received should the Preview panel be already opened. 
* [BUGFIX] Fixed an issue where the poker chip could disappear on device rotation. 
* [BUGFIX] Resolved an issue where properties sent using the SDK public methods, were not sent when running the SDK in Preview mode. 
* [BUGFIX] Resolved rotation issues which were causing the Admin popover to be incorrectly displayed under certain circumstances. 
* [BUGFIX] Resolved an issue where the Admin popover was positioned incorrectly on screen.
* [BUGFIX] Fixed rotation issues in the tracking and capture views.	
* [BUGFIX] Resolved an issue where the optimization could appear in Admin mode.

#### Version 2.6.0

* [NEW] Added support for automatic incoming parameter capture - the SDK will now send any parameters passed on a deep link to a ONE base touchpoint.
* [NEW] Added support for outgoing one-tid parameter, for identity transfer, using a public method.
* [NEW] Added support for automatic data capture in recycler views on load and on scroll
* [NEW] Added ability to see the current TID in preview mode.
* [NEW] Added ability to generate a monitor TID link and share this in preview mode.
* [UPDATE] Extended the SDKs programmatic support to allow response retrieval from manually triggered interactions with properties.
* [UPDATE] Aligned the poker chip logic with the iOS implementation.
* [UPDATE] Added further guards against the SDK crashing an app, in case some values are null. 
* [BUGFIX] Resolved an issue where TID was not saved correctly in some instances. 
* [BUGFIX] Resolved an issue where incorrect data was automatically captured when scrolling list really fast. 
* [BUGFIX] Resolved an issue where the automatic capture cache was not functioning correctly. 
* [BUGFIX] Resolved an issue where untracked elements were incorrectly highlighted on some devices.
* [BUGFIX] Resolved an issue where an error was shown when creating a new attribute with a name that already exists. 
* [BUGFIX] Resolved an issue where response codes where not sent to a base interaction. 
* [BUGFIX] Resolved an issue where single tracking events were not sent for radio buttons which sat within list items. 

#### Version 2.5.0

* [NEW] Added support for UTF-8 in mobile assets.
* [NEW] Added support for CSRF headers to all design time APIs.
* [NEW] Added ability to track and capture data from repeating list/grid view lists items and list item elements in both User and Admin mode using groups.
* [UPDATE] Updated the list and grid view data capture algorithms to send captured data on click of a list item. This helps align the implementation with ONE’s dynamic proposition requirements. 
* [UPDATE] Disabled retry on failure for network requests. 
* [UPDATE] Improved several areas of the SDK based on static analysis feedback.
* [BUGFIX] Resolved an issue where the poker chip would remain on screen if the app was exited once connectivity was lost. 
* [BUGFIX] Resolved an issue where the submit button was not functioning correctly in some of the Admin mode views.
* [BUGFIX] Resolved a crash which was happening in apps using a Navigation Drawer.
* [BUGFIX] Fixed a null pointer exception inside the NetworkErrorHandler.
* [BUGFIX] Added a catch for out of memory exceptions in the case there is not enough memory to decode an asset image returned from a mini or full screen optimization. 
* [BUGFIX] Resolved an issue where an empty alert was sometimes shown when adding capture or tracking point in Admin mode. 
* [BUGFIX] Resolved an issue where the data attribute item was switched to another one when searching in Admin mode. 
* [BUGFIX] Resolved an issue where the key icon was not being displayed on the capture point main screen in Admin mode. 
* [BUGFIX] Resolved an issue where data was not being sent in User mode for buttons and cells contained within a toolbar. 

#### Version 2.4.0

* [NEW] Ability to set a mini notification background colour.
* [NEW] Removed single Customer Key and added support capturing into Key attributes.
* [NEW] Ability to set a mini notification timer.
* [NEW] Ability to set a mini notification text size and colour.
* [NEW] Added support for flexible image width in mini notifications.
* [NEW] Ability to send a response code programmatically.
* [NEW] Ability to switch off automatic interaction detection in the SDK.
* [NEW] Ability to select attributes as dynamic propositions.
* [NEW] Support for programmatically triggering interaction requests, for another channel, from within a current mobile touchpoint.
* [NEW] Added support for retrofit 2. 
* [UPDATE] Updated the path generation algorithm to contain layout details. 
* [BUGFIX] Resolved a null pointer exception thrown by the NetworkErrorHandler. 
* [BUGFIX] Resolve an issue where the mini notification was partly hidden.
* [BUGFIX] Fixed an issue where white spaces were not trimmed for capture points and data attribute names.
* [BUGFIX] Resolved an issue where the incorrect colour of the proposition and activity type name was used in Admin mode. 
* [BUGFIX] Resolved an issue where the incorrect colour was used for the selection tick marker. 
* [BUGFIX] Resolved an issue where an appropriate error message wasn’t shown when the list of attributes could not be retrieved.
* [BUGFIX] Resolved an issue where the search field is not available after pressing the hardware back button.
* [BUGFIX] Resolved an issue where some labels where incorrectly placed in the Data Attribute screen
* [BUGFIX] Resolved an issue where multiple preview panels were shown after launching the app for the first time
* [BUGFIX] Resolved an issue where the search box close button was incorrectly placed
* [BUGFIX] Resolved an issue where the 'No results found' label was not visible in some cases where no data was available in the lists
* [BUGFIX] Resolved an issue where the app used to crash when running the stress testing on the Proposition list view
* [BUGFIX] Resolved an issue where the incorrect placeholder was used for the activity search field
* [BUGFIX] Resolved an issue where pressing the hardware back button didn’t fully return the user to the initial state
* [BUGFIX] Resolved an issue where the cursor did’t focus on the search field when navigating through proposition levels
* [BUGFIX] Resolved an issue where 'Create new attribute' button is visible when searching


#### Version 2.3.1

* [BUGFIX] Resolved 401 responses for design time request.

#### Version 2.3.0

* [NEW] Extended SDKs programmatic support to allow response retrieval from manually triggered interactions.
* [NEW] Ability to search attribute, activity and proposition lists.
* [UPDATE] Added support for tracking expandable lists and spinner lists.
* [BUGFIX] Fixed an issue where Popup Menu Android element was highlighted in orange but couldn’t be tracked.
* [BUGFIX] Fixed an issue where switching between the element and region tabs in the Admin popover caused the app to crash.
* [BUGFIX] Resolved issue where an Admin user could open multiple Proposition lists.
* [BUGFIX] Resolved issue where some list dividers where not being shown in Android 6.0.
* [BUGFIX] Resolved issue where region popover would sometimes be shown off screen.
* [BUGFIX] Resolved issue where optimization would interfere with the preview panel.
* [BUGFIX] Fixed multiple preview panels appearing on poker chip tap.
* [BUGFIX] Resolved an issue where the optimization point list was cleared when editing the region name.
* [BUGFIX] Resolved an issue where a black screen sometimes would appear when edit mode was on.
* [BUGFIX] Resolved an issue where the region tracking point counter didn’t decrease when deleting a tracking point.

#### Version 2.2.0

* [NEW] Ability to add multiple tracking points in Admin mode.
* [NEW] Ability to see optimization points in the Admin popover.
* [NEW] Ability to auto-recognise top most region on a screen.
* [UPDATE] Updated Preview mode UI to align with new Tagger.
* [UPDATE] Updated Edit mode Admin popover to align with new Tagger.
* [UPDATE] Updated Edit mode highlighting to align with new Tagger.
* [BUGFIX] Resolved an issue where the optimization point options were shown under the element section.
* [BUGFIX] Fixed Preview mode issue where selecting the current release didn’t send a new interaction request.
* [BUGFIX] Fixed date capturing for API 21 by adding additional listeners.
* [BUGFIX] Fixed a crash which was occurring sometimes when renaming a region.
* [BUGFIX] Fixed a crash which was occurring sometimes when deleting a point.
* [BUGFIX] Fixed an issue where Admin mode highlighting wasn’t working properly when deleting a point.
* [BUGFIX] Fixed an issue where long pressing the poker chip would enter an edit mode state with preview still active.
* [BUGFIX] Resolved crash which was occurring sometimes in the list of data attributes.
* [BUGFIX] Fixed bug which led to multiple propositions and activity types to appear to be selected in a list.
* [BUGFIX] Resolved an issue where the SDK sent unidentified capture points to ONE in certain apps.
* [BUGFIX] Fixed an issue where the click event tracking was not properly functioning on RecyclerView element.
* [BUGFIX] Fixed issue where an user was unable to click the Exit button in Preview mode.
* [BUGFIX] Fixed an issue where a notification is still shown after exiting Preview mode.

#### Version 2.1.0

* [NEW] Ability to preview in the works.
* [NEW] Ability to preview multiple releases.
* [UPDATE] Debug messages are logged based on BuildConfig.DEBUG_LOG condition defined in gradle.build and will not be printed to log when a release build of the SDK is used.
* [UPDATE] Improved Activity and Propositions lists to be sorted alphabetically.
* [UPDATE] Improved nested fragment detection algorithm. 
* [UPDATE] Added support for AppCompat switches. 
* [UPDATE] Improved highlighting algorithm to not highlight elements which are not currently visible on screen. 
* [BUGFIX] Fixed an issue with the region popover not always being displayed when selecting a region. 
* [BUGFIX] Fixed an issue with the track cell disappearing after deleting the region tracking point. 
* [BUGFIX] Fixed an issue where the floating action bar button could not be selected in some apps. 
* [BUGFIX] Fixed null pointer exception which was occurring in certain apps where the view object was not available on load of the activity.
* [BUGFIX] Fixed an issue where some cells where not being highlighted correctly in certain scenarios.
* [BUGFIX] Resolved a memory leak in the class which provides the Admin mode highlighting.
* [BUGFIX] Fixed an issues where multiple requests were being sent to ONE during scrolling of a list.
* [BUGFIX] Fixed an issues where element items which hold the element path could be created twice within an interaction.
* [BUGFIX] Fixed an issues with mini notification text being cropped when on 3 lines. 
* [BUGFIX] Fixed path generation algorithm to correctly create element paths and remove them when no longer in use.  
* [BUGFIX] Added a fix around how the SDK tracks view pager interaction which have not been initialised at load time. This fix prevents the client app from crashing. 
* [BUGFIX] The SDK will no longer highlight views which are set as invisible or gone. 

#### Version 2.0.1

* [NEW] Ability to ignore interactions from being tracked.
* [NEW] Ability to retrieve the tid using getTid public method.
* [NEW] Ability to retrieve the framework version using the frameworkVersion public method. 
* [NEW] Ability to review debug logs only.
* [UPDATE] The algorithm which identifies fragments in a view pager has been improved to better recognise fragments automatically.
* [UPDATE] Elements which are added on screen at a later stage, are now also allocated a path automatically. 
* [UPDATE] Added support for tracking and capturing data from custom ExpandableListView, ListView, GridView, AbsListView, Spinner, StackView and RecyclerView.
* [UPDATE] Added support for capturing text from AppCompatEditText.
* [UPDATE] Added support for capturing text from custom TextViews. 
* [UPDATE] Added support for tracking NavigationMenuItemViews. 
* [BUGFIX] Fixed crashed caused by pressing a Menu Item.  
* [BUGFIX] Added exception around CoordinatorLayout, to avoid crashes.
* [BUGFIX] Fixed issue with the poker chip disappearing in apps which use floating buttons or loading activities.
* [BUGFIX] Added recursive check around interaction removal to ensure the correct interaction is highlighted given some are removed.
* [BUGFIX] Fixed issue with invisible and/or hidden elements being highlighted.
* [BUGFIX] Fixed crash which was occurring when pressing Menu Item buttons.
* [BUGFIX] Fixed issues around the full screen notification not appearing on screen under certain circumstance.
* [BUGFIX] Added fixes to the view pager interaction identification algorithm to allow business user to correctly select an interaction.
* [BUGFIX] Fixed issue which prevented the whole interaction to be highlighted once the screen has been rotated.
* [BUGFIX] Decreased full screen notification opacity.
* [BUGFIX] Fixed inability to select some capture point elements. 
* [BUGFIX] Fixed highlighting algorithm to enable highlighting of nested interaction.  
* [BUGFIX] Fixed issue with the poker chip being displayed when the app is in the background. 
* [BUGFIX] Fixed highlighting issues with layouts sitting on top of other layouts.

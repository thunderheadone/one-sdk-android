package com.thunderhead.dynamicconfigurationexample

import androidx.multidex.MultiDexApplication
import com.thunderhead.One
import com.thunderhead.OneLogLevel
import com.thunderhead.OneModes
import com.thunderhead.android.api.oneConfiguration
import com.thunderhead.android.api.oneConfigure
import java.net.URI

/**
 * If you are running in Admin mode on Android 6.0+, you will need to enable the “draw over other apps” permission via your OS settings.
 */
val oneModeSetting: OneModes =
    if (BuildConfig.TH_SDK_ADMIN_MODE) OneModes.ADMIN_MODE else OneModes.USER_MODE

/**
 * Dynamic configuration example
 *
 * This example app demonstrates how to dynamically configure the ONE SDK when required.
 *
 * This app showcases the scenario where it may be useful to reconfigure the SDK to correspond to device region settings.
 * https://github.com/thunderheadone/one-sdk-android/tree/master/examples/dynamic-configuration-example
 */

/**
 * Each ONE configuration corresponds to a region.
 * Note: You will need to maintain and store your own SDK configurations as the SDK does not manage them.
 *
 * TODO: Configure with your own ONE credentials in gradle.properties.
 */
val OneSdkConfigurations = mapOf(
    "United States" to oneConfiguration {
        siteKey = BuildConfig.TH_SDK_SITEKEY_USA
        touchpoint = URI(BuildConfig.TH_SDK_URI_USA)
        userId = BuildConfig.TH_SDK_USER_USA
        apiKey = BuildConfig.TH_SDK_APIKEY_USA
        sharedSecret = BuildConfig.TH_SDK_SHAREDSECRET_USA
        host = URI(BuildConfig.TH_SDK_HOST_USA)
        mode = oneModeSetting
    },
    "Europe" to oneConfiguration {
        siteKey = BuildConfig.TH_SDK_SITEKEY_EUR
        touchpoint = URI(BuildConfig.TH_SDK_URI_EUR)
        userId = BuildConfig.TH_SDK_USER_EUR
        apiKey = BuildConfig.TH_SDK_APIKEY_EUR
        sharedSecret = BuildConfig.TH_SDK_SHAREDSECRET_EUR
        host = URI(BuildConfig.TH_SDK_HOST_EUR)
        mode = oneModeSetting
    },
    "United Kingdom" to oneConfiguration {
        siteKey = BuildConfig.TH_SDK_SITEKEY_UK
        touchpoint = URI(BuildConfig.TH_SDK_URI_UK)
        userId = BuildConfig.TH_SDK_USER_UK
        apiKey = BuildConfig.TH_SDK_APIKEY_UK
        sharedSecret = BuildConfig.TH_SDK_SHAREDSECRET_UK
        host = URI(BuildConfig.TH_SDK_HOST_UK)
        mode = oneModeSetting
    }
)

class DynamicConfigurationApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        // Enable logs before SDK is configured in order to log it to console.
        One.setLogLevel(OneLogLevel.FRAMEWORK)

        /**
         * SDK initialization is not required.
         *
         * The Thunderhead SDK is automatically initialized in an `un-configured` state.
         *  While `un-configured`, the SDK will continue to locally queue end-user data and will upload the data to server once
         *  the SDK is configured with valid parameters.
         *  This can be disabled at any time by setting the `oneOptOutConfiguration` to `true`.
         *
         * The SDK does not support partial, or piecemeal, configuration. All parameters must be provided, either all valid or invalid (`empty string` or `null`).
         * When configured with invalid parameters, it will set the SDK into an `un-configured` state.
         *
         * https://github.com/thunderheadone/one-sdk-android/tree/master/examples/dynamic-configuration-example
         */
        oneConfigure {
            siteKey = ""
            apiKey = ""
            sharedSecret = ""
            userId = ""
            host = null
            touchpoint = null
            mode = oneModeSetting
        }

        /**
         * Uncomment below to disable automatic Interaction detection.
         *  By disabling automatic Interaction detection, the SDK will no longer automatically send
         *  Interaction requests as activities and/or fragments are presented on screen.
         *  It becomes your responsibility to send them when needed by using the send Interaction methods outlined here:
         *
         * https://github.com/thunderheadone/one-sdk-android#send-an-interaction-request-programmatically
         *
         * You can set this back to false at any point to restart automatic Interaction detection.
         */
        // oneConfigureCodelessInteractionTracking {
        //     disableCodelessInteractionTracking = true
        // }

        /**
         * Uncomment below to programmatically erase the user profile data
         *  This method removes tid from local storage only.
         *  For instructions on how to completely remove a user's data from Thunderhead ONE or Salesforce Interaction Studio - see our api documentation.
         *
         * https://thunderheadone.github.io/one-api/#operation/delete
         */
        // One.clearUserProfile()
    }
}
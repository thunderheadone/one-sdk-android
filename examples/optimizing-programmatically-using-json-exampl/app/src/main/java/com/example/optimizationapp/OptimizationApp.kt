package com.example.optimizationapp

import android.app.Application
import com.thunderhead.One
import com.thunderhead.OneLogLevel
import com.thunderhead.OneModes
import java.lang.IllegalStateException

class OptimizationApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // example init
        One.getInstance(this)?.run {
            init(
                BuildConfig.thunderheadSiteKey,
                BuildConfig.thunderheadTouchpoint,
                BuildConfig.thunderheadApiKey,
                BuildConfig.thunderheadSharedSecret,
                BuildConfig.thunderheadUser,
                if(BuildConfig.thunderheadAdminMode) OneModes.ADMIN_MODE else OneModes.USER_MODE,
                BuildConfig.thunderheadHost
            )

            setLogLevel(OneLogLevel.ALL)
        } ?: throw IllegalStateException("Thunderhead SDK could not be initialized! Contact support.")
    }
}
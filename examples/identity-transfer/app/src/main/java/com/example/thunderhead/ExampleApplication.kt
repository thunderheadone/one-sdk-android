package com.example.thunderhead

import android.app.Application
import com.example.thunderhead.BuildConfig
import com.thunderhead.One
import com.thunderhead.OneLogLevel
import com.thunderhead.OneModes
import com.thunderhead.android.api.oneConfigure
import java.net.URI

class ExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        One.setLogLevel(OneLogLevel.ALL)

        oneConfigure {
            siteKey = BuildConfig.thunderheadSiteKey
            apiKey = BuildConfig.thunderheadApiKey
            sharedSecret = BuildConfig.thunderheadSharedSecret
            userId = BuildConfig.thunderheadUser
            host = URI(BuildConfig.thunderheadHost)
            touchpoint = URI(BuildConfig.thunderheadTouchpoint)
            mode = if(BuildConfig.thunderheadAdminMode) OneModes.ADMIN_MODE else OneModes.USER_MODE
        }
    }
}
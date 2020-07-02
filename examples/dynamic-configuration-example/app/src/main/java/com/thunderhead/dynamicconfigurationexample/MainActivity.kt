package com.thunderhead.dynamicconfigurationexample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.thunderhead.android.api.oneConfigure
import com.thunderhead.android.api.oneGetConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var currentRegion: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentRegion = getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        ).getString(getString(R.string.region_key), null)

        currentRegion?.let { region ->
            regionTextView.text = region
            selectButton.text = "CHANGE"

            val newConfiguration = OneSdkConfigurations[region]
            val currentConfiguration = oneGetConfiguration()
            newConfiguration?.let { newConfig ->
                if (currentConfiguration != newConfig) {
                    Log.v("Dynamic Config", "Thunderhead SDK is configured to `$region`: $newConfig")
                    oneConfigure {
                        siteKey = newConfig.siteKey
                        touchpoint = newConfig.touchpoint
                        userId = newConfig.userId
                        apiKey = newConfig.apiKey
                        sharedSecret = newConfig.sharedSecret
                        host = newConfig.host
                        mode = oneModeSetting
                    }
                }
            }
        }

        supportActionBar?.hide()
    }

    /** Called upon tapping the SELECT button */
    fun showChangeRegionActivity(view: View) {
        val intent = Intent(this, ChangeRegionActivity::class.java)
        startActivityForResult(intent, 1)
    }

    /** Handle SDK reconfiguration here. */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1 -> {
                val regionExtra = data?.getStringExtra("regionValue")
                regionExtra?.let { newRegion ->
                    currentRegion = newRegion
                    regionTextView.text = newRegion
                    selectButton.text = "CHANGE"

                    // Reconfigure the SDK that corresponds to the region selected.
                    val newConfiguration = OneSdkConfigurations[newRegion]
                    val currentConfiguration = oneGetConfiguration()
                    newConfiguration?.let { newConfig ->
                        if (currentConfiguration != newConfig) {
                            Log.v("Dynamic Config", "Thunderhead SDK is configured to `$newRegion`: $newConfig")
                            oneConfigure {
                                siteKey = newConfig.siteKey
                                touchpoint = newConfig.touchpoint
                                userId = newConfig.userId
                                apiKey = newConfig.apiKey
                                sharedSecret = newConfig.sharedSecret
                                host = newConfig.host
                                mode = oneModeSetting
                            }
                        }
                    }

                    // Store region setting.
                    val sharedPref = getSharedPreferences(
                        getString(R.string.preference_file_key),
                        Context.MODE_PRIVATE
                    ) ?: return

                    with(sharedPref.edit()) {
                        putString(getString(R.string.region_key), newRegion)
                        apply()
                    }
                }
            }
        }
    }
}

package com.siampiwat.indoormapdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.siampiwat.indoormapsdk.IndoorMapSDK
import com.siampiwat.indoormapsdk.data.appenum.SPWDepartmentStoreType
import com.siampiwat.indoormapsdk.data.appenum.SPWLanguage
import com.siampiwat.indoormapsdk.data.model.SPWAISLocation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        /////////////////////////////////////////////////////
        // Initialize SDK
        /////////////////////////////////////////////////////
        textview_status.text = getString(R.string.activity_main_tv_status_init)
        val jsonString = AssetHelper.readJsonString(this, "shop.json")
        IndoorMapSDK.initialize(SPWDepartmentStoreType.SIAM_PARAGON, jsonString!!, SPWLanguage.TH)
        /////////////////////////////////////////////////////

        /////////////////////////////////////////////////////
        // Get Venue
        /////////////////////////////////////////////////////
        textview_status.text = getString(R.string.activity_main_tv_status_get_venue)
        IndoorMapSDK.getInstance().getVenue(object : IndoorMapSDK.GetVenueCallback {
            override fun onSuccess() {
                textview_status.text = getString(R.string.activity_main_tv_status_ready)
                button_map.isEnabled = true

                /////////////////////////////////////////////////////
                // Get all stores
                /////////////////////////////////////////////////////
                val stores = IndoorMapSDK.getInstance().getStores()
                /////////////////////////////////////////////////////

                /////////////////////////////////////////////////////
                // Set origin by store
                /////////////////////////////////////////////////////
                // IndoorMapSDK.getInstance().setOriginByStore(stores[0])
                /////////////////////////////////////////////////////

                /////////////////////////////////////////////////////
                // Set origin by user's location
                /////////////////////////////////////////////////////
                val userLocation =
                    SPWAISLocation(
                        isIndoor = true,
                        latitude = 13.746600,
                        longitude = 100.534307,
                        buildId = "4409",
                        buildName = "Siam Paragon",
                        floorId = "8288",
                        floorNumber = "0"
                    )
                IndoorMapSDK.getInstance().setOriginByLocation(userLocation)
                /////////////////////////////////////////////////////

                /////////////////////////////////////////////////////
                // Set destination by store
                /////////////////////////////////////////////////////
                IndoorMapSDK.getInstance().setDestinationByStore(stores[1])
                /////////////////////////////////////////////////////
            }

            override fun onFailed(e: Throwable) {
                textview_status.text = e.toString()
                Log.e(TAG, "onFailed")
            }
        })
        /////////////////////////////////////////////////////

        button_map.setOnClickListener { openMapActivity() }
    }

    private fun openMapActivity() {
        startActivity(Intent(this@MainActivity, MyIndoorMapActivity::class.java))
    }

}
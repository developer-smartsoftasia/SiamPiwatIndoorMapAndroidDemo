package com.siampiwat.indoormapdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.siampiwat.indoormapsdk.IndoorMapSDK
import com.siampiwat.indoormapsdk.data.appenum.SPWApplicationSlug
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
        IndoorMapSDK.initialize(
            spwApplicationSlug = SPWApplicationSlug.ONE_SIAM,
            stores = jsonString!!,
            spwLanguage = SPWLanguage.TH,
            debug = true
        )
        Log.i(TAG, "Initialize the IndoorMapSDK")
        /////////////////////////////////////////////////////

        /////////////////////////////////////////////////////
        // Get Venue
        /////////////////////////////////////////////////////
        textview_status.text = getString(R.string.activity_main_tv_status_get_venue)
        Log.i(TAG, "Getting venue")
        IndoorMapSDK.getInstance().getVenue(object : IndoorMapSDK.GetVenueCallback {
            override fun onSuccess() {
                textview_status.text = getString(R.string.activity_main_tv_status_ready)
                button_map.isEnabled = true

                /////////////////////////////////////////////////////
                // Get current department store
                /////////////////////////////////////////////////////
                val currentDepartmentStore = IndoorMapSDK.getInstance().getDepartmentStore()
                Log.i(TAG, "Current department store : $currentDepartmentStore")
                /////////////////////////////////////////////////////

                /////////////////////////////////////////////////////
                // Set department store to 'SPWDepartmentStoreType.SIAM_DISCOVERY'
                /////////////////////////////////////////////////////
                val spwDepartmentStore = IndoorMapSDK.getInstance()
                    .getDepartmentStoreBySlug(SPWDepartmentStoreType.SIAM_DISCOVERY)
                spwDepartmentStore?.let {
                    Log.i(TAG, "Set department store to : $it")
                    IndoorMapSDK.getInstance().setDepartmentStore(it)
                }
                /////////////////////////////////////////////////////

                /////////////////////////////////////////////////////
                // Get all stores from current department store
                /////////////////////////////////////////////////////
                val stores = IndoorMapSDK.getInstance().getStores()
                Log.i(TAG, "Get stores from current department store : ${stores.size}")
                /////////////////////////////////////////////////////

                /////////////////////////////////////////////////////
                // Set destination by store
                /////////////////////////////////////////////////////
                IndoorMapSDK.getInstance().setDestinationByStore(stores[0])
                Log.i(TAG, "Set destination as : ${stores[0]}")
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
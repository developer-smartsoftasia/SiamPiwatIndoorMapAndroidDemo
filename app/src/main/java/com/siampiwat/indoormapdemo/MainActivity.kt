package com.siampiwat.indoormapdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.siampiwat.indoormapsdk.IndoorMapSDK
import com.siampiwat.indoormapsdk.data.appenum.SPWDepartmentStoreType
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
        IndoorMapSDK.initialize(SPWDepartmentStoreType.SIAM_PARAGON, "")
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
                // Set origin store
                /////////////////////////////////////////////////////
                IndoorMapSDK.getInstance().setOriginStore(stores[0])
                /////////////////////////////////////////////////////

                /////////////////////////////////////////////////////
                // Set origin store
                /////////////////////////////////////////////////////
                IndoorMapSDK.getInstance().setDestinationStore(stores[1])
                /////////////////////////////////////////////////////

                /////////////////////////////////////////////////////
                // Start Indoor map
                /////////////////////////////////////////////////////

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
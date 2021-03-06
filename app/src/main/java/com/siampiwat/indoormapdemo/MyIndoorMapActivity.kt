package com.siampiwat.indoormapdemo

import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.siampiwat.indoormapsdk.IndoorMapSDK
import com.siampiwat.indoormapsdk.data.model.SPWAISLocation
import com.siampiwat.indoormapsdk.presentation.view.indoormap.IndoorMapActivity

class MyIndoorMapActivity : IndoorMapActivity() {

    companion object {
        const val TAG = "MyIndoorMapActivity"
    }

    private var userLocation = SPWAISLocation(
        isIndoor = true,
        latitude = 13.746600,
        longitude = 100.534307,
        buildId = "4409",
        buildName = "Siam Paragon",
        floorId = "8288",
        floorNumber = "0"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            setUserLocation()
        }, 5000)
    }

    /////////////////////////////////////////////////////
    // Set user's location
    /////////////////////////////////////////////////////
    private fun setUserLocation() {
        IndoorMapSDK.getInstance().setUserLocation(userLocation)
    }
    /////////////////////////////////////////////////////

    override fun onIndoorMapError(exception: Exception) {
        Log.e(TAG, "onIndoorMapError : $exception")
    }
}
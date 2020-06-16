package com.siampiwat.indoormapdemo

import android.util.Log
import com.siampiwat.indoormapsdk.presentation.indoormap.IndoorMapActivity

class MyIndoorMapActivity : IndoorMapActivity() {
    companion object {
        const val TAG = "MyIndoorMapActivity"
    }

    override fun onIndoorMapError(exception: Exception) {
        Log.e(TAG, "onIndoorMapError : $exception")
    }
}
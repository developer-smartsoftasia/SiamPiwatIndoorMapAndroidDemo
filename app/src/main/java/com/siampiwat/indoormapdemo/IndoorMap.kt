package com.siampiwat.indoormapdemo

import android.app.Application
import com.siampiwat.indoormapsdk.IndoorMapSDK

class IndoorMap : Application() {
    override fun onCreate() {
        super.onCreate()
        IndoorMapSDK.init(this)
    }
}
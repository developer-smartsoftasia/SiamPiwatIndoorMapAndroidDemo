package com.siampiwat.indoormapdemo

import android.content.Context
import java.io.InputStream

object AssetHelper {
    fun readJsonString(context: Context, fileName: String): String? {
        val json: String?
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}
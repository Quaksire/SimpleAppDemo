package com.quaksire.simpleappdemo.utils

import android.content.Context
import java.io.InputStream

/**
 * Created by Julio.
 */
class RestServiceTestHelper {
    companion object {
        @Throws(Exception::class)
        private fun convertStreamToString(inputStream: InputStream): String {
            return inputStream.bufferedReader().use { it.readText() }
        }

        @Throws(Exception::class)
        fun getStringFromFile(context: Context, filePath: String): String {
            val stream = context.resources.assets.open(filePath)

            val ret = convertStreamToString(stream)
            //Make sure you close all streams.
            stream.close()
            return ret
        }
    }
}
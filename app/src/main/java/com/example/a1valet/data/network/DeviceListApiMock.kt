package com.example.a1valet.data.network

import android.content.Context
import com.example.a1valet.data.models.DeviceListModel
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

/**
 * In Actual Scenario this class will be replaced with retrofit/volley api code to fetch data from server.
 * As of now, I am reading the dummy data from device_data.json file from assets folder.
 */

class DeviceListApiMock @Inject constructor(@ApplicationContext val context: Context) {

    fun getDeviceListData(): DeviceListModel? {
        val itemList: DeviceListModel
        try {
            val jsonString =
                context.assets?.open("device_data.json")?.bufferedReader()
                    .use { it?.readText() }
            val gson = Gson()
            itemList = gson.fromJson(jsonString, DeviceListModel::class.java)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return itemList
    }
}
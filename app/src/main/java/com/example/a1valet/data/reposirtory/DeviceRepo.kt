package com.example.a1valet.data.reposirtory

import androidx.lifecycle.LiveData
import com.example.a1valet.data.db.DeviceDB
import com.example.a1valet.data.models.Device
import com.example.a1valet.data.network.DeviceListApiMock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repo class to fetch data.
 * This layer is manage the data endpoints and has access of both network and DB layers.
 *
 * In this assignment im am taking Room DB as single source of truth.
 * So, first time when DB is empty, I are fetching the data from data.json file and inserting it in DB
 * and then every screen is getting the data from DB only.
 *
 *
 */

class DeviceRepo @Inject constructor(
    private val apiMock: DeviceListApiMock,
    private val db: DeviceDB
) {

    suspend fun getDevicesInfo() {
        withContext(Dispatchers.IO) {
            if (db.deviceDao().getDevicesCount() == 0) {
                apiMock.getDeviceListData()?.devices?.let {
                    db.deviceDao().insertAll(it)
                }
            }
        }
    }

    fun getDeviceDetails(deviceId: String): LiveData<Device> {
        return db.deviceDao().getDeviceDetails(deviceId)
    }

    suspend fun toggleFavourite(selectedPropertyId: String, isFav: Boolean) {
        withContext(Dispatchers.IO) {
            db.deviceDao().toggleFavourite(selectedPropertyId, isFav)
        }
    }

    suspend fun resetFavDevices() {
        withContext(Dispatchers.IO) {
            db.deviceDao().resetFavDevices()
        }
    }

    fun getFilteredDevices(filter: String): LiveData<List<Device>> {
        return db.deviceDao().getFilteredDevices(filter)
    }

    fun getFilteredFavDevices(filter: String): LiveData<List<Device>> {
        return db.deviceDao().getFilteredFavDevices(filter)
    }
}
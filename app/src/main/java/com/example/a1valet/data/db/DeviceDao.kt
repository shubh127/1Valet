package com.example.a1valet.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a1valet.data.models.Device

/**
 * Data Access Object for Room DB.
 * This file includes the methods to insert fetch or save the data to DB
 */

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Device>)

    @Query("SELECT * FROM device WHERE title LIKE :filter ORDER BY _id")
    fun getFilteredDevices(filter: String): LiveData<List<Device>>

    @Query("SELECT * FROM device where id = :id")
    fun getDeviceDetails(id: String): LiveData<Device>

    @Query("SELECT count(*) FROM device")
    fun getDevicesCount(): Int

    @Query("UPDATE device SET isFav=:isFav WHERE id = :id")
    suspend fun toggleFavourite(id: String, isFav: Boolean): Int

    @Query("SELECT * FROM device where isFav = 1 AND title LIKE :filter ORDER BY _id")
    fun getFilteredFavDevices(filter: String): LiveData<List<Device>>

    @Query("UPDATE device SET isFav= 0")
    fun resetFavDevices()
}
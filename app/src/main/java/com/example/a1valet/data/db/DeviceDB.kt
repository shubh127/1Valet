package com.example.a1valet.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a1valet.data.models.Device

/**
 * The Actual Room DB Class
 */

@Database(
    entities = [Device::class],
    version = 1,
    exportSchema = false
)
abstract class DeviceDB : RoomDatabase() {
    abstract fun deviceDao(): DeviceDao

    companion object {
        @Volatile
        private var instance: DeviceDB? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                DeviceDB::class.java,
                "DeviceDb"
            ).build()
    }
}
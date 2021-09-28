package com.example.a1valet.di

import android.content.Context
import com.example.a1valet.data.db.DeviceDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * class to inject Room DB dependency
 * */

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun providePropertyDb(@ApplicationContext context: Context): DeviceDB {
        return DeviceDB(context)
    }
}
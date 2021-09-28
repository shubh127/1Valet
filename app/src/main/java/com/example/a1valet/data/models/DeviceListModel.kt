package com.example.a1valet.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Entity class for Room DB.
 * This data class holds all the columns for a DB table
 * */

class DeviceListModel {
    val devices: List<Device> = listOf()
}

@Entity(tableName = "device")
data class Device(
    @PrimaryKey(autoGenerate = true)
    val _id: Long,
    @SerializedName("Id")
    val id: String? = null,
    @SerializedName("Type")
    val type: String? = null,
    @SerializedName("Price")
    val price: Int? = null,
    @SerializedName("Currency")
    val currency: String? = null,
    val isFav: Boolean = false,
    @SerializedName("imageUrl")
    val imgURL: String? = null,
    @SerializedName("Title")
    val title: String? = null,
    @SerializedName("Description")
    val description: String? = null
)
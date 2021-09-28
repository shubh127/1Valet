package com.example.a1valet.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1valet.data.models.Device
import com.example.a1valet.data.reposirtory.DeviceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceDetailsViewModel @Inject constructor(private val repository: DeviceRepo) : ViewModel() {

    fun getDeviceDetails(deviceId: String): LiveData<Device> {
        return repository.getDeviceDetails(deviceId)
    }

    fun toggleFavourite(selectedPropertyId: String, isFav: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleFavourite(selectedPropertyId, isFav)
        }
    }
}
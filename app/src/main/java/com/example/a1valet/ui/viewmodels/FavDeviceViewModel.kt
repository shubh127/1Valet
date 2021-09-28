package com.example.a1valet.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.a1valet.data.models.Device
import com.example.a1valet.data.reposirtory.DeviceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavDeviceViewModel @Inject constructor(private val repository: DeviceRepo) : ViewModel() {
    var filteredFavDevices: LiveData<List<Device>>
    var filter = MutableLiveData("%")

    init {
        filteredFavDevices = Transformations.switchMap(filter) { filter ->
            repository.getFilteredFavDevices(filter)
        }
    }

    fun setFilter(newFilter: String) {
        val f = when {
            newFilter.isEmpty() -> "%"
            else -> "$newFilter%"
        }
        filter.postValue(f)
    }
}
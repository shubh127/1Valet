package com.example.a1valet.ui.viewmodels

import androidx.lifecycle.*
import com.example.a1valet.data.models.Device
import com.example.a1valet.data.reposirtory.DeviceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceListViewModel @Inject constructor(private val repository: DeviceRepo) : ViewModel() {

    var allItemsFiltered: LiveData<List<Device>>
    var filter = MutableLiveData("%")

    init {
        allItemsFiltered = Transformations.switchMap(filter) { filter ->
            repository.getFilteredDevices(filter)
        }
    }

    fun getDevicesInfo() {
        viewModelScope.launch {
            repository.getDevicesInfo()
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
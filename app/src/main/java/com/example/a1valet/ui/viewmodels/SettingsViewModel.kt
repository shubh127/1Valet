package com.example.a1valet.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1valet.data.reposirtory.DeviceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: DeviceRepo) : ViewModel() {

    fun resetFavDevices() {
        viewModelScope.launch {
            repository.resetFavDevices()
        }
    }
}
package com.example.travel.ui.editor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.travel.dto.MapMarker
import com.example.travel.repository.MapRepository
import com.example.travel.repository.MapRepositorySQLImpl
import kotlinx.coroutines.launch

class EditMarkerViewModel(application: Application) : AndroidViewModel(application) {
    val currentMarker = MutableLiveData(emptyMapMarker)

    val repository: MapRepository = MapRepositorySQLImpl(application)

    fun setCurrentMarker(marker: MapMarker) {
        currentMarker.value = marker
    }

    fun saveCurrentMarker()  = viewModelScope.launch {
        currentMarker.value?.let {
            repository.saveMarker(it)
        }
    }

    fun loadCurrentMarker(id: Long) = viewModelScope.launch {
        currentMarker.value?.let {
            currentMarker.value = repository.getMarkerById(id)
        }
    }

    fun removeCurrentMarker() = viewModelScope.launch {
        currentMarker.value?.let {
            repository.removeMarkerById(it.id)
        }
    }

}

val emptyMapMarker: MapMarker = MapMarker(
    id = 0,
    lat = 0.0,
    lng = 0.0
)
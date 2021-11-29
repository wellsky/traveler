package com.example.travel.ui.map

import android.app.Application
import androidx.lifecycle.*
import com.example.travel.repository.MapRepository
import com.example.travel.repository.MapRepositorySQLImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MapRepository = MapRepositorySQLImpl(application)
    val data = repository.data.asLiveData(Dispatchers.Default)
}
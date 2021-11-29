package com.example.travel.repository

import com.example.travel.dto.MapMarker
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    val data: Flow<List<MapMarker>>

    suspend fun saveMarker(marker: MapMarker)
    suspend fun getMarkerById(id: Long): MapMarker
    suspend fun removeMarkerById(id: Long)
}
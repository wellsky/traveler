package com.example.travel.repository

import com.example.travel.dto.MapMarker
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    val data: Flow<List<MapMarker>>
}
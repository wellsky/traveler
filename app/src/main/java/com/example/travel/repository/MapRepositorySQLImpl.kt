package com.example.travel.repository


import android.content.Context
import com.example.travel.db.AppDb
import com.example.travel.dto.MapMarker
import com.example.travel.entity.MapMarkerEntity
import com.example.travel.entity.toDto
import com.example.travel.entity.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MapRepositorySQLImpl(context: Context): MapRepository {
    val dao = AppDb.getInstance(context).mapMarkerDao()

    override val data: Flow<List<MapMarker>> = dao.getAll().map(List<MapMarkerEntity>::toDto).flowOn(Dispatchers.Default)

    override suspend fun saveMarker(marker: MapMarker) {
        dao.insert(marker.toEntity())
    }

    override suspend fun getMarkerById(id: Long): MapMarker {
        return dao.getById(id).toDto()
    }

    override suspend fun removeMarkerById(id: Long) {
        dao.removeById(id)
    }

}
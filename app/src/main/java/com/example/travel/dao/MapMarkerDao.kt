package com.example.travel.dao

import androidx.room.*
import com.example.travel.entity.MapMarkerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MapMarkerDao {
    @Query("SELECT * FROM MapMarkerEntity")
    fun getAll(): Flow<List<MapMarkerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(marker: MapMarkerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(markers: List<MapMarkerEntity>)

    @Query("DELETE FROM MapMarkerEntity")
    suspend fun clear()
}
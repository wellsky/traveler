package com.example.travel.dao

import androidx.room.*
import com.example.travel.entity.MapMarkerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MapMarkerDao {
    @Query("SELECT * FROM MapMarkerEntity")
    fun getAll(): Flow<List<MapMarkerEntity>>

    @Query("SELECT * FROM MapMarkerEntity WHERE id = :id")
    suspend fun getById(id: Long): MapMarkerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(marker: MapMarkerEntity)

    @Query("DELETE FROM MapMarkerEntity WHERE id = :id")
    suspend fun removeById(id: Long)
}
package com.example.travel.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.travel.dto.MapMarker

@Entity
data class MapMarkerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val lat: Double,
    val lng: Double,
    val name: String = "",
    val text: String = ""
) {
    fun toDto() = MapMarker(
        id = id,
        lat = lat,
        lng = lng,
        name = name,
        text = text
    )

    companion object {
        fun fromDto(dto: MapMarker) =
            MapMarkerEntity(
                id = dto.id,
                lat = dto.lat,
                lng = dto.lng,
                name = dto.name,
                text = dto.text
            )
    }
}

fun List<MapMarkerEntity>.toDto(): List<MapMarker> = map(MapMarkerEntity::toDto)
fun List<MapMarker>.toEntity(): List<MapMarkerEntity> = map(MapMarkerEntity::fromDto)
fun MapMarker.toEntity(): MapMarkerEntity = MapMarkerEntity.fromDto(this)
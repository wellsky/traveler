package com.example.travel.dto

data class MapMarker (
    val id: Long = 0,
    val lat: Double,
    val lng: Double,
    val name: String = "",
    val text: String = ""
)
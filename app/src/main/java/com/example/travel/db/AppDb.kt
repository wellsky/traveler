package com.example.travel.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.travel.dao.MapMarkerDao
import com.example.travel.entity.MapMarkerEntity


@Database(entities = [
    MapMarkerEntity::class,
 ], version = 2, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun mapMarkerDao(): MapMarkerDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDb::class.java, "app.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
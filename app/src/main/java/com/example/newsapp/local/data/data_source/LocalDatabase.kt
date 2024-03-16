package com.example.newsapp.local.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.local.domain.model.LocalArticle
import com.example.newsapp.local.data.util.Converter

@Database(
    entities = [LocalArticle::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class LocalDatabase: RoomDatabase() {
    abstract val localDao: LocalDao

    companion object{
        const val DATABASE_NAME = "local_db"
    }
}
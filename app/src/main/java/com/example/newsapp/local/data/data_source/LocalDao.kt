package com.example.newsapp.local.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.local.domain.model.LocalArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDao {

    @Query("SELECT * FROM localarticle")
    fun getArticles(): Flow<List<LocalArticle>>

    @Query("SELECT * FROM localarticle WHERE title LIKE :query")
    fun searchForArticles(query: String): Flow<List<LocalArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: LocalArticle)

    @Delete
    suspend fun deleteArticle(article: LocalArticle)

}
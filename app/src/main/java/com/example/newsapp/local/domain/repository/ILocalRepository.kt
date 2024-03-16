package com.example.newsapp.local.domain.repository

import com.example.newsapp.local.domain.model.LocalArticle
import kotlinx.coroutines.flow.Flow

interface ILocalRepository {
    fun getArticles(): Flow<List<LocalArticle>>

    fun searchForArticles(query: String): Flow<List<LocalArticle>>

    suspend fun insertArticle(article: LocalArticle)

    suspend fun deleteArticle(article: LocalArticle)
}
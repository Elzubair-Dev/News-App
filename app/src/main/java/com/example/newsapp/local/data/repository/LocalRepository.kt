package com.example.newsapp.local.data.repository

import com.example.newsapp.local.data.data_source.LocalDao
import com.example.newsapp.local.domain.model.LocalArticle
import com.example.newsapp.local.domain.repository.ILocalRepository
import kotlinx.coroutines.flow.Flow

class LocalRepository(
    private val dao: LocalDao
): ILocalRepository {
    override fun getArticles(): Flow<List<LocalArticle>> {
        return dao.getArticles()
    }

    override fun searchForArticles(query: String): Flow<List<LocalArticle>> {
        return dao.searchForArticles(query)
    }

    override suspend fun insertArticle(article: LocalArticle) {
        dao.insertArticle(article)
    }

    override suspend fun deleteArticle(article: LocalArticle) {
        dao.deleteArticle(article)
    }
}
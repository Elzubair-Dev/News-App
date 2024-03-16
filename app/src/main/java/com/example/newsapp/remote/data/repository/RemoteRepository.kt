package com.example.newsapp.remote.data.repository

import com.example.newsapp.remote.data.remote.RemoteApi
import com.example.newsapp.remote.data.remote.dto.ArticleDto
import com.example.newsapp.remote.domain.repository.IRemoteRepository
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val api: RemoteApi
): IRemoteRepository {
    override suspend fun getBreakingNews(category: String, countryCode: String): ArticleDto {
        return api.getBreakingNews(category = category, country = countryCode)
    }

    override suspend fun getTagNews(tag: String): ArticleDto {
        return api.getTagNews(tag = tag)
    }

    override suspend fun getSearchedNews(query: String, sort: String): ArticleDto {
        return api.getSearchedNews(query = query, sort = sort)
    }
}
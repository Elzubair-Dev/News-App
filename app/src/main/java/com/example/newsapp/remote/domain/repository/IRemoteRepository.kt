package com.example.newsapp.remote.domain.repository

import com.example.newsapp.remote.data.remote.dto.ArticleDto

interface IRemoteRepository {
    suspend fun getBreakingNews(
        category: String,
        countryCode: String
    ): ArticleDto

    suspend fun getTagNews(
        tag: String
    ): ArticleDto

    suspend fun getSearchedNews(
        query: String,
        sort: String
    ): ArticleDto
}
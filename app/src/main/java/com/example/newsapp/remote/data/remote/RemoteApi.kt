package com.example.newsapp.remote.data.remote

import com.example.newsapp.common.util.Constants.API_KEY
import com.example.newsapp.remote.data.remote.dto.ArticleDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("category")
        category: String,
        @Query("country")
        country: String
    ): ArticleDto

    @GET("v2/everything")
    suspend fun getTagNews(
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("q")
        tag: String,
        @Query("sortBy")
        sort: String = "relevancy",
        @Query("language")
        language: String = "en"
    ): ArticleDto

    @GET("v2/everything")
    suspend fun getSearchedNews(
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("q")
        query: String,
        @Query("sortBy")
        sort: String,
        @Query("language")
        language: String = "en"
    ): ArticleDto
}
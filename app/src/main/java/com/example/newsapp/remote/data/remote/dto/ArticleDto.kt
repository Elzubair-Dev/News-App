package com.example.newsapp.remote.data.remote.dto

import com.example.newsapp.remote.domain.model.Article

data class ArticleDto(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

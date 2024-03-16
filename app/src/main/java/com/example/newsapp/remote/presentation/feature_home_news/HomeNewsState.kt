package com.example.newsapp.remote.presentation.feature_home_news

import android.graphics.Bitmap
import com.example.newsapp.remote.domain.model.Article

data class HomeNewsState(
    val greeting: String = "Hi, Sir",
    val category: String = "sports",
    val countryCode: String = "gb",
    val tag: String = "football",
    val profileImageBitmap: Bitmap? = null,
    val isLoading: Boolean = false,
    val breakingArticles: List<Article> = emptyList(),
    val tagArticles: List<Article> = emptyList(),
    val error: String = ""
)

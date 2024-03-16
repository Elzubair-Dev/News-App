package com.example.newsapp.remote.presentation.feature_home_news

import com.example.newsapp.local.domain.model.LocalArticle

sealed class HomeNewsEvents{
    data class SaveArticle(val article: LocalArticle): HomeNewsEvents()
    data class UpdateTag(val tag: String): HomeNewsEvents()
    object Update: HomeNewsEvents()
}

package com.example.newsapp.remote.presentation.feature_search_news

import com.example.newsapp.common.util.Sort
import com.example.newsapp.local.domain.model.LocalArticle

sealed class SearchEvents{
    data class SearchForArticles(val query: String): SearchEvents()
    data class SaveArticle(val article: LocalArticle): SearchEvents()
    data class SortArticles(val query: String, val sort: Sort): SearchEvents() //
    object Refresh: SearchEvents()
    object ToggleSearchSection: SearchEvents()
    object DeactivateSearchSection: SearchEvents()
    object ClearSearch:SearchEvents()
}

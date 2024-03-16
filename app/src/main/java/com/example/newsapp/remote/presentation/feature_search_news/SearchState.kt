package com.example.newsapp.remote.presentation.feature_search_news

import com.example.newsapp.remote.domain.model.Article

data class SearchState(
    val searchedArticles: List<Article> = emptyList(),
    val error: String = "",
    val isSearched: Boolean = false,
    val isSearchSectionActive: Boolean = false,
    val isLoading: Boolean = false
)

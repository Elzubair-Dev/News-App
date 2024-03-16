package com.example.newsapp.local.presentation.feature_bookmarks

import com.example.newsapp.local.domain.model.LocalArticle

data class BookmarksState(
    val bookmarksArticles: List<LocalArticle> = emptyList(),
    val error: String = "",
    val isSearchSectionActive: Boolean = false
)

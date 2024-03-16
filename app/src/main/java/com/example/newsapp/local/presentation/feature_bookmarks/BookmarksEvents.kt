package com.example.newsapp.local.presentation.feature_bookmarks

import com.example.newsapp.local.domain.model.LocalArticle

sealed class BookmarksEvents{
    data class DeleteArticle(val article: LocalArticle): BookmarksEvents()
    data class SearchForArticles(val query: String): BookmarksEvents()
    object ToggleSearchSection: BookmarksEvents()
    object DeactivateSearchSection: BookmarksEvents()
    object RestoreArticle: BookmarksEvents()
}

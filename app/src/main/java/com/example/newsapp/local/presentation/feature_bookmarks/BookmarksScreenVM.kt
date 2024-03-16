package com.example.newsapp.local.presentation.feature_bookmarks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.local.domain.model.LocalArticle
import com.example.newsapp.local.domain.use_cases.LocalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksScreenVM @Inject constructor(
    private val localUseCases: LocalUseCases
) : ViewModel() {
    private val _state = mutableStateOf(BookmarksState())
    val state: State<BookmarksState> = _state

    private var recentlyDeletedArticle: LocalArticle? = null

    private var getSearchedArticles: Job? = null

    private var getArticles: Job? = null

    init {
        getArticles()
    }

    fun onEvent(event: BookmarksEvents) {
        when (event) {
            BookmarksEvents.DeactivateSearchSection -> {
                _state.value = state.value.copy(
                    isSearchSectionActive = false
                )
                getArticles()
            }

            is BookmarksEvents.DeleteArticle -> {
                viewModelScope.launch {
                    localUseCases.deleteArticle(event.article)
                    recentlyDeletedArticle = event.article
                }
            }

            BookmarksEvents.RestoreArticle -> {

                viewModelScope.launch {
                    localUseCases.insertArticle(recentlyDeletedArticle ?: return@launch)
                    recentlyDeletedArticle = null
                }
            }


            is BookmarksEvents.SearchForArticles -> {
                if (event.query.isBlank()) {
                    getArticles()
                } else {
                    getSearchedArticles(event.query)
                }
            }

            BookmarksEvents.ToggleSearchSection -> {
                _state.value = state.value.copy(
                    isSearchSectionActive = !state.value.isSearchSectionActive
                )
            }
        }
    }

    private fun getArticles() {
        getArticles?.cancel()

        getArticles = localUseCases.getArticles()
            .onEach { articles ->
                _state.value = state.value.copy(
                    bookmarksArticles = articles.reversed()
                )
            }.launchIn(viewModelScope)
    }

    private fun getSearchedArticles(query: String) {
        val searchQuery = "%$query%"
        getSearchedArticles?.cancel()

        getSearchedArticles = localUseCases.searchForArticles(searchQuery)
            .onEach { articles ->
                _state.value = state.value.copy(
                    bookmarksArticles = articles
                )
            }.launchIn(viewModelScope)
    }
}
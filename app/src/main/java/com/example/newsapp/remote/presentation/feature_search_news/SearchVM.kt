package com.example.newsapp.remote.presentation.feature_search_news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.common.util.Resource
import com.example.newsapp.common.util.Sort
import com.example.newsapp.local.domain.use_cases.LocalUseCases
import com.example.newsapp.remote.domain.use_cases.RemoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVM @Inject constructor(
    private val useCases: RemoteUseCases,
    private val bookmarkUseCases: LocalUseCases
): ViewModel()  {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private val _tempQuery = mutableStateOf("")
    val tempQuery: State<String> = _tempQuery

    private val _sort = mutableStateOf(Sort("Date", "publishedAt"))
    val sort: State<Sort> = _sort

    fun onEvent(event: SearchEvents){
        when(event){

            is SearchEvents.SearchForArticles -> {
                if (event.query.isNotBlank()){
                    searchForArticle(query = event.query, sort = "publishedAt")

                    _state.value = state.value.copy(
                        isSearched = true
                    )
                    _tempQuery.value = event.query
                }
            }

            is SearchEvents.SaveArticle -> {
                viewModelScope.launch {
                    bookmarkUseCases.insertArticle(
                        event.article
                    )
                }
            }

            is SearchEvents.SortArticles -> {
                searchForArticle(query = event.query, sort = event.sort.value)

                _sort.value = event.sort
            }

            is SearchEvents.Refresh -> {
                if (_tempQuery.value.isNotBlank()){
                    searchForArticle(query = _tempQuery.value, sort = "publishedAt")
                }
            }

            is SearchEvents.ClearSearch -> {
                _state.value = state.value.copy(
                    searchedArticles = emptyList(),
                    isSearched = false
                )
                _tempQuery.value = ""
            }
            is SearchEvents.ToggleSearchSection -> {
                _state.value = state.value.copy(
                    isSearchSectionActive = ! state.value.isSearchSectionActive
                )
            }
            is SearchEvents.DeactivateSearchSection -> {
                _state.value = state.value.copy(
                    isSearchSectionActive = false,
                    isSearched = false
                )
            }
        }
    }

    private fun searchForArticle(query: String, sort: String){
        useCases.getSearchedNews(query = query, sort = sort).onEach { resource ->
            when(resource){
                is Resource.Error -> {
                    _state.value = SearchState(
                        error = resource.message ?: "Unexpected error ...",
                        searchedArticles = emptyList()
                    )
                }
                is Resource.Loading -> {
                    _state.value = SearchState(
                        isLoading = true,
                        searchedArticles = emptyList()
                    )
                }
                is Resource.Success -> {
                    _state.value = SearchState(
                        searchedArticles = resource.data.first()?.articles ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
package com.example.newsapp.remote.presentation.feature_search_news

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.common.util.Sort
import com.example.newsapp.remote.domain.model.toLocalArticle
import com.example.newsapp.remote.presentation.feature_search_news.components.landscape.content.LandScapeScreen
import com.example.newsapp.remote.presentation.feature_search_news.components.portrait.content.PortraitScreen

@Composable
fun SearchScreen(
    navController: NavController,
    isPortrait: Boolean,
    iconId: Int,
    onThemeIconClick: () -> Unit,
    viewModel: SearchVM = hiltViewModel()
) {
    val state = viewModel.state.value

    if (isPortrait) {
        PortraitScreen(
            navController = navController,
            isLoading = state.isLoading,
            isSearched = state.isSearched,
            searchedArticles = state.searchedArticles,
            iconId = iconId,
            sortName = viewModel.sort.value.name,
            sortList = sort,
            error = state.error,
            onThemeIconClick = onThemeIconClick,
            onSearchClick = { searchedQuery ->
                viewModel.onEvent(SearchEvents.SearchForArticles(searchedQuery))
            },
            onCancelClick = {
                viewModel.onEvent(SearchEvents.DeactivateSearchSection)
            },
            onSortClick = { sort ->
                viewModel.onEvent(SearchEvents.SortArticles(query = viewModel.tempQuery.value, sort = sort))
            },
            toBookmark = { article ->
                viewModel.onEvent(SearchEvents.SaveArticle(article.toLocalArticle()))
            },
            refresh = {
                viewModel.onEvent(SearchEvents.Refresh)
            },
            onClearClick = {
                viewModel.onEvent(SearchEvents.ClearSearch)
            }
        )
    } else {
        LandScapeScreen(
            navController = navController,
            isLoading = state.isLoading,
            isSearched = state.isSearched,
            isSearchSectionActive = state.isSearchSectionActive,
            searchedArticles = state.searchedArticles,
            iconId = iconId,
            sortName = viewModel.sort.value.name,
            sortList = sort,
            error = state.error,
            onThemeIconClick = onThemeIconClick,
            onCancelClick = {
                viewModel.onEvent(SearchEvents.DeactivateSearchSection)
            },
            onSortClick = { sort ->
                viewModel.onEvent(SearchEvents.SortArticles(query = viewModel.tempQuery.value,sort = sort))
            },
            toBookmark = { article ->
                viewModel.onEvent(SearchEvents.SaveArticle(article.toLocalArticle()))
            },
            onSearchClick = { searchedQuery ->
                viewModel.onEvent(SearchEvents.SearchForArticles(searchedQuery))
            },
            refresh = {
                viewModel.onEvent(SearchEvents.Refresh)
            },
            onSearchToggled = {
                viewModel.onEvent(SearchEvents.ToggleSearchSection)
            },
            onClearClick = {
                viewModel.onEvent(SearchEvents.ClearSearch)
            }
        )
    }
}

val sort = listOf(
    Sort(
        name = "Relevance",
        value = "relevancy"
    ),
    Sort(
        name = "Date",
        value = "publishedAt"
    ),
    Sort(
        name = "Popularity",
        value = "popularity"
    ),
)
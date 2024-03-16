package com.example.newsapp.remote.presentation.feature_search_news.components.portrait.content

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import com.example.newsapp.common.presentation.components.BottomBar
import com.example.newsapp.common.presentation.components.TopBar
import com.example.newsapp.common.util.Screen
import com.example.newsapp.common.util.Sort
import com.example.newsapp.remote.domain.model.Article
import com.example.newsapp.remote.presentation.feature_search_news.components.portrait.shimmer.SearchContentShimmer

@Composable
fun PortraitScreen(
    navController: NavController,
    isLoading: Boolean,
    isSearched: Boolean,
    iconId: Int,
    sortName: String,
    sortList: List<Sort>,
    error: String,
    searchedArticles: List<Article>,
    onThemeIconClick: () -> Unit,
    onCancelClick: () -> Unit,
    onClearClick: () -> Unit,
    refresh: () -> Unit,
    onSearchClick: (String) -> Unit,
    onSortClick: (Sort) -> Unit,
    toBookmark: (Article) -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Scaffold(
            topBar = {
                TopBar(
                    text = "Search",
                    iconId = iconId,
                    onThemeIconClick = onThemeIconClick
                )
            },
            bottomBar = {
                BottomBar(
                    navController = navController,
                    pageName = Screen.SearchScreen.label,
                    subEvent = {
                        onClearClick()
                        onCancelClick()
                    }
                )
            }
        ) { padding ->
            if (isLoading) {
                SearchContentShimmer(padding = padding)
            } else {
                SearchContent(
                    navController = navController,
                    searchedArticles = searchedArticles,
                    padding = padding,
                    sortName = sortName,
                    sortList = sortList,
                    isSearched = isSearched,
                    error = error,
                    onSearchClick = onSearchClick,
                    onCancelClick = onCancelClick,
                    onSortClick = onSortClick,
                    toBookmark = toBookmark,
                    refresh = refresh,
                    onClearClick = onClearClick
                )
            }
        }
    }
}
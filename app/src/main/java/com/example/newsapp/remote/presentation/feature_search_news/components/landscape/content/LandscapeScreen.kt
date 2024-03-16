@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.newsapp.remote.presentation.feature_search_news.components.landscape.content

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import com.example.newsapp.common.presentation.components.BottomBar
import com.example.newsapp.common.presentation.components.BottomSheet
import com.example.newsapp.common.presentation.components.TopBar
import com.example.newsapp.common.util.Screen
import com.example.newsapp.common.util.Sort
import com.example.newsapp.remote.domain.model.Article
import com.example.newsapp.remote.presentation.feature_search_news.components.landscape.shimmer.SearchLandscapeShimmer

@Composable
fun LandScapeScreen(
    navController: NavController,
    isLoading: Boolean,
    isSearched: Boolean,
    isSearchSectionActive: Boolean,
    iconId: Int,
    sortName: String,
    sortList: List<Sort>,
    error: String,
    searchedArticles: List<Article>,
    onThemeIconClick: () -> Unit,
    onCancelClick: () -> Unit,
    onClearClick: () -> Unit,
    refresh: () -> Unit,
    onSearchToggled: () -> Unit,
    onSearchClick: (String) -> Unit,
    onSortClick: (Sort) -> Unit,
    toBookmark: (Article) -> Unit
) {

    val scaffoldState = rememberBottomSheetScaffoldState()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        BottomSheet(
            isLoading = isLoading,
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    text = "Search",
                    iconId = iconId,
                    showSearchIcon = true,
                    onThemeIconClick = onThemeIconClick,
                    onSearchToggled = onSearchToggled
                )
            },
            sheetContent = {
                // sheet content
                BottomBar(
                    navController = navController,
                    pageName = Screen.SearchScreen.label,
                    subEvent = {
                        onClearClick()
                        onCancelClick()
                    }
                )
            },
            screenShimmer = {
                SearchLandscapeShimmer()
            },
            screenContent = { padding ->
                SearchLandscapeContent(
                    navController = navController,
                    sortName = sortName,
                    sortList = sortList,
                    padding = padding,
                    isSearched = isSearched,
                    isSearchSectionActive = isSearchSectionActive,
                    error = error,
                    searchedArticles = searchedArticles,
                    toBookmark = toBookmark,
                    refresh = refresh,
                    onCancelClick = onCancelClick,
                    onSortClick = onSortClick,
                    onSearchClick = onSearchClick,
                    onClearClick = onClearClick
                )
            }
        )
    }
}
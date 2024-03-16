package com.example.newsapp.remote.presentation.feature_search_news.components.landscape.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.common.presentation.components.EmptyScreen
import com.example.newsapp.common.presentation.components.LandscapeNewsItem
import com.example.newsapp.common.presentation.components.SearchSection
import com.example.newsapp.common.util.Screen
import com.example.newsapp.common.util.Sort
import com.example.newsapp.remote.domain.model.Article
import com.example.newsapp.remote.presentation.feature_home_news.components.content.ErrorSection
import com.example.newsapp.remote.presentation.feature_search_news.components.common.content.ResultSection
import com.example.newsapp.remote.presentation.feature_search_news.components.common.content.SortSection

@Composable
fun SearchLandscapeContent(
    navController: NavController,
    padding: PaddingValues,
    sortName: String,
    sortList: List<Sort>,
    error: String,
    isSearched: Boolean,
    isSearchSectionActive: Boolean,
    searchedArticles: List<Article> = emptyList(),
    onSearchClick: (String) -> Unit,
    onCancelClick: () -> Unit,
    onClearClick: () -> Unit,
    onSortClick: (Sort) -> Unit,
    toBookmark: (Article) -> Unit,
    refresh: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding.calculateBottomPadding()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AnimatedVisibility(
                visible = isSearchSectionActive,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Spacer(modifier = Modifier.padding(6.dp))

                    SearchSection(
                        isActive = isSearchSectionActive,
                        onSearchClicked = onSearchClick,
                        onCancelClick = onCancelClick
                    )

                    Spacer(modifier = Modifier.padding(6.dp))

                    ResultSection(
                        thereIsResults = searchedArticles.isNotEmpty(),
                        results = searchedArticles.size,
                        onClearClick = onClearClick
                    )

                    Spacer(modifier = Modifier.padding(6.dp))

                    SortSection(
                        thereIsResults = searchedArticles.isNotEmpty(),
                        sortName = sortName,
                        sortList = sortList,
                        onSortClick = onSortClick
                    )
                }
            }

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize(),
                columns = GridCells.Adaptive(160.dp),
                content = {
                    items(searchedArticles) { article ->
                        LandscapeNewsItem(
                            article = article,
                            onArticleClick = {
                                navController.navigate(
                                    Screen.WebViewScreen.route +
                                            "?articleUrl=${it.url}"
                                )
                            }, toBookmark = {
                                toBookmark(it)
                            }
                        )
                    }
                }
            )
        }
        if (error.isNotBlank()) {
            ErrorSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .align(Alignment.Center),
                error = error,
                refresh = refresh
            )
        }
        else if (isSearched && searchedArticles.isEmpty()) {
            EmptyScreen(
                text = "No result found"
            )
        }
    }
}
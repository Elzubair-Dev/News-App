package com.example.newsapp.remote.presentation.feature_search_news.components.portrait.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.common.presentation.components.EmptyScreen
import com.example.newsapp.common.presentation.components.NewsItem
import com.example.newsapp.common.presentation.components.SearchSection
import com.example.newsapp.common.util.Screen
import com.example.newsapp.common.util.Sort
import com.example.newsapp.remote.domain.model.Article
import com.example.newsapp.remote.presentation.feature_home_news.components.content.ErrorSection
import com.example.newsapp.remote.presentation.feature_search_news.components.common.content.ResultSection
import com.example.newsapp.remote.presentation.feature_search_news.components.common.content.SortSection

@Composable
fun SearchContent(
    navController: NavController,
    searchedArticles: List<Article>,
    padding: PaddingValues,
    sortName: String,
    sortList: List<Sort>,
    error: String,
    isSearched: Boolean,
    onSearchClick: (String) -> Unit,
    onSortClick: (Sort) -> Unit,
    onCancelClick: () -> Unit,
    onClearClick: () -> Unit,
    refresh: () -> Unit,
    toBookmark: (Article) -> Unit
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                top = padding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding() / 2,
                start = 8.dp,
                end = 8.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            SearchSection(
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
                sortName = sortName,
                thereIsResults = searchedArticles.isNotEmpty(),
                sortList = sortList,
                onSortClick = onSortClick
            )

            Spacer(modifier = Modifier.padding(6.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(searchedArticles) { article ->
                    NewsItem(
                        article = article,
                        onArticleClick = {
                            navController.navigate(
                                Screen.WebViewScreen.route +
                                        "?articleUrl=${it.url}"
                            )
                        },
                        toBookmark = toBookmark
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
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
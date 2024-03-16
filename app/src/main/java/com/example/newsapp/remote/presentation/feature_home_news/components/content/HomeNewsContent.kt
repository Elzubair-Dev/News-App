@file:OptIn(ExperimentalFoundationApi::class)

package com.example.newsapp.remote.presentation.feature_home_news.components.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.common.presentation.components.NewsItem
import com.example.newsapp.common.util.Common
import com.example.newsapp.common.util.Screen
import com.example.newsapp.common.util.Tag
import com.example.newsapp.remote.domain.model.Article

@Stable
@Composable
fun HomeNewsContent(
    navController: NavController,
    padding: PaddingValues,
    categoryTitle: String,
    tag: String,
    error: String,
    pagerState: PagerState,
    breakingArticles: List<Article>,
    tagArticles: List<Article>,
    onTagClick: (String) -> Unit,
    toBookmark: (Article) -> Unit,
    refresh: () -> Unit
) {
    val tags: List<Tag> = when (categoryTitle) {
        "business" -> {
            Common.BusinessTags
        }

        "entertainment" -> {
            Common.EntertainmentTags
        }

        "health" -> {
            Common.HealthTags
        }

        "science" -> {
            Common.ScienceTags
        }

        "sports" -> {
            Common.SportsTags
        }

        "technology" -> {
            Common.TechnologyTags
        }

        else -> {
            Common.GeneralTags
        }
    }

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Breaking News",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(end = 64.dp),
                    pageSpacing = 16.dp
                ) { pageNumber ->
                    if (breakingArticles.isNotEmpty()) {
                        BreakingNewsCard(
                            category = categoryTitle,
                            article = breakingArticles[pageNumber],
                            toBookmark = {
                                toBookmark(breakingArticles[pageNumber])
                            },
                            onArticleClick = {
                                navController.navigate(
                                    Screen.WebViewScreen.route +
                                            "?articleUrl=${breakingArticles[pageNumber].url}"
                                )
                            })
                    }
                }
            }


            Spacer(modifier = Modifier.padding(4.dp))

            // tags
            if (tagArticles.isNotEmpty()) {
                Tags(
                    currentTag = tag,
                    tags = tags,
                    onTagClick = onTagClick
                )
            }

            Spacer(modifier = Modifier.padding(4.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(tagArticles) { article ->
                    NewsItem(
                        category = categoryTitle,
                        article = article,
                        onArticleClick = {
                            navController.navigate(
                                Screen.WebViewScreen.route +
                                        "?articleUrl=${article.url}"
                            )
                        },
                        toBookmark = { toBookmark(article) }
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
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
    }
}
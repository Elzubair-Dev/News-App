@file:OptIn(ExperimentalFoundationApi::class)

package com.example.newsapp.remote.presentation.feature_home_news.components.shimmer

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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeNewsShimmer(padding: PaddingValues) {

    val pagerState = rememberPagerState(pageCount = { 10 })

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
                ) {
                    // Breaking news card shimmer
                    BreakingNewsCardShimmer()
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            // Tags Shimmer
            TagsShimmer()

            Spacer(modifier = Modifier.padding(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(20) {
                    // News item shimmer
                    NewsItemShimmer()
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}
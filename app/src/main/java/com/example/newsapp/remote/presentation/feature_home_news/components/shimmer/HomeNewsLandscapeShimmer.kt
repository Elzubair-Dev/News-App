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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeNewsLandscapeShimmer(
    padding: PaddingValues
) {
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
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        VerticalPager(
                            state = pagerState,
                            contentPadding = PaddingValues(bottom = 8.dp),
                            pageSpacing = 6.dp
                        ) {
                            BreakingNewsCardShimmer(1f)
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(6.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    // tags
                    TagsShimmer()

                    Spacer(modifier = Modifier.padding(6.dp))

                    LazyColumn(modifier = Modifier.fillMaxSize()) {

                        items(10) {
                            NewsItemShimmer()
                            Spacer(modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
    }
}
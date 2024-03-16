package com.example.newsapp.remote.presentation.feature_search_news.components.portrait.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.common.presentation.components.SearchSection
import com.example.newsapp.remote.presentation.feature_home_news.components.shimmer.NewsItemShimmer
import com.example.newsapp.remote.presentation.feature_search_news.components.common.shimmer.ResultSectionShimmer
import com.example.newsapp.remote.presentation.feature_search_news.components.common.shimmer.SortSectionShimmer

@Composable
fun SearchContentShimmer(
    padding: PaddingValues
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
            SearchSection(isLoading = true)

            Spacer(modifier = Modifier.padding(6.dp))

            ResultSectionShimmer()

            Spacer(modifier = Modifier.padding(6.dp))

            SortSectionShimmer()

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
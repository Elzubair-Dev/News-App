package com.example.newsapp.remote.presentation.feature_home_news.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.common.util.Common.shimmerEffect

@Composable
fun TagsShimmer() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        items(9) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .width((50 .. 80).random().dp)
                    .height(10.dp)
                    .shimmerEffect()

            )
        }
    }
}
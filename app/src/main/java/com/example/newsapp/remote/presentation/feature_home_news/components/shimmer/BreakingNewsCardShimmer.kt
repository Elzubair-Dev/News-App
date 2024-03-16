package com.example.newsapp.remote.presentation.feature_home_news.components.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.newsapp.common.util.Common.shimmerEffect

@Composable
fun BreakingNewsCardShimmer(
    fraction: Float = 0.35f
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction)
            .clip(RoundedCornerShape(15.dp))
            .shimmerEffect()
    )
}
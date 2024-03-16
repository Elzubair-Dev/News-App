package com.example.newsapp.remote.presentation.feature_search_news.components.common.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.newsapp.common.util.Common.shimmerEffect

@Composable
fun SortSectionShimmer() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(0.6f)
                .shimmerEffect()
        )

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(30.dp)
                .shimmerEffect()
        )
    }
}
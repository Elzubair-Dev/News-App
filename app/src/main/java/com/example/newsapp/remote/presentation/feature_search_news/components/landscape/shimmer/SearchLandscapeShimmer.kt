package com.example.newsapp.remote.presentation.feature_search_news.components.landscape.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.newsapp.common.util.Common.shimmerEffect

@Composable
fun SearchLandscapeShimmer() {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(20) {
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(0.5f)
                    .height(160.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .shimmerEffect()
            )
        }
    }
}
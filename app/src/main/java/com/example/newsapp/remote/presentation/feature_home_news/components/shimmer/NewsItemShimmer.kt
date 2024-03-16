package com.example.newsapp.remote.presentation.feature_home_news.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.newsapp.common.util.Common.shimmerEffect

@Composable
fun NewsItemShimmer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // image shimmer
        Box(
            modifier = Modifier
                .padding(end = 6.dp)
                .fillMaxWidth()
                .height(90.dp)
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .shimmerEffect()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .weight(1.5f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 3 Texts shimmer
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .shimmerEffect()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .shimmerEffect()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(15.dp)
                    .shimmerEffect()
            )

            // Author and date shimmer
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Author icon shimmer
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(20.dp)
                            .shimmerEffect()

                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    // Author name shimmer
                    Box(
                        modifier = Modifier
                            .width(65.dp)
                            .height(12.dp)
                            .shimmerEffect()

                    )

                }

                // Date shimmer
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(12.dp)
                            .shimmerEffect()

                    )
                }
            }
        }
    }
}
package com.example.newsapp.remote.presentation.feature_search_news.components.common.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun ResultSection(
    thereIsResults: Boolean,
    results: Int,
    onClearClick:()->Unit = {}
) {
    if (thereIsResults) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total results: $results",
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Clear",
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 6.sp),
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.clickable {
                    onClearClick()
                }
            )
        }
    }
}
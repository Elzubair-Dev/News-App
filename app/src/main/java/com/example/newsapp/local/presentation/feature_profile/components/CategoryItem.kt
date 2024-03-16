package com.example.newsapp.local.presentation.feature_profile.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.common.util.Category

@Composable
fun CategoryItem(
    currentCategory: String,
    category: Category,
    onCategoryClick: (Category) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(category.color!!)
            .clickable {
                onCategoryClick(category)
            }
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedContent(
            targetState = currentCategory == category.title.lowercase(),
            transitionSpec = { slideInHorizontally { it } togetherWith slideOutHorizontally { it } },
            label = ""
        ) { isVisible ->
            if (isVisible) {
                Image(
                    painter = painterResource(id = category.iconID!!),
                    contentDescription = category.title,
                    alpha = 0.75f,
                    modifier = Modifier.size(25.dp)
                )
            }
        }

        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Text(
            text = category.title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
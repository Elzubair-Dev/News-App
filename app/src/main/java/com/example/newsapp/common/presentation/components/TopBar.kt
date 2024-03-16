package com.example.newsapp.common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    text: String,
    iconId: Int,
    showSearchIcon: Boolean = false,
    onThemeIconClick: () -> Unit,
    onSearchToggled: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground
        )

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showSearchIcon) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Show search icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onSearchToggled()
                        }
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "Theme icon",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        onThemeIconClick()
                    }
                    .padding(4.dp)
            )
        }
    }
}
package com.example.newsapp.remote.presentation.feature_home_news.components.content

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R

@Composable
fun HomeLandscapeTopBar(
    greeting: String? = null,
    themeIconId: Int = R.drawable.baseline_bedtime_24,
    bitmap: Bitmap? = null,
    onThemeIconClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (bitmap == null) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Transparent)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = CircleShape
                        )
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = "Person icon",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                    )
                }
            } else {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "User profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .size(35.dp)
                )
            }

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = greeting ?: "Hi, Sir",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 6.sp
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            text = "Breaking News",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Icon(
            painter = painterResource(id = themeIconId),
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
package com.example.newsapp.remote.presentation.feature_home_news.components.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.common.util.Tag

@Composable
fun Tags(
    currentTag: String = "",
    tags: List<Tag>,
    onTagClick:(String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically){
        items(tags){ tag ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clip(CircleShape)
                    .clickable {
                        onTagClick(tag.name.lowercase())
                    },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (currentTag == tag.name.lowercase()){
                    Text(
                        text = "#",
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        color =  MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .padding(start = 4.dp, bottom = 4.dp, top = 4.dp, end = 1.dp)
                            .rotate(6f)
                    )
                }

                Text(
                    text = tag.name,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 6.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(end = 4.dp, top = 4.dp, bottom = 4.dp)
                )
            }
        }
    }
}
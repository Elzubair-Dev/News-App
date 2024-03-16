@file:Suppress("USELESS_ELVIS")

package com.example.newsapp.common.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.common.util.Common.toEmoji
import com.example.newsapp.common.util.Constants.THUMBS_UP_EMOJI_UNICODE
import com.example.newsapp.remote.domain.util.RemoteUtilFunctions.placeholderID
import com.example.newsapp.remote.domain.model.Article
import com.example.newsapp.ui.theme.White

@Composable
fun LandscapeNewsItem(
    category: String = "general",
    article: Article,
    onArticleClick:(Article)->Unit,
    toBookmark:(Article)->Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .clickable {
                onArticleClick(article)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
        ) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = "Article Image",
                placeholder = painterResource(id = placeholderID(category)),
                error = painterResource(id = placeholderID(category)),
                fallback = painterResource(id = placeholderID(category)),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            )
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.3f))
                    .blur(20.dp)
                    .clickable {
                        toBookmark(article)
                        Toast
                            .makeText(
                                context,
                                "Done ${THUMBS_UP_EMOJI_UNICODE.toEmoji()}",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                    .padding(4.dp)
                    .align(Alignment.TopStart),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = "Bookmark icon",
                    tint = White,
                    modifier = Modifier.size(15.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 4.dp)
                .width(160.dp)
                .height(100.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = article.title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 14.sp
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .width(160.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Author icon"
                    )
                    Text(
                        text = article.author ?: "Author",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 6.sp,
                            fontStyle = FontStyle.Normal
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = article.publishedAt.take(10) ?: "",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 6.sp,
                            fontStyle = FontStyle.Normal
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
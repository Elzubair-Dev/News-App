@file:Suppress("USELESS_ELVIS")

package com.example.newsapp.remote.presentation.feature_home_news.components.content

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.common.util.Common.toEmoji
import com.example.newsapp.common.util.Constants
import com.example.newsapp.remote.domain.model.Article
import com.example.newsapp.remote.domain.util.RemoteUtilFunctions.placeholderID
import com.example.newsapp.ui.theme.White

@Composable
fun BreakingNewsCard(
    category: String,
    article: Article,
    fraction: Float = 0.35f,
    toBookmark:()->Unit,
    onArticleClick:()->Unit
) {

    val context = LocalContext.current

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .clickable {
                    onArticleClick()
                }
        ) {
            AsyncImage(
                model = article.urlToImage,
                placeholder = painterResource(id = placeholderID(category)),
                error = painterResource(id = placeholderID(category)),
                fallback = painterResource(id = placeholderID(category)),
                contentDescription = "Article Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.35f))
                    .blur(20.dp)
                    .clickable {
                        toBookmark()
                        Toast
                            .makeText(
                                context,
                                "Done ${Constants.THUMBS_UP_EMOJI_UNICODE.toEmoji()}",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                    .padding(4.dp)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = "Bookmark icon",
                    tint = White
                )
            }

            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight(.5f)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(4.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = article.title,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        lineHeight = 12.sp
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.padding(4.dp))
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
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Author icon"
                        )
                        Text(
                            text = article.author ?: "",
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
                            text = article.publishedAt.take(10),
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
}
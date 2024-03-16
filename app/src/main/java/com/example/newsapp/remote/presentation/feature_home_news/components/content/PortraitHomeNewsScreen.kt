@file:OptIn(ExperimentalFoundationApi::class)

package com.example.newsapp.remote.presentation.feature_home_news.components.content

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import com.example.newsapp.common.presentation.components.BottomBar
import com.example.newsapp.common.util.Screen
import com.example.newsapp.remote.domain.model.Article
import com.example.newsapp.remote.presentation.feature_home_news.components.shimmer.HomeNewsShimmer

@Stable
@Composable
fun PortraitHomeNewsScreen(
    navController: NavController,
    categoryTitle: String,
    greeting: String,
    tag: String,
    error: String,
    breakingArticles: List<Article>,
    tagArticles: List<Article>,
    pagerState: PagerState,
    iconId: Int,
    isLoading: Boolean,
    profileImageBitmap: Bitmap?,
    onThemeIconClick:()-> Unit,
    toBookmark:(Article)-> Unit,
    refresh:()-> Unit,
    onTagClick:(String)-> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                HomeTopBar(
                    greeting = greeting,
                    themeIconId = iconId,
                    bitmap = profileImageBitmap,
                    onThemeIconClick = onThemeIconClick
                )
            },
            bottomBar = {
                BottomBar(
                    navController = navController,
                    pageName = Screen.HomeScreen.label
                )
            }
        ) { padding ->
            if (isLoading){
                HomeNewsShimmer(padding = padding)
            }else{
                HomeNewsContent(
                    navController = navController,
                    padding = padding,
                    categoryTitle = categoryTitle,
                    pagerState = pagerState,
                    breakingArticles = breakingArticles,
                    tag = tag,
                    tagArticles = tagArticles,
                    error = error,
                    toBookmark = toBookmark,
                    onTagClick = onTagClick,
                    refresh = refresh
                )
            }
        }
    }
}
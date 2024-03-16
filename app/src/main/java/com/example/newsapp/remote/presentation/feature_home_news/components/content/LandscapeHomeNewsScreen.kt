@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.example.newsapp.remote.presentation.feature_home_news.components.content

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import com.example.newsapp.common.presentation.components.BottomBar
import com.example.newsapp.common.presentation.components.BottomSheet
import com.example.newsapp.common.util.Screen
import com.example.newsapp.remote.domain.model.Article
import com.example.newsapp.remote.presentation.feature_home_news.components.shimmer.HomeNewsLandscapeShimmer

@Stable
@Composable
fun LandscapeHomeNewsScreen(
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

    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheet(
        isLoading = isLoading,
        scaffoldState = scaffoldState,
        topBar = {
            HomeLandscapeTopBar(
                greeting = greeting,
                themeIconId = iconId,
                bitmap = profileImageBitmap,
                onThemeIconClick = onThemeIconClick
            )
        },
        sheetContent = {
            BottomBar(
                navController = navController,
                pageName = Screen.HomeScreen.label
            )
        },
        screenContent = { padding ->
            HomeNewsLandscapeContent(
                navController = navController,
                padding = padding,
                categoryTitle = categoryTitle,
                tag = tag,
                error = error,
                pagerState = pagerState,
                breakingArticles = breakingArticles,
                tagArticles = tagArticles,
                onTagClick = onTagClick,
                toBookmark = toBookmark,
                refresh = refresh
            )
        },
        screenShimmer = {
            HomeNewsLandscapeShimmer(padding = it)
        }
    )
}
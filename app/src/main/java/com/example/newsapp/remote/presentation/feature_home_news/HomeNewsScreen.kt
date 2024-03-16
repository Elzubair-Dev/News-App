@file:OptIn(ExperimentalFoundationApi::class)

package com.example.newsapp.remote.presentation.feature_home_news

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.remote.domain.model.toLocalArticle
import com.example.newsapp.remote.presentation.feature_home_news.components.content.LandscapeHomeNewsScreen
import com.example.newsapp.remote.presentation.feature_home_news.components.content.PortraitHomeNewsScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Stable
@Composable
fun HomeNewsScreen(
    navController: NavController,
    isPortrait: Boolean,
    iconId: Int,
    viewModel: HomeNewsVM = hiltViewModel(),
    onThemeIconClick: () -> Unit
) {
    val state = viewModel.state.value

    val category = rememberUpdatedState(newValue = state.category)


    val pagerState = rememberPagerState(pageCount = { state.breakingArticles.size })

    LaunchedEffect(key1 = Unit) {

        viewModel.onEvent(HomeNewsEvents.Update)

        if (viewModel.isUpdated.value){
            pagerState.scrollToPage(0)
        }

        while (true) {
            yield()
            delay(5000)
            pagerState.animateScrollToPage(
                page = if (pagerState.pageCount > 0) (pagerState.currentPage + 1) % (pagerState.pageCount) else 0,
                animationSpec = tween(600)
            )
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (isPortrait) {
            PortraitHomeNewsScreen(
                navController = navController,
                categoryTitle = category.value,
                greeting = state.greeting,
                tag = state.tag,
                error = state.error,
                breakingArticles = state.breakingArticles,
                tagArticles = state.tagArticles,
                pagerState = pagerState,
                iconId = iconId,
                isLoading = state.isLoading,
                profileImageBitmap = state.profileImageBitmap,
                onThemeIconClick = onThemeIconClick,
                toBookmark = { article ->
                    viewModel.onEvent(
                        HomeNewsEvents.SaveArticle(article.toLocalArticle())
                    )
                },
                refresh = {
                    viewModel.onEvent(HomeNewsEvents.Update)
                },
                onTagClick = { tag ->
                    viewModel.onEvent(HomeNewsEvents.UpdateTag(tag))
                }
            )
        } else {
            LandscapeHomeNewsScreen(
                navController = navController,
                categoryTitle = category.value,
                greeting = state.greeting,
                tag = state.tag,
                error = state.error,
                breakingArticles = state.breakingArticles,
                tagArticles = state.tagArticles,
                pagerState = pagerState,
                iconId = iconId,
                isLoading = state.isLoading,
                profileImageBitmap = state.profileImageBitmap,
                onThemeIconClick = onThemeIconClick,
                toBookmark = { article ->
                    viewModel.onEvent(HomeNewsEvents.SaveArticle(article.toLocalArticle()))
                },
                refresh = {
                    viewModel.onEvent(HomeNewsEvents.Update)
                },
                onTagClick = { tag ->
                    viewModel.onEvent(HomeNewsEvents.UpdateTag(tag))
                }
            )
        }
    }
}
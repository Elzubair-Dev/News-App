package com.example.newsapp.local.presentation.feature_bookmarks.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.common.presentation.components.BottomBar
import com.example.newsapp.common.presentation.components.EmptyScreen
import com.example.newsapp.common.presentation.components.SearchSection
import com.example.newsapp.common.presentation.components.TopBar
import com.example.newsapp.common.util.Screen
import com.example.newsapp.local.domain.model.LocalArticle

@Composable
fun PortraitBookmarkContent(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    bookmarksArticles: List<LocalArticle>,
    iconId: Int,
    isSearchSectionActive: Boolean,
    onThemeIconClick:()->Unit,
    onSearchToggled:()->Unit,
    onCancelClick:()->Unit,
    onSearchClicked:(String)->Unit,
    onArticleDelete:(LocalArticle)->Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
            topBar = {
                TopBar(
                    text = "Bookmarks",
                    iconId = iconId,
                    showSearchIcon = true,
                    onThemeIconClick = onThemeIconClick,
                    onSearchToggled = onSearchToggled
                )
            },
            bottomBar = {
                BottomBar(
                    navController = navController,
                    pageName = Screen.BookmarkNewsScreen.label
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(
                        top = padding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding() - 8.dp,
                        start = 12.dp,
                        end = 12.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                AnimatedVisibility(
                    visible = isSearchSectionActive,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    SearchSection(
                        isActive = isSearchSectionActive,
                        onCancelClick = onCancelClick,
                        onSearchClicked = onSearchClicked
                    )
                }

                if (bookmarksArticles.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        items(bookmarksArticles) { article ->
                            BookmarksArticleCard(
                                article = article,
                                onArticleDelete = onArticleDelete,
                                toCachedWebView = { url ->
                                    navController.navigate(
                                        Screen.WebViewScreen.route +
                                                "?articleUrl=${url}"
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.padding(bottom = 8.dp))
                        }
                    }
                }else{
                    EmptyScreen()
                }
            }
        }
    }
}
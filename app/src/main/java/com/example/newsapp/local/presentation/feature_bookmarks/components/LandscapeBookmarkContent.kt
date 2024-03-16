@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newsapp.local.presentation.feature_bookmarks.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.common.presentation.components.BottomBar
import com.example.newsapp.common.presentation.components.BottomSheet
import com.example.newsapp.common.presentation.components.EmptyScreen
import com.example.newsapp.common.presentation.components.SearchSection
import com.example.newsapp.common.presentation.components.TopBar
import com.example.newsapp.common.util.Screen
import com.example.newsapp.local.domain.model.LocalArticle

@Composable
fun LandscapeBookmarkContent(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    bookmarksArticles: List<LocalArticle>,
    iconId: Int,
    isSearchSectionActive: Boolean,
    onThemeIconClick: () -> Unit,
    onSearchToggled: () -> Unit,
    onCancelClick: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onArticleDelete: (LocalArticle) -> Unit
) {


    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheet(
        snackbarHostState = snackbarHostState,
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                text = "Bookmarks",
                iconId = iconId,
                showSearchIcon = true,
                onThemeIconClick = onThemeIconClick,
                onSearchToggled = onSearchToggled
            )
        },
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                BottomBar(
                    navController = navController,
                    pageName = Screen.BookmarkNewsScreen.label
                )
            }
        },
        screenContent = { padding ->
            if (bookmarksArticles.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = padding.calculateBottomPadding()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.padding(top = 6.dp))

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

                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    LazyVerticalGrid(columns = GridCells.Adaptive(160.dp)) {
                        items(bookmarksArticles) { article ->
                            BookmarkLandscapeCard(
                                article = article,
                                onArticleDelete = { deletedArticle ->
                                    onArticleDelete(deletedArticle)
                                },
                                toCachedWebView = { url ->
                                    navController.navigate(
                                        Screen.WebViewScreen.route +
                                                "?articleUrl=${url}"
                                    )
                                }
                            )
                        }
                    }
                }
            } else {
                EmptyScreen()
            }
        }
    )
}
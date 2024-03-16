package com.example.newsapp.local.presentation.feature_bookmarks


import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.local.domain.model.LocalArticle
import com.example.newsapp.local.presentation.feature_bookmarks.components.LandscapeBookmarkContent
import com.example.newsapp.local.presentation.feature_bookmarks.components.PortraitBookmarkContent
import kotlinx.coroutines.launch

@Composable
fun BookmarksScreen(
    navController: NavController,
    isPortrait: Boolean,
    iconId: Int,
    viewModel: BookmarksScreenVM = hiltViewModel(),
    onThemeIconClick: () -> Unit
) {
    val state = viewModel.state.value

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    fun onArticleDelete(article: LocalArticle) {
        viewModel.onEvent(BookmarksEvents.DeleteArticle(article))
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = "Article Deleted",
                actionLabel = "Undo",
                duration = SnackbarDuration.Short
            )

            if (result == SnackbarResult.ActionPerformed) {
                viewModel.onEvent(BookmarksEvents.RestoreArticle)
            }
        }
    }

    if (isPortrait) {
        PortraitBookmarkContent(
            navController = navController,
            snackbarHostState = snackbarHostState,
            bookmarksArticles = state.bookmarksArticles,
            iconId = iconId,
            isSearchSectionActive = state.isSearchSectionActive,
            onThemeIconClick = onThemeIconClick,
            onSearchToggled = {
                viewModel.onEvent(BookmarksEvents.ToggleSearchSection)
            },
            onCancelClick = {
                viewModel.onEvent(BookmarksEvents.DeactivateSearchSection)
            },
            onSearchClicked = { query ->
                viewModel.onEvent(BookmarksEvents.SearchForArticles(query))
            },
            onArticleDelete = { article ->
                onArticleDelete(article)
            }
        )
    } else {
        LandscapeBookmarkContent(
            navController = navController,
            snackbarHostState = snackbarHostState,
            bookmarksArticles = state.bookmarksArticles,
            iconId = iconId,
            isSearchSectionActive = state.isSearchSectionActive,
            onThemeIconClick = onThemeIconClick,
            onSearchToggled = {
                viewModel.onEvent(BookmarksEvents.ToggleSearchSection)
            },
            onCancelClick = {
                viewModel.onEvent(BookmarksEvents.DeactivateSearchSection)
            },
            onSearchClicked = { query ->
                viewModel.onEvent(BookmarksEvents.SearchForArticles(query))
            },
            onArticleDelete = { article ->
                onArticleDelete(article)
            }
        )
    }

}
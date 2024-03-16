package com.example.newsapp.common.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.common.presentation.components.WebViewScreen
import com.example.newsapp.common.util.Screen
import com.example.newsapp.local.presentation.feature_bookmarks.BookmarksScreen
import com.example.newsapp.local.presentation.feature_profile.ProfileScreen
import com.example.newsapp.local.presentation.feature_splash.SplashScreen
import com.example.newsapp.remote.presentation.feature_home_news.HomeNewsScreen
import com.example.newsapp.remote.presentation.feature_search_news.SearchScreen

@Composable
fun ScreensHolder(
    iconId: Int,
    isPortrait: Boolean,
    onThemeIconClick: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeNewsScreen(
                navController = navController,
                isPortrait = isPortrait,
                iconId = iconId,
                onThemeIconClick = onThemeIconClick
            )
        }
        composable(
            Screen.WebViewScreen.route +
                    "?articleUrl={articleUrl}",
            arguments = listOf(
                navArgument(
                    name = "articleUrl"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                })
        ) {
            val url = it.arguments?.getString("articleUrl") ?: ""

            WebViewScreen(url = url, navController = navController)

        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(
                navController = navController,
                isPortrait = isPortrait,
                iconId = iconId,
                onThemeIconClick = onThemeIconClick
            )
        }
        composable(Screen.BookmarkNewsScreen.route) {
            BookmarksScreen(
                navController = navController,
                isPortrait = isPortrait,
                iconId = iconId,
                onThemeIconClick = onThemeIconClick
            )
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(
                navController = navController,
                isPortrait = isPortrait,
                iconId = iconId,
                onThemeIconClick = onThemeIconClick
            )
        }
    }
}
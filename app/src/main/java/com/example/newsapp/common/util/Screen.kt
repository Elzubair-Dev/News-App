package com.example.newsapp.common.util

import com.example.newsapp.R

sealed class Screen(
    val route: String,
    val label: String?,
    val icon: Int?
){
    object SplashScreen: Screen("splash_screen", null, null)
    object HomeScreen: Screen("home_screen", "Home", R.drawable.ic_home_empty)
    object BookmarkNewsScreen: Screen("bookmark_news_screen","Bookmark", R.drawable.ic_bookmark)
    object ProfileScreen: Screen("profile_screen","Profile", R.drawable.ic_profile_empty)
    object SearchScreen: Screen("search_screen","Search", R.drawable.ic_search)
    object WebViewScreen: Screen("web_view_screen",null, null)
}

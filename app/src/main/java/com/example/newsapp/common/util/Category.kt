package com.example.newsapp.common.util

import androidx.compose.ui.graphics.Color

data class Category(
    val title: String = "",
    val mainTag: String = title.lowercase(),
    val iconID: Int? = null,
    val color: Color? = null
)

package com.example.newsapp.local.presentation.feature_profile

import android.graphics.Bitmap

data class ProfileState(
    val name: String = "",
    val image: Bitmap? = null,
    val category: String = "sports",
    val countryCode: String = "gb",
    val tag: String = "football"
)

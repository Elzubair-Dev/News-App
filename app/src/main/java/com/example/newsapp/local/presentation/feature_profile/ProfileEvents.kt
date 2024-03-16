package com.example.newsapp.local.presentation.feature_profile

import android.graphics.Bitmap
import com.example.newsapp.common.util.Category

sealed class ProfileEvents {
    data class EnterName(val name: String): ProfileEvents()
    data class ChangeImage(val image: Bitmap): ProfileEvents()
    data class ChooseCategory(val category: Category): ProfileEvents()
    data class ChooseCountry(val countryCode: String): ProfileEvents()
    data class ChooseTag(val tag: String): ProfileEvents()
}
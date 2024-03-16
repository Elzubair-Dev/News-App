@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newsapp.local.presentation.feature_profile.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.common.presentation.components.BottomBar
import com.example.newsapp.common.presentation.components.BottomSheet
import com.example.newsapp.common.presentation.components.TopBar
import com.example.newsapp.common.util.Category
import com.example.newsapp.common.util.Common.Categories
import com.example.newsapp.common.util.Common.Countries
import com.example.newsapp.common.util.Screen
import com.example.newsapp.common.util.Tag

@Composable
fun LandscapeProfileContent(
    navController: NavController,
    iconId: Int,
    name: String,
    tags: List<Tag>,
    image: Bitmap?,
    currentCountryCode: String,
    currentCategory: String,
    currentTag: String,
    onImageChange: (Bitmap) -> Unit,
    onEnterName: (String) -> Unit,
    onCountryClick: (String) -> Unit,
    onTagClick: (String) -> Unit,
    onCategoryClick: (Category) -> Unit,
    onThemeIconClick: () -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheet(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                text = "Profile",
                iconId = iconId,
                onThemeIconClick = onThemeIconClick
            )
        },
        sheetContent = {
            BottomBar(
                navController = navController,
                pageName = Screen.ProfileScreen.label
            )
        },
        screenContent = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding(),
                        bottom = it.calculateBottomPadding(),
                        start = 8.dp,
                        end = 8.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                items(count = 1) {
                    Spacer(modifier = Modifier.padding(16.dp))
                    UserInfoSection(
                        name = name,
                        image = image,
                        onEnterName = onEnterName,
                        onImageChange = onImageChange
                    )

                    Spacer(modifier = Modifier.padding(16.dp))

                    Text(
                        text = "-Customize your app-",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.padding(8.dp))

                    CountriesSection(
                        currentCountryCode = currentCountryCode,
                        countries = Countries,
                        onCountryClick = onCountryClick
                    )

                    Spacer(modifier = Modifier.padding(16.dp))

                    CategoriesSection(
                        currentCategory = currentCategory,
                        categories = Categories,
                        onCategoryClick = onCategoryClick
                    )
                    Spacer(modifier = Modifier.padding(16.dp))

                    TagsSection(
                        currentTag = currentTag,
                        tags = tags,
                        onTagClick = onTagClick
                    )
                }
            }
        }
    )
}
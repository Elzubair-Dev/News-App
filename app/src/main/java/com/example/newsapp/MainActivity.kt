package com.example.newsapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.common.presentation.ScreensHolder
import com.example.newsapp.common.presentation.ScreensHolderVM
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: ScreensHolderVM = hiltViewModel()

            NewsAppTheme(darkTheme = viewModel.darkTheme.value) {

                val config = LocalConfiguration.current

                var isPortrait by remember {
                    mutableStateOf(false)
                }

                isPortrait = config.orientation == Configuration.ORIENTATION_PORTRAIT

                ScreensHolder(
                    iconId = viewModel.themeIcon.value,
                    isPortrait = isPortrait,
                    onThemeIconClick = viewModel::onDarkThemeChange
                )
            }
        }
    }
}

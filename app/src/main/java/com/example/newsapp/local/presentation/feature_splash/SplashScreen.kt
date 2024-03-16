package com.example.newsapp.local.presentation.feature_splash

import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newsapp.R
import com.example.newsapp.common.util.Screen
import com.example.newsapp.local.presentation.feature_splash.components.OrientationLock

@Composable
fun SplashScreen(
    navController: NavController
) {
    OrientationLock(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.tv)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        speed = 0.6f
    )

    LaunchedEffect(key1 = progress >= 0.93f) {
        if (progress >= 0.93f) {
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = false
                }
                launchSingleTop = true
                restoreState = false
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {

        LottieAnimation(
            composition = composition,
            progress = { progress }
        )
    }
}
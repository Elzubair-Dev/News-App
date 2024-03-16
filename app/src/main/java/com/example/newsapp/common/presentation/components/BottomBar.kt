package com.example.newsapp.common.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.common.util.Screen

@Composable
fun BottomBar(
    navController: NavController,
    pageName: String? = Screen.HomeScreen.label,
    subEvent:()->Unit = {}
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        BottomNav(
            navController = navController,
            pageName = pageName,
            subEvent = subEvent
        )
    }
}
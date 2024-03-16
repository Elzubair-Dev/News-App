package com.example.newsapp.common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.newsapp.R
import com.example.newsapp.ui.theme.Purple
import com.example.newsapp.common.util.Screen

@Composable
fun BottomNav(
    navController: NavController,
    pageName: String?,
    subEvent: () -> Unit = {}
) {
    val items = listOf(
        Screen.HomeScreen,
        Screen.SearchScreen,
        Screen.BookmarkNewsScreen,
        Screen.ProfileScreen
    )

    NavigationBar(
        modifier = Modifier
            .padding(
                start = 12.dp,
                end = 12.dp
            ),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        items.forEach {
            NavigationBarItem(
                selected = false,
                onClick = {
                    it.route.let { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    subEvent()
                },
                icon = {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_remove_24),
                            contentDescription = null,
                            tint = if (it.label == pageName) Purple else MaterialTheme.colorScheme.background
                        )
                        Spacer(modifier = Modifier.padding(top = 1.dp))

                        Icon(
                            painter = painterResource(id = it.icon!!),
                            contentDescription = "Screen Icon",
                            modifier = Modifier
                                .size(20.dp),
                            tint = if (it.label == pageName) Purple else MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    }
}
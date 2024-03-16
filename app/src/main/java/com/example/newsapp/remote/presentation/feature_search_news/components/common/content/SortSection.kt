package com.example.newsapp.remote.presentation.feature_search_news.components.common.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.common.util.Sort

@Composable
fun SortSection(
    thereIsResults: Boolean,
    sortName: String,
    sortList: List<Sort>,
    onSortClick: (Sort) -> Unit
) {


    var showSortedList by remember {
        mutableStateOf(false)
    }




    if (thereIsResults) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sorted by: $sortName",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.baseline_sort_24
                    ),
                    contentDescription = "Sorting icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            showSortedList = !showSortedList
                        }
                        .padding(4.dp)
                )
                DropdownMenu(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .border(width = 0.5.dp, color = MaterialTheme.colorScheme.onBackground),
                    expanded = showSortedList,
                    onDismissRequest = { showSortedList = false }
                ) {
                    sortList.forEach { sortType ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = sortType.name,
                                    style = MaterialTheme.typography.titleMedium)
                            }, onClick = {
                                onSortClick(sortType)
                                showSortedList = false
                            },
                            colors = MenuDefaults.itemColors(
                                textColor = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                }
            }
        }
    }
}
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newsapp.common.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheet(
    isLoading: Boolean = false,
    scaffoldState: BottomSheetScaffoldState,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    sheetContent: @Composable () -> Unit,
    topBar: @Composable () -> Unit,
    screenShimmer: @Composable (PaddingValues) -> Unit = {},
    screenContent: @Composable (PaddingValues) -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        BottomSheetScaffold(
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
            scaffoldState = scaffoldState,
            topBar = {
                topBar()
            },
            sheetContent = {
                sheetContent()
            },
            sheetPeekHeight = 50.dp,
            sheetContainerColor = MaterialTheme.colorScheme.background,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            if (isLoading) {
                screenShimmer(it)
            } else {
                screenContent(it)
            }
        }
    }
}
package com.aamir.compose.eBooksLibrary.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.aamir.compose.eBooksLibrary.app.AppNavGraph
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryNavigation
import com.aamir.compose.eBooksLibrary.core.presentation.MainAppBar
import com.aamir.compose.eBooksLibrary.core.presentation.SecondaryAppBar

@Composable
fun MainComposable(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navActions: EBooksLibraryNavigation = remember(navController) {
        EBooksLibraryNavigation(navController)
    }
    val showBottomBar = remember { mutableStateOf(false) }
    val topAppBarType = remember { mutableStateOf<TopAppBarType>(TopAppBarType.MainAppBar("")) }

    Scaffold(
        topBar = {
            when (topAppBarType.value) {
                is TopAppBarType.MainAppBar -> MainAppBar(
                    title = (topAppBarType.value as TopAppBarType.MainAppBar).title,
                    onSearchClick = {
                        navActions.navigateToNoteSearchScreen()
                    },
                    onNotificationsClick = {
                        navActions.navigateToNoteNotificationsScreen()
                    }
                )

                is TopAppBarType.SecondaryAppBar -> SecondaryAppBar(
                    title = (topAppBarType.value as TopAppBarType.SecondaryAppBar).title,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )

                is TopAppBarType.SecondaryAppBarNoBack -> SecondaryAppBar(
                    title = (topAppBarType.value as TopAppBarType.SecondaryAppBarNoBack).title,
                    isShowBackIcon = false
                )
            }
        },
        bottomBar = {
            if (showBottomBar.value) {
                AppBottomBar(
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White)) {
            AppNavGraph(
                modifier = modifier,
                navController = navController,
                navActions = navActions,
                showBottomBar = {
                    showBottomBar.value = it
                },
                topAppBarType = {
                    topAppBarType.value = it
                }
            )
        }
    }
}

sealed interface TopAppBarType {
    data class MainAppBar(val title: String) : TopAppBarType
    data class SecondaryAppBar(val title: String) : TopAppBarType
    data class SecondaryAppBarNoBack(val title: String) : TopAppBarType
}



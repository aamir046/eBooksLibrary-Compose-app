package com.aamir.compose.eBooksLibrary.presentation.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.aamir.compose.eBooksLibrary.app.AppNavGraph

@Composable
fun MainComposable(
    modifier: Modifier = Modifier,
){
    val navController = rememberNavController()
    val showBottomBar = remember { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            if (showBottomBar.value) {
                AppBottomBar(
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding))
        AppNavGraph(
            modifier = modifier,
            navController = navController,
            showBottomBar = {
                showBottomBar.value = it
            }
        )
    }
}



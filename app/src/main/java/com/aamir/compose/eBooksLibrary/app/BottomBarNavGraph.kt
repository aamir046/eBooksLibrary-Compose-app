package com.aamir.compose.eBooksLibrary.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aamir.compose.eBooksLibrary.core.presentation.SharedViewModel
import com.aamir.compose.eBooksLibrary.presentation.home.HomeScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.home.HomeViewModel
import com.aamir.compose.eBooksLibrary.presentation.main.components.AppBottomBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun BottomBarSectionsNavGraph(
    rootNavController: NavHostController,
    startDestination: String = EBooksLibraryAppDestinations.HOME_ROUTE
) {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            AppBottomBar(navController = bottomNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding),
            route = "main_graph"
        ) {
            composable(EBooksLibraryAppDestinations.HOME_ROUTE) { backStackEntry ->
                val viewModel = koinViewModel<HomeViewModel>()
                val selectedBookViewModel = backStackEntry.sharedKoinViewModel<SharedViewModel>(rootNavController)

                LaunchedEffect(true) { selectedBookViewModel.onSelectBook(null) }

                HomeScreenRoot(
                    viewModel = viewModel,
                    onBookClick = { book ->
                        selectedBookViewModel.onSelectBook(book)
                        rootNavController.navigate(EBooksLibraryAppDestinations.BOOK_DETAILS_ROUTE)
                    },
                    onSearchClick = {
                        rootNavController.navigate(EBooksLibraryAppDestinations.SEARCH_ROUTE)
                    },
                    onNotificationsClick = {
                        rootNavController.navigate(EBooksLibraryAppDestinations.NOTIFICATIONS_ROUTE)
                    }
                )
            }
            composable(EBooksLibraryAppDestinations.CATEGORIES_ROUTE) {
               // CategoriesScreenRoot()
            }
            composable(EBooksLibraryAppDestinations.AUTHORS_ROUTE) {
               // AuthorsScreenRoot()
            }
            composable(EBooksLibraryAppDestinations.PROFILE_ROUTE) {
               // ProfileScreenRoot()
            }
        }
    }
}

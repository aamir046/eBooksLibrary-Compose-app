package com.aamir.compose.eBooksLibrary.app

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aamir.compose.eBooksLibrary.core.presentation.SharedViewModel
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.BookDetailsScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.BookDetailsViewModel
import com.aamir.compose.eBooksLibrary.presentation.home.HomeScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.home.HomeViewModel
import com.aamir.compose.eBooksLibrary.presentation.notifications.NotificationsScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.notifications.NotificationsViewModel
import com.aamir.compose.eBooksLibrary.presentation.search.SearchScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.search.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = EBooksLibraryAppDestinations.HOME_ROUTE,
    navActions: EBooksLibraryNavigation = remember(navController) {
        EBooksLibraryNavigation(navController)
    },
    showBottomBar:(Boolean)->Unit={}
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        route = "eBookLibraryGraph",
        enterTransition = { slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth }, // Slide from right
            animationSpec = tween(
                durationMillis = 200,
                easing = FastOutSlowInEasing
            )
        ) },
        exitTransition = { slideOutHorizontally() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {

        composable(
            EBooksLibraryAppDestinations.HOME_ROUTE
        ) { backStackEntry ->

            val viewModel = koinViewModel<HomeViewModel>()
            val selectedBookViewModel =
                backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)

            LaunchedEffect(true) {
                selectedBookViewModel.onSelectBook(null)
                showBottomBar.invoke(true)
            }

            HomeScreenRoot(
                viewModel = viewModel,
                onBookClick = { book ->
                    selectedBookViewModel.onSelectBook(book)
                    navActions.navigateToNoteDetailsScreen()
                },
                onSearchClick = {
                        navActions.navigateToNoteSearchScreen()
                },
                onNotificationsClick = {
                        navActions.navigateToNoteNotificationsScreen()
                }
            )
        }

        composable(
            EBooksLibraryAppDestinations.BOOK_DETAILS_ROUTE
        ) {backStackEntry ->

            val selectedBookViewModel =
                backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)
            val viewModel = koinViewModel<BookDetailsViewModel>()
            val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

            LaunchedEffect(selectedBook) {
                selectedBook?.let {
                    viewModel.onSelectBook(selectedBook)
                }
                showBottomBar.invoke(false)
            }

            BookDetailsScreenRoot(
                viewModel = viewModel,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            EBooksLibraryAppDestinations.SEARCH_ROUTE
        ) {backStackEntry ->

            val viewModel = koinViewModel<SearchViewModel>()
            val selectedBookViewModel =
                backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)

            LaunchedEffect(true) {
                selectedBookViewModel.onSelectBook(null)
                showBottomBar.invoke(false)
            }

            SearchScreenRoot(
                viewModel = viewModel,
                onSearchResultSelected = {book->
                    selectedBookViewModel.onSelectBook(book)
                    navActions.navigateToNoteDetailsScreen()
                },
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            EBooksLibraryAppDestinations.NOTIFICATIONS_ROUTE
        ) {
            val viewModel = koinViewModel<NotificationsViewModel>()
            LaunchedEffect(true) {
                showBottomBar.invoke(false)
            }
            NotificationsScreenRoot(
                viewModel = viewModel,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }

        composable(EBooksLibraryAppDestinations.CATEGORIES_ROUTE) {

        }
        composable(EBooksLibraryAppDestinations.AUTHORS_ROUTE) {

        }
        composable(EBooksLibraryAppDestinations.PROFILE_ROUTE) {

        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}

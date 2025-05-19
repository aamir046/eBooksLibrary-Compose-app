package com.aamir.compose.eBooksLibrary.app

import androidx.compose.animation.core.SnapSpec
import androidx.compose.animation.slideInHorizontally
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
import com.aamir.compose.eBooksLibrary.presentation.authors.AuthorsScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.authors.AuthorsViewModel
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.BookDetailsScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.BookDetailsViewModel
import com.aamir.compose.eBooksLibrary.presentation.categories.CategoriesScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.categories.CategoriesViewModel
import com.aamir.compose.eBooksLibrary.presentation.home.HomeScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.home.HomeViewModel
import com.aamir.compose.eBooksLibrary.presentation.main.components.TopAppBarType
import com.aamir.compose.eBooksLibrary.presentation.notifications.NotificationsScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.notifications.NotificationsViewModel
import com.aamir.compose.eBooksLibrary.presentation.profiile.ProfileScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.profiile.ProfileViewModel
import com.aamir.compose.eBooksLibrary.presentation.search.SearchScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.search.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = EBooksLibraryAppDestinations.HOME_ROUTE,
    navActions: EBooksLibraryNavigation = EBooksLibraryNavigation(navController),
    showBottomBar:(Boolean)->Unit={},
    topAppBarType:(TopAppBarType)->Unit={}
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
            animationSpec = SnapSpec(
               100
            )
        ) },
    ) {

        composable(
            EBooksLibraryAppDestinations.HOME_ROUTE) { backStackEntry ->

            val viewModel = koinViewModel<HomeViewModel>()
            val selectedBookViewModel =
                backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)

            LaunchedEffect(true) {
                selectedBookViewModel.onSelectBook(null)
            }

            showBottomBar.invoke(true)
            topAppBarType.invoke(TopAppBarType.MainAppBar("Home"))

            HomeScreenRoot(
                viewModel = viewModel,
                onBookClick = { book ->
                    selectedBookViewModel.onSelectBook(book)
                    navActions.navigateToNoteDetailsScreen()
                }
            )
        }

        composable(
            EBooksLibraryAppDestinations.BOOK_DETAILS_ROUTE) {backStackEntry ->

            val selectedBookViewModel =
                backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)
            val viewModel = koinViewModel<BookDetailsViewModel>()
            val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

            LaunchedEffect(selectedBook) {
                selectedBook?.let {
                    viewModel.onSelectBook(selectedBook)
                }
            }

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("Book Details"))

            BookDetailsScreenRoot(
                viewModel = viewModel,
                
            )
        }

        composable(
            EBooksLibraryAppDestinations.SEARCH_ROUTE) {backStackEntry ->

            val viewModel = koinViewModel<SearchViewModel>()
            val selectedBookViewModel =
                backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)

            LaunchedEffect(true) {
                selectedBookViewModel.onSelectBook(null)
            }

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("Search"))

            SearchScreenRoot(
                viewModel = viewModel,
                onSearchResultSelected = {book->
                    selectedBookViewModel.onSelectBook(book)
                    navActions.navigateToNoteDetailsScreen()
                },
                
            )
        }

        composable(EBooksLibraryAppDestinations.NOTIFICATIONS_ROUTE) {
            val viewModel = koinViewModel<NotificationsViewModel>()

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("Notifications"))

            NotificationsScreenRoot(
                viewModel = viewModel,
                
            )
        }

        composable(route = EBooksLibraryAppDestinations.CATEGORIES_ROUTE) {
            val viewModel = koinViewModel<CategoriesViewModel>()

            showBottomBar.invoke(true)
            topAppBarType.invoke(TopAppBarType.MainAppBar("Categories"))

            CategoriesScreenRoot(
                viewModel = viewModel
            )
        }
        composable(EBooksLibraryAppDestinations.AUTHORS_ROUTE) {
            val viewModel = koinViewModel<AuthorsViewModel>()

            showBottomBar.invoke(true)
            topAppBarType.invoke(TopAppBarType.MainAppBar("Authors"))

            AuthorsScreenRoot(
                viewModel = viewModel
            )
        }
        composable(EBooksLibraryAppDestinations.PROFILE_ROUTE) {
            val viewModel = koinViewModel<ProfileViewModel>()

            showBottomBar.invoke(true)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBarNoBack("Profile"))

            ProfileScreenRoot(
                viewModel = viewModel
            )
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

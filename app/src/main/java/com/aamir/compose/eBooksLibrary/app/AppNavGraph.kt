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
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.ADDRESS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.AUTHORS_DETAILS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.AUTHORS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.BOOK_DETAILS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.CATEGORIES_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.FAVOURITES_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.HELP_CENTER_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.HOME_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.MY_ACCOUNT_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.NOTIFICATIONS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.PROFILE_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.PROMOS_OFFER_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.SEARCH_ROUTE
import com.aamir.compose.eBooksLibrary.core.presentation.SharedViewModel
import com.aamir.compose.eBooksLibrary.presentation.authors.authordetails.AuthorDetailsScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.authors.authordetails.AuthorDetailsViewModel
import com.aamir.compose.eBooksLibrary.presentation.authors.authorslisting.AuthorsScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.authors.authorslisting.AuthorsViewModel
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.BookDetailsScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.BookDetailsViewModel
import com.aamir.compose.eBooksLibrary.presentation.categories.CategoriesScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.categories.CategoriesViewModel
import com.aamir.compose.eBooksLibrary.presentation.home.HomeScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.home.HomeViewModel
import com.aamir.compose.eBooksLibrary.presentation.main.components.TopAppBarType
import com.aamir.compose.eBooksLibrary.presentation.notifications.NotificationsScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.notifications.NotificationsViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.profiile.ProfileScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.userprofile.profiile.ProfileViewModel
import com.aamir.compose.eBooksLibrary.presentation.search.SearchScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.search.SearchViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.address.AddressScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.userprofile.address.AddressViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites.FavouritesScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites.FavouritesViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter.HelpCenterScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter.HelpCenterViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount.MyAccountScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount.MyAccountViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.offersndpromos.OffersAndPromosScreenRoot
import com.aamir.compose.eBooksLibrary.presentation.userprofile.offersndpromos.OffersAndPromosViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
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

        composable(HOME_ROUTE) { backStackEntry ->

            val viewModel = koinViewModel<HomeViewModel>()
            val sharedViewModel =
                backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)

            LaunchedEffect(true) {
                sharedViewModel.onSelectBook(null)
            }

            showBottomBar.invoke(true)
            topAppBarType.invoke(TopAppBarType.MainAppBar("Home"))

            HomeScreenRoot(
                viewModel = viewModel,
                onBookClick = { book ->
                    sharedViewModel.onSelectBook(book)
                    navActions.navigateToBookDetailsScreen()
                }
            )
        }

        composable(BOOK_DETAILS_ROUTE) {backStackEntry ->

            val sharedViewModel =
                backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)
            val viewModel = koinViewModel<BookDetailsViewModel>()
            val selectedBook by sharedViewModel.selectedBook.collectAsStateWithLifecycle()

            LaunchedEffect(selectedBook) {
                selectedBook?.let {
                    viewModel.onSelectBook(selectedBook)
                }
            }

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("Book Details"))

            BookDetailsScreenRoot(viewModel = viewModel)
        }

        composable(SEARCH_ROUTE) {backStackEntry ->

            val viewModel = koinViewModel<SearchViewModel>()
            val sharedViewModel =
                backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)

            LaunchedEffect(true) {
                sharedViewModel.onSelectBook(null)
            }

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("Search"))

            SearchScreenRoot(
                viewModel = viewModel,
                onSearchResultSelected = {book->
                    sharedViewModel.onSelectBook(book)
                    navActions.navigateToBookDetailsScreen()
                },

            )
        }

        composable(NOTIFICATIONS_ROUTE) {
            val viewModel = koinViewModel<NotificationsViewModel>()

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("Notifications"))

            NotificationsScreenRoot(viewModel = viewModel)
        }

        composable(CATEGORIES_ROUTE) { backStackEntry->
            val viewModel = koinViewModel<CategoriesViewModel>()

            val sharedViewModel =
                backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)

            LaunchedEffect(true) {
                sharedViewModel.onSelectBook(null)
            }

            showBottomBar.invoke(true)
            topAppBarType.invoke(TopAppBarType.MainAppBar("Categories"))

            CategoriesScreenRoot(
                viewModel = viewModel,
                onBookClick = {book->
                    sharedViewModel.onSelectBook(book)
                    navActions.navigateToBookDetailsScreen()
                }
            )
        }

        composable(AUTHORS_ROUTE) {backStackEntry ->
            val viewModel = koinViewModel<AuthorsViewModel>()

            val sharedViewModel =
                backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)

            LaunchedEffect(true) {
                sharedViewModel.onSelectAuthor(null)
            }

            showBottomBar.invoke(true)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBarNoBack("Authors"))

            AuthorsScreenRoot(
                viewModel = viewModel,
                onAuthorSelected = {author->
                    sharedViewModel.onSelectAuthor(author)
                    navActions.navigateToAuthorDetailsScreen()
                }
            )
        }

        composable(PROFILE_ROUTE) {
            val viewModel = koinViewModel<ProfileViewModel>()

            showBottomBar.invoke(true)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBarNoBack("Profile"))

            ProfileScreenRoot(
                viewModel = viewModel,
                onProfileListingItemClick = { targetRoute->
                    when(targetRoute){
                        MY_ACCOUNT_ROUTE->navActions.navigateToMyAccountScreen()
                        ADDRESS_ROUTE->navActions.navigateToAddressScreen()
                        PROMOS_OFFER_ROUTE->navActions.navigateToPromosOfferScreen()
                        HELP_CENTER_ROUTE->navActions.navigateToHelpCenterScreen()
                        FAVOURITES_ROUTE->navActions.navigateToFavoritesScreen()
                    }

                }
            )
        }

        composable(MY_ACCOUNT_ROUTE) {
            val viewModel = koinViewModel<MyAccountViewModel>()

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("My Account"))

            MyAccountScreenRoot(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(AUTHORS_DETAILS_ROUTE) {backStackEntry ->

            val sharedViewModel = backStackEntry.sharedKoinViewModel<SharedViewModel>(navController)

            val viewModel = koinViewModel<AuthorDetailsViewModel>()
            val selectedAuthor by sharedViewModel.selectedAuthor.collectAsStateWithLifecycle()

            LaunchedEffect(true) {
                sharedViewModel.onSelectBook(null)
            }

            LaunchedEffect(selectedAuthor) {
                selectedAuthor?.let {
                    viewModel.onSelectAuthor(selectedAuthor)
                }
            }

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("Author Details"))

            AuthorDetailsScreenRoot(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onBookSelected = {book->
                    sharedViewModel.onSelectBook(book)
                    navActions.navigateToBookDetailsScreen()
                }
            )
        }

        composable(ADDRESS_ROUTE) {
            val viewModel = koinViewModel<AddressViewModel>()

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("Address"))

            AddressScreenRoot(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(PROMOS_OFFER_ROUTE) {
            val viewModel = koinViewModel<OffersAndPromosViewModel>()

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("Offers & Promos"))

            OffersAndPromosScreenRoot(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(FAVOURITES_ROUTE) {
            val viewModel = koinViewModel<FavouritesViewModel>()

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("Favourites"))

            FavouritesScreenRoot(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(HELP_CENTER_ROUTE) {
            val viewModel = koinViewModel<HelpCenterViewModel>()

            showBottomBar.invoke(false)
            topAppBarType.invoke(TopAppBarType.SecondaryAppBar("Help Center"))

            HelpCenterScreenRoot(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
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

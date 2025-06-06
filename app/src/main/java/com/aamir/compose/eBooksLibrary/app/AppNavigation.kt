package com.aamir.compose.eBooksLibrary.app


import androidx.navigation.NavHostController
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppArgs.BOOK_ARG
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.ADDRESS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.BOOK_DETAILS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.FAVOURITES_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.HELP_CENTER_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.HOME_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.MY_ACCOUNT_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.NOTIFICATIONS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.PROMOS_OFFER_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.SEARCH_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.ADDRESS_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.AUTHORS_DETAILS_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.AUTHORS_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.BOOK_DETAILS_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.CATEGORIES_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.FAVOURITES_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.HELP_CENTER_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.HOME_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.MY_ACCOUNT_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.NOTIFICATIONS_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.PROFILE_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.PROMOS_OFFER_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.SEARCH_SCREEN

/**
 * Screens used in [EBooksLibraryAppDestinations]
 */
private object EBooksLibraryAppScreens {
    const val HOME_SCREEN = "HOME_SCREEN"
    const val BOOK_DETAILS_SCREEN = "BOOK_DETAILS_SCREEN"
    const val SEARCH_SCREEN = "SEARCH_SCREEN"
    const val NOTIFICATIONS_SCREEN = "NOTIFICATIONS_SCREEN"
    const val CATEGORIES_SCREEN = "CATEGORIES_SCREEN"
    const val AUTHORS_SCREEN = "AUTHORS_SCREEN"
    const val AUTHORS_DETAILS_SCREEN = "AUTHORS_DETAILS_SCREEN"
    const val PROFILE_SCREEN = "PROFILE_SCREEN"
    const val MY_ACCOUNT_SCREEN = "MY_ACCOUNT_SCREEN"
    const val PROMOS_OFFER_SCREEN = "PROMOS_OFFER_SCREEN"
    const val FAVOURITES_SCREEN = "FAVOURITES_SCREEN"
    const val ADDRESS_SCREEN = "ADDRESS_SCREEN"
    const val HELP_CENTER_SCREEN = "HELP_CENTER_SCREEN"
}

/**
 * Arguments used in [EBooksLibraryAppDestinations] routes
 */
object EBooksLibraryAppArgs {
    const val USER_MESSAGE_ARG = "userMessage"
    const val BOOK_ARG = "book"
}

/**
 * Destinations used in the [MainActivity]
 */
object EBooksLibraryAppDestinations {
    const val HOME_ROUTE = HOME_SCREEN
    const val BOOK_DETAILS_ROUTE =  BOOK_DETAILS_SCREEN
    const val SEARCH_ROUTE =  SEARCH_SCREEN
    const val NOTIFICATIONS_ROUTE =  NOTIFICATIONS_SCREEN
    const val CATEGORIES_ROUTE = CATEGORIES_SCREEN
    const val AUTHORS_ROUTE = AUTHORS_SCREEN
    const val AUTHORS_DETAILS_ROUTE = AUTHORS_DETAILS_SCREEN
    const val PROFILE_ROUTE = PROFILE_SCREEN
    const val MY_ACCOUNT_ROUTE = MY_ACCOUNT_SCREEN
    const val ADDRESS_ROUTE = ADDRESS_SCREEN
    const val PROMOS_OFFER_ROUTE = PROMOS_OFFER_SCREEN
    const val HELP_CENTER_ROUTE = HELP_CENTER_SCREEN
    const val FAVOURITES_ROUTE = FAVOURITES_SCREEN
}

/**
 * Models the navigation actions in the app.
 */
class EBooksLibraryNavigation(private val navController: NavHostController) {
    fun navigateToBookDetailsScreen() {
        navController.navigate(
            BOOK_DETAILS_ROUTE
        )
    }

    fun navigateToSearchScreen() {
        navController.navigate(
            SEARCH_ROUTE
        )
    }
    fun navigateToNotificationsScreen() {
        navController.navigate(
            NOTIFICATIONS_ROUTE
        )
    }
    fun navigateToAuthorDetailsScreen() {
        navController.navigate(
            AUTHORS_DETAILS_SCREEN
        )
    }
    fun navigateToMyAccountScreen() {
        navController.navigate(
            MY_ACCOUNT_ROUTE
        )
    }
    fun navigateToAddressScreen() {
        navController.navigate(
            ADDRESS_ROUTE
        )
    }
    fun navigateToPromosOfferScreen() {
        navController.navigate(
            PROMOS_OFFER_ROUTE
        )
    }
    fun navigateToFavoritesScreen() {
        navController.navigate(
            FAVOURITES_ROUTE
        )
    }
    fun navigateToHelpCenterScreen() {
        navController.navigate(
            HELP_CENTER_ROUTE
        )
    }

}

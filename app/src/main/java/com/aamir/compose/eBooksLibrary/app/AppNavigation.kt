package com.aamir.compose.eBooksLibrary.app


import androidx.navigation.NavHostController
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppArgs.BOOK_ARG
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.BOOK_DETAILS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.HOME_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.NOTIFICATIONS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.SEARCH_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.AUTHORS_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.BOOK_DETAILS_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.CATEGORIES_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.HOME_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.NOTIFICATIONS_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.PROFILE_SCREEN
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
    const val PROFILE_SCREEN = "PROFILE_SCREEN"
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
    const val PROFILE_ROUTE = PROFILE_SCREEN
}

/**
 * Models the navigation actions in the app.
 */
class EBooksLibraryNavigation(private val navController: NavHostController) {
    fun navigateToNoteDetailsScreen() {
        navController.navigate(
            BOOK_DETAILS_ROUTE
        )
    }

    fun navigateToNoteSearchScreen() {
        navController.navigate(
            SEARCH_ROUTE
        )
    }
    fun navigateToNoteNotificationsScreen() {
        navController.navigate(
            NOTIFICATIONS_ROUTE
        )
    }

}

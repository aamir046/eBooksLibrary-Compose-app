package com.aamir.compose.eBooksLibrary.app


import androidx.navigation.NavHostController
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppArgs.BOOK_ARG
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.BOOK_DETAILS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.BOOK_DETAILS_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.HOME_SCREEN
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.SEARCH_SCREEN

/**
 * Screens used in [EBooksLibraryAppDestinations]
 */
private object EBooksLibraryAppScreens {
    const val HOME_SCREEN = "HOME_SCREEN"
    const val BOOK_DETAILS_SCREEN = "BOOK_DETAILS_SCREEN"
    const val SEARCH_SCREEN = "SEARCH_SCREEN"
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
            SEARCH_SCREEN
        )
    }
}

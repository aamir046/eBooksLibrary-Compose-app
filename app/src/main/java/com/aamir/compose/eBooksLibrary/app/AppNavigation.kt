package com.aamir.compose.eBooksLibrary.app


import androidx.navigation.NavHostController
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppScreens.HOME_SCREEN

/**
 * Screens used in [EBooksLibraryAppDestinations]
 */
private object EBooksLibraryAppScreens {
    const val HOME_SCREEN = "home"
}

/**
 * Arguments used in [EBooksLibraryAppDestinations] routes
 */
object EBooksLibraryAppArgs {
    const val USER_MESSAGE_ARG = "userMessage"
    const val USER_NOTE_ARG = "userNote"
}

/**
 * Destinations used in the [MainActivity]
 */
object EBooksLibraryAppDestinations {
    const val HOME_ROUTE = "$HOME_SCREEN"
}

/**
 * Models the navigation actions in the app.
 */
class EBooksLibraryAppNavigation(private val navController: NavHostController) {

}

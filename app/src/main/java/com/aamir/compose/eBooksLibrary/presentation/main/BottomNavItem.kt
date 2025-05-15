package com.aamir.compose.eBooksLibrary.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.CATEGORIES_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.AUTHORS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.PROFILE_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.HOME_ROUTE

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    data object Home : BottomNavItem( HOME_ROUTE , "Home", Icons.Default.Home)
    data object Categories : BottomNavItem(CATEGORIES_ROUTE, "Categories", Icons.Default.List)
    data object Authors : BottomNavItem(AUTHORS_ROUTE, "Authors", Icons.Default.Person)
    data object Profile : BottomNavItem(PROFILE_ROUTE, "Profile", Icons.Default.AccountCircle)
}
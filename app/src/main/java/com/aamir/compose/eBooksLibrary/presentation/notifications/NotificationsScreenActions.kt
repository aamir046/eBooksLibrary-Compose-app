package com.aamir.compose.eBooksLibrary.presentation.notifications

sealed interface NotificationsScreenActions {
    data object OnBackClick : NotificationsScreenActions
}
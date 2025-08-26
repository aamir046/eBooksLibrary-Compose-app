package com.aamir.compose.eBooksLibrary.presentation.notifications

import com.aamir.compose.eBooksLibrary.domain.model.BookInfoNotification
import com.aamir.compose.eBooksLibrary.domain.model.GeneralNotification

sealed interface NotificationsItem {
    data class CurrentNotifications(val currentNotifications: List<Notifications>) : NotificationsItem
    data class OlderNotifications(val olderNotifications: List<Notifications>) : NotificationsItem
}

sealed interface Notifications {
    data class General(val generalNotification: GeneralNotification) : Notifications
    data class BookInfo(val bookInfoNotification: BookInfoNotification) : Notifications
}
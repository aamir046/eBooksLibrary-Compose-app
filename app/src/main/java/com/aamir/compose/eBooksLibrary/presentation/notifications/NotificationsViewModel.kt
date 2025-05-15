package com.aamir.compose.eBooksLibrary.presentation.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.domain.Book
import com.aamir.compose.eBooksLibrary.domain.BookInfoNotification
import com.aamir.compose.eBooksLibrary.domain.GeneralNotification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class NotificationsViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(NotificationsScreenState())
    val uiState = _uiState.onStart {
        createDummyNotificationForUI()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    private fun createDummyNotificationForUI() {
        val currentNotifications = listOf(
            Notifications.General(
                generalNotification = GeneralNotification(
                    title = "Welcome",
                    description = "Dive into your next chapter or explore new titles today",
                    dateTime = "05/14/2025: 05:10 PM"
                )
            ),
            Notifications.General(
                generalNotification = GeneralNotification(
                    title = "New Arrivals Just In",
                    description = "Check out the latest eBooks added to your library—don’t miss out!",
                    dateTime = "05/14/2025: 10:10 AM"
                )
            ),
            Notifications.BookInfo(
                bookInfoNotification = BookInfoNotification(
                    dateTime = "05/13/2025: 01:50 PM",
                    status = "Launching at 6-15-2025",
                    book =  Book(
                        author = "George Orwell",
                        title = "1984",
                        year = "1949",
                        description = "A chilling vision of a dystopian world under total surveillance.",
                        imageUrl = "https://images.penguinrandomhouse.com/cover/9780679417392"
                    )
                )
            )
        )
        val olderNotifications = listOf(
            Notifications.BookInfo(
                bookInfoNotification = BookInfoNotification(
                    dateTime = "05/12/2025: 12:30 PM",
                    status = "Newly Published",
                    book = Book(
                        author = "J.R.R. Tolkien",
                        title = "The Lord of the Rings",
                        year = "1954",
                        description = "An epic fantasy quest to destroy the One Ring and defeat evil.",
                        imageUrl = "https://images.gr-assets.com/books/1566425108l/33.jpg"
                    )
                )
            )
        )
        _uiState.update {
            it.copy(
                notificationItems = listOf(
                    NotificationsItem.CurrentNotifications(currentNotifications),
                    NotificationsItem.OlderNotifications(olderNotifications)
                )
            )
        }

    }
}
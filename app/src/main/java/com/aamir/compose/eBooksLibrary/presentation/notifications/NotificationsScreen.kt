package com.aamir.compose.eBooksLibrary.presentation.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.core.presentation.SecondaryAppBar
import com.aamir.compose.eBooksLibrary.presentation.notifications.components.NotificationsListing

@Composable
fun NotificationsScreenRoot(
    viewModel: NotificationsViewModel,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val onActions: (NotificationsScreenActions) -> Unit = { action ->
        when (action) {
            is NotificationsScreenActions.OnBackClick -> onBackClick()
        }
    }

    NotificationsScreen(
        uiState = uiState,
        modifier = Modifier,
        onActions = onActions
    )
}

@Composable
fun NotificationsScreen(
    uiState: NotificationsScreenState,
    modifier: Modifier = Modifier,
    onActions: (NotificationsScreenActions) -> Unit
) {
    Scaffold(
        topBar = {
            SecondaryAppBar(
                title = "Notifications",
                onBackClick = { onActions(NotificationsScreenActions.OnBackClick) }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ){
                items(uiState.notificationItems){notification->
                    when (notification) {
                        is NotificationsItem.CurrentNotifications -> NotificationsListing(
                            modifier = Modifier.padding(16.dp),
                            title = "Current",
                            notifications = notification.currentNotifications
                        )

                        is NotificationsItem.OlderNotifications -> NotificationsListing(
                            modifier = Modifier.padding(16.dp),
                            title = "Older",
                            notifications = notification.olderNotifications
                        )
                    }
                }
            }
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun NotificationsScreenPreview() {
    NotificationsScreen(
        uiState = NotificationsScreenState(),
        modifier = Modifier,
        onActions = {}
    )
}
package com.aamir.compose.eBooksLibrary.presentation.userprofile.profiile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.presentation.userprofile.profiile.components.ProfileListingCard
import com.aamir.compose.eBooksLibrary.presentation.userprofile.profiile.components.UserInfoCard

@Composable
fun ProfileScreenRoot(
    viewModel: ProfileViewModel,
    onProfileListingItemClick: (String) -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val onActions: (ProfileScreenActions) -> Unit = { action ->
        when(action){
            is ProfileScreenActions.OnProfileListingItemClick -> onProfileListingItemClick(action.targetRoute)
        }
    }

    ProfileScreen(
        uiState = uiState,
        modifier = Modifier,
        onActions = onActions
    )
}

@Composable
fun ProfileScreen(
    uiState: ProfileScreenState,
    modifier: Modifier = Modifier,
    onActions: (ProfileScreenActions) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        UserInfoCard(
            modifier = Modifier,
            userName = uiState.userName,
            userEmail = uiState.userEmail
        )
        Spacer(modifier = Modifier.padding(16.dp))
        uiState.profileListingItems.forEach {
            ProfileListingCard(
                modifier = Modifier,
                iconRes = it.iconRes,
                title = it.title,
                targetRoute = it.targetRoute,
                onItemClick = {targetRoute->
                    onActions(ProfileScreenActions.OnProfileListingItemClick(targetRoute))
                }
            )
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        uiState = ProfileScreenState(),
        modifier = Modifier,
        onActions = {}
    )
}

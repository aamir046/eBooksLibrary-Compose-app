package com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.core.presentation.FormEntry
import com.aamir.compose.eBooksLibrary.core.presentation.extensions.showToastMessage

@Composable
fun HelpCenterScreenRoot(
    viewModel: HelpCenterViewModel,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current


    val actions: (HelpCenterScreenActions) -> Unit = remember {
        { action ->
            when (action) {
                is HelpCenterScreenActions.OnBackClick -> onBackClick()
                else -> viewModel.onAction(action)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is HelpCenterScreenUiEvent.ShowMessage -> context.showToastMessage(event.message)
                is HelpCenterScreenUiEvent.NavigateBack -> onBackClick()
            }
        }
    }

    HelpCenterScreen(
        uiState = uiState,
        modifier = Modifier,
        actions = actions
    )
}

@Composable
fun HelpCenterScreen(
    modifier: Modifier = Modifier,
    uiState: HelpCenterScreenState = HelpCenterScreenState(),
    actions: (HelpCenterScreenActions) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Share your valuable feedback",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = "Tell us how we can help \uD83D\uDC4B",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
            )

            Text(
                text = "Chapter are standing by for service & support!",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))
        FormEntry(
            modifier = Modifier.padding(horizontal = 16.dp,vertical = 8.dp),
            title = "Email",
            label = "Enter your email",
            value = uiState.userFeedback.email,
            onValueChange = {
                actions.invoke(HelpCenterScreenActions.OnEmailChange(it))
            }
        )
        FormEntry(
            modifier = Modifier.padding(horizontal = 16.dp,vertical = 8.dp),
            title = "Subject",
            label = "Enter email subject",
            value = uiState.userFeedback.subject,
            onValueChange = {
                actions.invoke(HelpCenterScreenActions.OnSubjectChange(it))
            }
        )
        FormEntry(
            modifier = Modifier.padding(horizontal = 16.dp,vertical = 8.dp),
            title = "Description",
            label = "Enter complete description",
            value = uiState.userFeedback.description,
            onValueChange = {
                actions.invoke(HelpCenterScreenActions.OnDescriptionChange(it))
            }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(vertical = 40.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            onClick = {
                actions.invoke(HelpCenterScreenActions.OnShareFeedback)
            }
        ) {
            Text(text = "Share Feedback")
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun HelpCenterScreenPreview() {
    HelpCenterScreen(
        uiState = HelpCenterScreenState(),
        modifier = Modifier,
        actions = {}
    )
}
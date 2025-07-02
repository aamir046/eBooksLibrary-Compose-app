package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.core.presentation.LoadRemoteImage
import com.aamir.compose.eBooksLibrary.core.presentation.FormEntry
import com.aamir.compose.eBooksLibrary.presentation.theme.Gray

@Composable
fun MyAccountScreenRoot(
    viewModel: MyAccountViewModel,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val actions: (MyAccountScreenActions) -> Unit = { action ->
        when (action) {
            is MyAccountScreenActions.OnBackClick -> onBackClick()
            else -> viewModel.onAction(action)
        }
    }

    MyAccountScreen(
        uiState = uiState,
        modifier = Modifier,
        actions = actions
    )
}

@Composable
fun MyAccountScreen(
    modifier: Modifier = Modifier,
    uiState: MyAccountScreenState = MyAccountScreenState(),
    actions: (MyAccountScreenActions) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Gray)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .fillMaxHeight(0.845f)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.fillMaxHeight(0.14f))
            FormEntry(
                modifier = Modifier.padding(horizontal = 16.dp,vertical = 8.dp),
                title = "Name",
                label = "Enter name",
                value = uiState.userInfo?.name ?: "",
                onValueChange = {
                    actions.invoke(MyAccountScreenActions.OnNameChange(it))
                }
            )
            FormEntry(
                modifier = Modifier.padding(horizontal = 16.dp,vertical = 8.dp),
                title = "Email",
                label = "Enter email",
                value = uiState.userInfo?.email ?: "",
                onValueChange = {
                    actions.invoke(MyAccountScreenActions.OnEmailChange(it))
                }
            )
            FormEntry(
                modifier = Modifier.padding(horizontal = 16.dp,vertical = 8.dp),
                title = "Phone Number",
                label = "Enter Phone Number",
                value = uiState.userInfo?.phoneNumber ?: "",
                onValueChange = {
                    actions.invoke(MyAccountScreenActions.OnPhoneNumberChange(it))
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(vertical = 40.dp),
                colors = ButtonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                ),
                onClick = {
                    actions.invoke(MyAccountScreenActions.OnBackClick)
                }
            ) {
                Text(text = "Save Changes")
            }
        }

        LoadRemoteImage(
            url = uiState.userInfo?.imageUrl ?: "",
            contentDescription = "Profile Picture",
            modifier = Modifier
                .padding(top = 40.dp)
                .shadow(elevation = 8.dp, shape = CircleShape)
                .clip(shape = CircleShape)
                .width(120.dp)
                .height(120.dp)
                .align(Alignment.TopCenter)
                .border(1.dp, Color.LightGray, CircleShape),
            contentScale = ContentScale.Crop,
            placeholderRes = R.drawable.ic_pofile_deafult
        )

    }
}

@Preview(apiLevel = 34, showBackground = true, name = "Empty View", device = Devices.PIXEL)
@Composable
fun MyAccountScreenPreview() {
    MyAccountScreen(
        uiState = MyAccountScreenState(),
        modifier = Modifier,
        actions = {}
    )
}
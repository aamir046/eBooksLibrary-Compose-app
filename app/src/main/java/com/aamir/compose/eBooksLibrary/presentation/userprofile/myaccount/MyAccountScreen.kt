package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.app.permissions.AppPermission
import com.aamir.compose.eBooksLibrary.app.permissions.PermissionHandler
import com.aamir.compose.eBooksLibrary.core.presentation.LoadRemoteImage
import com.aamir.compose.eBooksLibrary.core.presentation.FormEntry
import com.aamir.compose.eBooksLibrary.core.presentation.LoadLocalUriImage
import com.aamir.compose.eBooksLibrary.core.presentation.extensions.showToastMessage
import com.aamir.compose.eBooksLibrary.presentation.theme.Gray
import com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount.components.MyAccountForm

@Composable
fun MyAccountScreenRoot(
    viewModel: MyAccountViewModel,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var isStoragePermission by remember { mutableStateOf(false) }

    val actions = remember {
        { action: MyAccountScreenActions ->
            when (action) {
                is MyAccountScreenActions.OnBackClick -> onBackClick()
                else -> viewModel.onAction(action)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is MyAccountScreenUiEvent.ShowMessage -> context.showToastMessage(event.message)
                is MyAccountScreenUiEvent.NavigateBack -> onBackClick()
            }
        }
    }

    if (!isStoragePermission) {
        PermissionHandler(
            appPermission = AppPermission.Storage,
            onGranted = { isStoragePermission = true },
            onDenied = {
                Log.e("MyAccountScreen", "Permission Denied")
            }
        )
    }

    MyAccountScreen(
        uiState = uiState,
        modifier = Modifier,
        actions = actions,
        isStoragePermission = isStoragePermission
    )
}

@Composable
fun MyAccountScreen(
    modifier: Modifier = Modifier,
    uiState: MyAccountScreenState = MyAccountScreenState(),
    isStoragePermission: Boolean = false,
    actions: (MyAccountScreenActions) -> Unit
) {
    val context = LocalContext.current

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { actions.invoke(MyAccountScreenActions.OnImagePicked(it, context)) }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Gray)
    ) {
        MyAccountForm(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .fillMaxHeight(0.845f)
                .align(Alignment.BottomCenter),
            name = uiState.userInfo.name,
            email = uiState.userInfo.email,
            phoneNumber = uiState.userInfo.phoneNumber,
            actions = actions
        )

        LoadLocalUriImage(
            url = uiState.userInfo.imageUrl,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .padding(top = 40.dp)
                .shadow(elevation = 8.dp, shape = CircleShape)
                .clip(shape = CircleShape)
                .clickable {
                    if (isStoragePermission) {
                        pickImageLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    } else {
                        //show permission required UI
                    }
                }
                .width(120.dp)
                .height(120.dp)
                .align(Alignment.TopCenter)
                .border(1.dp, Color.LightGray, CircleShape),
            contentScale = ContentScale.Crop,
            placeholderRes = R.drawable.ic_pofile_deafult
        )

    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun MyAccountScreenPreview() {
    MyAccountScreen(
        uiState = MyAccountScreenState(),
        modifier = Modifier,
        actions = {}
    )
}
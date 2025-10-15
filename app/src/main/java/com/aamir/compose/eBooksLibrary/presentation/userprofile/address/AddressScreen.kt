package com.aamir.compose.eBooksLibrary.presentation.userprofile.address

import android.location.Geocoder
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.app.permissions.AppPermission
import com.aamir.compose.eBooksLibrary.app.permissions.PermissionHandler
import com.aamir.compose.eBooksLibrary.presentation.userprofile.address.components.AddNewAddressButton
import com.aamir.compose.eBooksLibrary.presentation.userprofile.address.components.AddressDetailsBottomSheet
import com.aamir.compose.eBooksLibrary.presentation.userprofile.address.components.MapViewWithLocation
import com.aamir.compose.eBooksLibrary.presentation.userprofile.address.components.SavedAddresses
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun AddressScreenRoot(
    viewModel: AddressViewModel,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val goeCoder = remember { Geocoder(context, Locale.getDefault()) }
    var hasPermission by remember { mutableStateOf(false) }

    val actions: (AddressAction) -> Unit = remember {
        { action ->
            viewModel.onAction(action)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setGeocoder(goeCoder)
    }

    if (!hasPermission)
        PermissionHandler(
            appPermission = AppPermission.Location,
            onGranted = { hasPermission = true },
            onDenied = {
                // Show a dialog or fallback UI
            }
        )
    else
        AddressScreen(
            uiState = uiState,
            modifier = Modifier,
            actions = actions
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    modifier: Modifier,
    uiState: AddressScreenState,
    actions: (AddressAction) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = bottomSheetState,
            containerColor = Color.White,
        ) {
            AddressDetailsBottomSheet(
                address = uiState.bottomSheetAddress,
                onSaveAddress = { address ->
                    actions.invoke(AddressAction.OnSaveAddress(address))
                    scope.launch { bottomSheetState.hide() }
                    showSheet = false
                }
            )
        }
    }

    Box(
        modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        Column(modifier.fillMaxSize()) {
            MapViewWithLocation(
                modifier = modifier.height(200.dp),
                onLocationSelected = { geoPoint ->
                    actions(AddressAction.OnMapLocationSelected(geoPoint))
                    showSheet = true
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SavedAddresses(
                modifier = modifier.weight(1f),
                savedAddresses = uiState.savedAddresses,
            )
        }
        AddNewAddressButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onButtonClick = {
                showSheet = true
            }
        )
    }
}

    @Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
    @Composable
    fun AddressScreenPreview() {
        AddressScreen(
            uiState = AddressScreenState(),
            modifier = Modifier,
            actions = {}
        )
    }

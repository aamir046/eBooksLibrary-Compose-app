package com.aamir.compose.eBooksLibrary.presentation.userprofile.address

import AddressModel
import android.location.Geocoder
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.app.permissions.AppPermission
import com.aamir.compose.eBooksLibrary.app.permissions.PermissionHandler
import com.aamir.compose.eBooksLibrary.presentation.userprofile.address.components.AddressDetailsBottomSheet
import com.aamir.compose.eBooksLibrary.presentation.userprofile.address.components.MapViewWithLocation
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

    if (!hasPermission) {
        PermissionHandler(
            appPermission = AppPermission.Location,
            onGranted = { hasPermission = true },
            onDenied = {
                // Show a dialog or fallback UI
            }
        )
    }

    if (hasPermission) {
        AddressScreen(
            uiState = uiState,
            modifier = Modifier,
            actions = actions
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    uiState : AddressScreenState ,
    modifier : Modifier,
    actions: (AddressAction) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }
    var editableAddress by remember { mutableStateOf<AddressModel?>(null) }

    LaunchedEffect(uiState.selectedAddress) {
        uiState.selectedAddress?.let { address ->
            editableAddress = address
            showSheet = true
        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = bottomSheetState,
            containerColor = Color.White,
        ) {
            AddressDetailsBottomSheet(
                address = editableAddress?: AddressModel(),
                onConfirm = { addressModel ->
                    actions.invoke(AddressAction.UpdateAddress(addressModel))
                    scope.launch { bottomSheetState.hide() }
                    showSheet = false
                }
            )
        }
    }

    Column {
        MapViewWithLocation(
            modifier = modifier
                .height(200.dp)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            onLocationSelected = { geoPoint ->
                actions(AddressAction.LocationSelected(geoPoint))
            }
        )

        Card(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Address Details", style = MaterialTheme.typography.titleMedium)
                    IconButton(onClick = {
                        editableAddress = AddressModel()
                        showSheet = true
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Address Manually")
                    }
                }
                uiState.currentAddress?.let {
                    Text(it.title, fontWeight = FontWeight.Bold)
                    Text(it.fullAddress)
                } ?: Text("Long Press on map to add address.")
            }
        }
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

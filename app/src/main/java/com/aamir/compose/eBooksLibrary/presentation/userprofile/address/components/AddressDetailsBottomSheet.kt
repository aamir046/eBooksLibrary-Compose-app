package com.aamir.compose.eBooksLibrary.presentation.userprofile.address.components

import AddressModel
import AddressTag
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.core.presentation.FormEntry
import com.aamir.compose.eBooksLibrary.presentation.userprofile.address.AddressScreen
import com.aamir.compose.eBooksLibrary.presentation.userprofile.address.AddressScreenState
import com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount.MyAccountScreenActions

@Composable
fun AddressDetailsBottomSheet(
    address: AddressModel,
    onConfirm: (AddressModel) -> Unit
) {
    var title by remember { mutableStateOf(address.title) }
    var fullAddress by remember { mutableStateOf(address.fullAddress) }
    var selectedTag by remember { mutableStateOf(address.tag) }

    Column(
        Modifier.padding(16.dp)
    ) {
        FormEntry(
            modifier = Modifier,
            title = "Address Title",
            label = "Enter Title",
            value = title,
            onValueChange = {
                title = it
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        FormEntry(
            modifier = Modifier,
            title = "Full Address",
            label = "Enter Full Address",
            value = fullAddress,
            onValueChange = {
                fullAddress = it
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        ) {
            listOf(AddressTag.Home, AddressTag.Office, AddressTag.None).forEach { tag ->
                Button(
                    onClick = { selectedTag = tag },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTag == tag) Color.Black else Color.Gray
                    )
                ) {
                    Text(text = tag.name, color = Color.White)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                onConfirm(
                    address.copy(
                        title = title,
                        fullAddress = fullAddress,
                        tag = selectedTag
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.Unspecified,
                disabledContentColor = Color.Unspecified
            )
        ) {
            Text("Confirm")
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun AddressDetailsBottomSheetPreview() {
    AddressDetailsBottomSheet(
        address = AddressModel(),
        onConfirm = {},
    )
}

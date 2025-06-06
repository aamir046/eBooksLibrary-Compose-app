package com.aamir.compose.eBooksLibrary.presentation.userprofile.address

import AddressModel
import AddressTag
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp

@Composable
fun AddressDetailsBottomSheet(
    address: AddressModel,
    onConfirm: (AddressTag) -> Unit
) {
    var title by remember { mutableStateOf(address.title) }
    var selectedTag by remember { mutableStateOf(address.tag) }

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = address.fullAddress, style = MaterialTheme.typography.bodyMedium)

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            listOf(AddressTag.Home, AddressTag.Office, AddressTag.None).forEach { tag ->
                Button(
                    onClick = { selectedTag = tag },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTag == tag) MaterialTheme.colorScheme.primary else Color.LightGray
                    )
                ) {
                    Text(tag.name)
                }
            }
        }

        Button(
            onClick = { onConfirm(selectedTag) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirm")
        }
    }
}

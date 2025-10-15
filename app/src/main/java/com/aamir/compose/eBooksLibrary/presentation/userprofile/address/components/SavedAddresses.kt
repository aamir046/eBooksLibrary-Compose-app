package com.aamir.compose.eBooksLibrary.presentation.userprofile.address.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.core.presentation.EmptyListComposable
import com.aamir.compose.eBooksLibrary.domain.model.Address

@Composable
fun SavedAddresses(
    modifier: Modifier = Modifier,
    savedAddresses: List<Address>,

) {
    Column(modifier = modifier) {
        Text("Saved Addresses", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.size(16.dp))
        if (savedAddresses.isEmpty()) {
            EmptyListComposable(
                modifier = Modifier.padding(bottom = 60.dp),
                painterResource = painterResource(R.drawable.ic_empty_address),
                title = "No Address Found",
                subTitle = "Long Press on map or Click Quick Add"
            )
        } else {
            LazyColumn {
                items(savedAddresses) { address ->
                    ItemAddressList(
                        modifier = Modifier,
                        title = address.title,
                        fullAddress = address.fullAddress
                    )
                }
            }
        }
    }
}
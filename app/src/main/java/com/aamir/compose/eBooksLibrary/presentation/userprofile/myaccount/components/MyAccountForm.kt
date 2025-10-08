package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.core.presentation.FormEntry
import com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount.MyAccountScreenActions

@Composable
fun MyAccountForm(
    modifier: Modifier = Modifier,
    name: String = "",
    email: String = "",
    phoneNumber: String = "",
    actions: (MyAccountScreenActions) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.fillMaxHeight(0.14f))
        FormEntry(
            modifier = Modifier.padding(horizontal = 16.dp,vertical = 8.dp),
            title = "Name",
            label = "Enter name",
            value = name,
            onValueChange = {
                actions.invoke(MyAccountScreenActions.OnNameChange(it))
            }
        )
        FormEntry(
            modifier = Modifier.padding(horizontal = 16.dp,vertical = 8.dp),
            title = "Email",
            label = "Enter email",
            value = email,
            onValueChange = {
                actions.invoke(MyAccountScreenActions.OnEmailChange(it))
            }
        )
        FormEntry(
            modifier = Modifier.padding(horizontal = 16.dp,vertical = 8.dp),
            title = "Phone Number",
            label = "Enter Phone Number",
            value = phoneNumber,
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
                actions.invoke(MyAccountScreenActions.UpdateUserInfo)
            }
        ) {
            Text(text = "Save Changes")
        }
    }
}
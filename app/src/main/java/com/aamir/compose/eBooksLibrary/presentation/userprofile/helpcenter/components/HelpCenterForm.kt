package com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter.components

import androidx.compose.foundation.layout.Column
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
import com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter.HelpCenterScreenActions

@Composable
fun HelpCenterForm(
    modifier: Modifier = Modifier,
    email: String = "",
    subject: String = "",
    description: String = "",
    actions: (HelpCenterScreenActions) -> Unit ={}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormEntry(
            modifier = modifier.padding(horizontal = 16.dp,vertical = 8.dp),
            title = "Email",
            label = "Enter your email",
            value = email,
            onValueChange = {
                actions.invoke(HelpCenterScreenActions.OnEmailChange(it))
            }
        )
        FormEntry(
            modifier = modifier.padding(horizontal = 16.dp,vertical = 8.dp),
            title = "Subject",
            label = "Enter email subject",
            value = subject,
            onValueChange = {
                actions.invoke(HelpCenterScreenActions.OnSubjectChange(it))
            }
        )
        FormEntry(
            modifier = modifier.padding(horizontal = 16.dp,vertical = 8.dp),
            title = "Description",
            label = "Enter complete description",
            value = description,
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
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.White),
            enabled = email.isNotBlank() && subject.isNotBlank() && description.isNotBlank(),
            onClick = {
                actions.invoke(HelpCenterScreenActions.OnShareFeedback)
            }
        ) {
            Text(text = "Share Feedback")
        }
    }

}
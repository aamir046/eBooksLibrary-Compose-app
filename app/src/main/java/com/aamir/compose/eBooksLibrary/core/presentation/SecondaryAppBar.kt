package com.aamir.compose.eBooksLibrary.core.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondaryAppBar(
    modifier: Modifier = Modifier,
    title:String = "No Title",
    onBackClick: () -> Unit = {},
    isShowBackIcon:Boolean = true
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold
            )
        },
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {
            isShowBackIcon.takeIf { it }?.also {
                IconButton(
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp),
                    onClick = { onBackClick.invoke() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "Notifications"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        )
    )

}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun SecondaryAppBarPreview() {
    SecondaryAppBar()
}
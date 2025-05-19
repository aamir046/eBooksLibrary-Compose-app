package com.aamir.compose.eBooksLibrary.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
fun MainAppBar(
    title:String = "Home",
    onNotificationsClick: () -> Unit = {},
    onSearchClick: () -> Unit = {}
){
    CenterAlignedTopAppBar(
        title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        navigationIcon ={
            IconButton(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp),
                onClick = { onSearchClick.invoke() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search"
                )
            }
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp),
                onClick = { onNotificationsClick.invoke() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications),
                    contentDescription = "Notifications"
                )
            }
        }
    )
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun MainAppBarPreview(){
    Scaffold(){paddingvalues->
        Box(modifier = Modifier
            .padding(paddingvalues)
            .fillMaxSize()
            .background(Color.White)){
            MainAppBar()
        }
    }
}
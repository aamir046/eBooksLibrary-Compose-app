package com.aamir.compose.eBooksLibrary.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    onNotificationsClick: () -> Unit = {},
    onSearchClick: () -> Unit = {}
){
    TopAppBar(
        title = {
            Text(
                text = "Home",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        actions = {
                IconButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(40.dp)
                        .width(40.dp),
                    onClick = { onSearchClick.invoke() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search"
                    )
                }

                IconButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
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
fun HomeAppBarPreview(){
    Scaffold(){paddingvalues->
        Box(modifier = Modifier
            .padding(paddingvalues)
            .fillMaxSize()
            .background(Color.White)){
            HomeAppBar()
        }
    }
}
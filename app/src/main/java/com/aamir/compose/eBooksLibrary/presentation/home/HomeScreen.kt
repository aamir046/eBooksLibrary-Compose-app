package com.aamir.compose.eBooksLibrary.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aamir.compose.eBooksLibrary.presentation.home.components.HomeAppBar

@Composable
fun HomeScreenRoot() {
    HomeScreen()
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            HomeAppBar()
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Box(modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White)) {

            Card(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Box(modifier = modifier.fillMaxSize().background(Color.Gray)){
                    Text(modifier = Modifier.align(Alignment.Center), text = "Welcome Home", style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        color = Color.White)
                }
            }
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, name = "Empty View", device = Devices.PIXEL)
@Composable
fun GreetingPreview() {
    HomeScreen()
}
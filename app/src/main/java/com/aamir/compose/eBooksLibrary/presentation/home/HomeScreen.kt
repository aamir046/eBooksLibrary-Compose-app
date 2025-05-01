package com.aamir.compose.eBooksLibrary.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.domain.Book
import com.aamir.compose.eBooksLibrary.presentation.home.components.HomeAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.books.collectAsStateWithLifecycle()
    HomeScreen()
}


@Composable
fun HomeScreen(
    state: List<Book> = emptyList(),
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

            Column {
                Card(
                    modifier = modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Box(modifier = modifier.fillMaxSize().background(Color.Gray)){
                        Text(modifier = modifier.align(Alignment.Center), text = "Welcome Home", style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                            color = Color.White)
                    }
                }

                LazyColumn {
                    items(state.size) { index->
                        Text(state[index].title, modifier = Modifier.padding(10.dp))
                    }
                }
            }

        }
    }
}

@Preview(apiLevel = 34, showBackground = true, name = "Empty View", device = Devices.PIXEL)
@Composable
fun GreetingPreview() {
    HomeScreen(
     state =listOf(Book("J.R.R. Tolkien", "AlgoLogics", "2022", "An Amazing Book for Clean Code Programming"))
    )
}
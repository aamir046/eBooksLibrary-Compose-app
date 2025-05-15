package com.aamir.compose.eBooksLibrary.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aamir.compose.eBooksLibrary.presentation.main.components.MainComposable
import com.aamir.compose.eBooksLibrary.presentation.theme.EBooksLibraryComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EBooksLibraryComposeTheme {
                MainComposable()
            }
        }
    }
}
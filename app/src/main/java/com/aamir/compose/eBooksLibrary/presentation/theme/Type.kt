package com.aamir.compose.eBooksLibrary.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aamir.compose.eBooksLibrary.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.open_sans_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.open_sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    titleMedium =TextStyle(
        fontFamily = FontFamily(Font(R.font.open_sans_semibold)),
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    titleSmall =TextStyle(
        fontFamily = FontFamily(Font(R.font.open_sans_semibold)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.open_sans_light)),
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.5.sp
    ),

    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.open_sans_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp
    ),

    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.open_sans_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),

)
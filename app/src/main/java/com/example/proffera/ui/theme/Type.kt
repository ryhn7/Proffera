package com.example.proffera.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.proffera.R

// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.n_sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        color = Dark
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.n_sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
        color = Dark
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.n_sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp,
        color = Dark
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.n_sans_sb)),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp,
        color = Dark
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.n_sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
        color = Dark
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.n_sans_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        color = Dark
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.n_sans_light)),
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        color = Dark
    ),
)
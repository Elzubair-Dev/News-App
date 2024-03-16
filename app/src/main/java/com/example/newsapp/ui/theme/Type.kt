package com.example.newsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.newsapp.R

private val playfair = FontFamily(
    fonts = listOf(
        Font(R.font.playfair_display_extra_bold, weight = FontWeight.Bold, style = FontStyle.Normal),
        Font(R.font.playfair_display_extra_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
        Font(R.font.playfair_display_bold, weight = FontWeight.W600, style = FontStyle.Normal),
        Font(R.font.playfair_display_bold_italic, weight = FontWeight.W600, style = FontStyle.Italic),
        Font(R.font.playfair_display_medium, weight = FontWeight.W500, style = FontStyle.Normal),
        Font(R.font.playfair_display_medium_italic, weight = FontWeight.W500, style = FontStyle.Italic),
        Font(R.font.playfair_display_regular, weight = FontWeight.W300, style = FontStyle.Normal),
        Font(R.font.playfair_display_italic, weight = FontWeight.W300, style = FontStyle.Italic),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = playfair,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 18.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = playfair,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = playfair,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 10.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = playfair,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 8.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
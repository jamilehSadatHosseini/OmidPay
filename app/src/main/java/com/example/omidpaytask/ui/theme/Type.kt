package com.example.omidpaytask.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.omidpaytask.R

val Inter = FontFamily(
    fonts = listOf(
        Font(R.font.inter_regular, FontWeight.Normal),
        Font(R.font.inter_bold, FontWeight.Bold),
        Font(R.font.inter_semibold, FontWeight.SemiBold),
    )
)


val Typography = Typography(
    displaySmall = TextStyle(
        fontSize = 24.sp,
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp,
    ),
    displayMedium = TextStyle(
        fontSize = 32.sp,
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        lineHeight = 48.sp,
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    labelSmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 17.sp,
    ),
    headlineMedium = TextStyle(
        fontSize = 24.sp,
        fontFamily = Inter,
        lineHeight = 29.sp,
        fontWeight = FontWeight.SemiBold,
        color = Red,
    ),
)
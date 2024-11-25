package com.example.crapsgame.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.createFontFamilyResolver
import com.example.crapsgame.R

//initalize cutom fonts
val LifeSaver = FontFamily(
    Font(R.font.lifesavers_regular),
    Font(R.font.lifesavers_bold, FontWeight.Bold),
    Font(R.font.lifesavers_extrabold, FontWeight.ExtraBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    displayLarge = TextStyle(
        fontFamily = LifeSaver,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
    ),

    displayMedium = TextStyle(
    fontFamily = LifeSaver,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 25.sp,
),
    displaySmall = TextStyle(
        fontFamily = LifeSaver,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),

    labelSmall = TextStyle(
        fontFamily = LifeSaver,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
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
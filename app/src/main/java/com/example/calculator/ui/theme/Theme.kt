package com.example.calculator.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColorScheme(
    background = OuterSpace300,
    onBackground = OuterSpace200,
)

@Composable
fun CalculatorTheme(content: @Composable () -> Unit) {
    val colors = DarkColorPalette
    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(color = colors.background)
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
package com.jeckonly.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext



@Composable
fun BudgetTheme(
    colorScheme: BudgetColorTheme,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    wantDynamic: Boolean = true,
    content: @Composable () -> Unit
) {
    val dynamicColor = wantDynamic && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        dynamicColor && useDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !useDarkTheme -> dynamicLightColorScheme(LocalContext.current)
        useDarkTheme -> colorScheme.darkScheme
        else -> colorScheme.lightScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = budgetTypography,
        shapes = shapes,
        content = content
    )
}
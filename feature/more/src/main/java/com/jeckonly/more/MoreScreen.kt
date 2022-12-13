package com.jeckonly.more

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jeckonly.designsystem.Mdf

@Composable
fun MoreRoute() {
    MoreScreen()
}

@Composable
fun MoreScreen() {
    Surface(color = Color.Gray, modifier = Mdf.fillMaxSize()) {
        
    }
}
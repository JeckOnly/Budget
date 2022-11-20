package com.jeckonly.choosetype

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChooseTypeRoute() {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val systemUiColor = MaterialTheme.colorScheme.secondaryContainer

    DisposableEffect(key1 = systemUiController) {
        systemUiController.setStatusBarColor(
            color = systemUiColor,
            darkIcons = true
        )
        onDispose {

        }
    }

    ChooseTypeScreen()
}

@Composable
fun ChooseTypeScreen() {

}
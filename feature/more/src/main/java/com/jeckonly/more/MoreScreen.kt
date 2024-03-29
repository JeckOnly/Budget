package com.jeckonly.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.more.ui.LangButton
import com.jeckonly.more.ui.ThemeButton

@Composable
fun MoreRoute(
    toChangeThemeScreen: () -> Unit,
    toChangeLangScreen: () -> Unit
) {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val systemUiColor = MaterialTheme.colorScheme.primary

    DisposableEffect(key1 = systemUiColor) {
        systemUiController.setStatusBarColor(
            color = systemUiColor,
            darkIcons = true
        )
        onDispose {

        }
    }
    MoreScreen(
        toChangeThemeScreen = toChangeThemeScreen,
        toChangeLangScreen = toChangeLangScreen,
        modifier = Mdf.fillMaxSize()
    )
}

@Composable
fun MoreScreen(
    toChangeThemeScreen: () -> Unit,
    toChangeLangScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(color = MaterialTheme.colorScheme.surface, modifier = modifier) {
        Column(modifier = Mdf.fillMaxSize()) {
            MoreScreenHeader()
            Row(verticalAlignment = Alignment.CenterVertically){
                ThemeButton(
                    toChangeThemeScreen = toChangeThemeScreen,
                    modifier = Mdf.padding(20.dp)
                )
                LangButton(toChangeLangScreen = toChangeLangScreen)
            }
        }
    }
}

@Composable
private fun MoreScreenHeader() {
    Box(
        modifier = Mdf
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Mdf.padding(10.dp)) {
            Text(
                text = stringResource(id = R.string.more),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 19.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Mdf.height(10.dp))
            Text(
                text = stringResource(id = R.string.more_hint),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
            )
        }
    }
}
package com.jeckonly.changelang

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R

@Composable
fun ChangeLangRoute(
    modifier: Modifier = Modifier,
) {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val systemUiColor = MaterialTheme.colorScheme.primary
    val viewModel: ChangeLangViewModel = hiltViewModel()

    DisposableEffect(key1 = systemUiColor) {
        systemUiController.setStatusBarColor(
            color = systemUiColor,
            darkIcons = true
        )
        onDispose {

        }
    }

    ChangeLangScreen(
        modifier = Mdf.fillMaxSize()
    )
}

@Composable
fun ChangeLangScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Surface(color = MaterialTheme.colorScheme.surface, modifier = modifier) {
        Column {
            ChangeLangHeader()
        }
    }
}

@Composable
private fun ChangeLangHeader() {
    Box(
        modifier = Mdf
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Mdf.padding(10.dp)) {
            Text(
                text = stringResource(id = R.string.change_lang),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 19.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Mdf.height(10.dp))
            Text(
                text = stringResource(id = R.string.change_lang_hint),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
            )
        }
    }
}



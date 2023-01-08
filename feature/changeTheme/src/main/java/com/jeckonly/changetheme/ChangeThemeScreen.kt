package com.jeckonly.changetheme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.changetheme.ui.ThemeItem
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.theme.BudgetColorTheme

@Composable
fun ChangeThemeRoute(
    modifier: Modifier = Modifier,
) {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val systemUiColor = MaterialTheme.colorScheme.primary
    val viewModel: ChangeThemeViewModel = hiltViewModel()

    DisposableEffect(key1 = systemUiColor) {
        systemUiController.setStatusBarColor(
            color = systemUiColor,
            darkIcons = true
        )
        onDispose {

        }
    }

    ChangeThemeScreen(
        selectedNumber = viewModel.themeNumberFlow.collectAsState().value,
        onClickItem = viewModel::updateTheme,
        modifier = Mdf.fillMaxSize()
    )
}

@Composable
fun ChangeThemeScreen(
    selectedNumber: Int,
    onClickItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(color = MaterialTheme.colorScheme.surface, modifier = modifier) {
        Column {
            ChangeThemeHeader()
            LazyColumn(modifier = Mdf
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 15.dp), content = {
                items(items = BudgetColorTheme.themeItemList, key = { it.number }) {
                    ThemeItem(
                        selectedNumber = selectedNumber,
                        number = it.number,
                        text = it.colorDescribedText,
                        color = it.color,
                        onClick = onClickItem,
                        modifier = Mdf.fillMaxWidth()
                    )
                }
            })
        }
    }
}

@Composable
private fun ChangeThemeHeader() {
    Box(
        modifier = Mdf
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Mdf.padding(10.dp)) {
            Text(
                text = stringResource(id = R.string.theme_setting),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 19.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Mdf.height(10.dp))
            Text(
                text = stringResource(id = R.string.theme_setting_hint),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
            )
        }
    }
}


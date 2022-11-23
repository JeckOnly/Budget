package com.jeckonly.choosetype

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.choosetype.ui.TabHeader
import com.jeckonly.choosetype.ui.TypePager
import com.jeckonly.choosetype.ui.state.ChooseTypeUiState


private const val HEADER_HEIGHT = 55

@Composable
fun ChooseTypeRoute(
    modifier: Modifier = Modifier,
    viewModel: ChooseTypeViewModel = hiltViewModel()
) {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val systemUiColor = MaterialTheme.colorScheme.secondaryContainer
    val chooseTypeUiState = viewModel.chooseTypeUiStateFlow.collectAsState()

    DisposableEffect(key1 = systemUiController) {
        systemUiController.setStatusBarColor(
            color = systemUiColor,
            darkIcons = true
        )
        onDispose {

        }
    }

    ChooseTypeScreen(
        chooseTypeUiState = chooseTypeUiState.value,
        modifier = modifier
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChooseTypeScreen(
    chooseTypeUiState: ChooseTypeUiState,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(0)

    Column(modifier = modifier.fillMaxSize()) {
        TabHeader(
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(HEADER_HEIGHT.dp)
        )
        TypePager(
            loading = chooseTypeUiState.isLoading,
            pagerState = pagerState,
            modifier = Modifier.fillMaxSize()
        )
    }
}
package com.jeckonly.recorddetail

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.recorddetail.ui.state.RecordDetailUIState


private const val HEADER_HEIGHT = 55

@Composable
fun RecordDetailRoute(
    recordId: Int,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecordDetailViewModel = hiltViewModel()
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

    LaunchedEffect(key1 = Unit, block = {
        viewModel.initViewModel(recordId)
    })

    val recordDetailUIState = viewModel.recordDetailUIStateFlow.collectAsState()

    RecordDetailScreen(
        recordDetailUIState.value,
        modifier = modifier
    )
}

@Composable
fun RecordDetailScreen(
    recordDetailUIState: RecordDetailUIState,
    modifier: Modifier = Modifier
) {

}
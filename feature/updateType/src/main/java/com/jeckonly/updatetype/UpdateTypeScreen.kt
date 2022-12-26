package com.jeckonly.updatetype

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.core_model.ui.TypeUI
import com.jeckonly.designsystem.composable.pager.TabHeader
import com.jeckonly.updatetype.ui.UpdateTypePager
import com.jeckonly.updatetype.ui.state.UpdateTypeUiState


private const val HEADER_HEIGHT = 55

@Composable
fun UpdateTypeRoute(
    modifier: Modifier = Modifier,
    viewModel: UpdateTypeViewModel = hiltViewModel()
) {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val systemUiColor = MaterialTheme.colorScheme.secondaryContainer
    val updateTypeUiState = viewModel.updateTypeUiStateFlow.collectAsState()

    DisposableEffect(key1 = systemUiController) {
        systemUiController.setStatusBarColor(
            color = systemUiColor,
            darkIcons = true
        )
        onDispose {

        }
    }

    UpdateTypeScreen(
        updateTypeUiState = updateTypeUiState.value,
        onDragEnd = viewModel::onDragEnd,
        onClickDelete = viewModel::onClickDelete,
        modifier = modifier
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UpdateTypeScreen(
    updateTypeUiState: UpdateTypeUiState,
    onDragEnd: ((newList:List<TypeUI>, startIndex: Int, endIndex: Int) -> (Unit)),
    onClickDelete: (Int, Boolean, (Int) -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(0)
    // 表示当前选择的类型
    Column(modifier = modifier.fillMaxSize()) {
        TabHeader(
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(HEADER_HEIGHT.dp)
        )
        UpdateTypePager(
            updateTypeUiState = updateTypeUiState,
            pagerState = pagerState,
            onDragEnd = onDragEnd,
            onClickDelete = onClickDelete,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}
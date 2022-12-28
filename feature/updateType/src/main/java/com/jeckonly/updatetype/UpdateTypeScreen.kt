package com.jeckonly.updatetype

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.core_model.ui.TypeUI
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.composable.pager.TabHeader
import com.jeckonly.designsystem.noIndicationClickable
import com.jeckonly.updatetype.ui.UpdateTypePager
import com.jeckonly.updatetype.ui.state.UpdateTypeUiState


private const val HEADER_HEIGHT = 55

@Composable
fun UpdateTypeRoute(
    goToAddOrEdit: (Int) -> Unit,
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
        goToAddOrEdit = goToAddOrEdit,
        modifier = modifier
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UpdateTypeScreen(
    updateTypeUiState: UpdateTypeUiState,
    onDragEnd: ((newList: List<TypeUI>, startIndex: Int, endIndex: Int) -> (Unit)),
    goToAddOrEdit: (Int) -> Unit,
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
            goToAddOrEdit = goToAddOrEdit,
            onClickDelete = onClickDelete,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Surface(
            modifier = Mdf
                .height(55.dp)
                .fillMaxWidth()
                .noIndicationClickable {
                    // 传-1表示是添加类型
                    goToAddOrEdit(-1)
                }
            , color = MaterialTheme.colorScheme.surface, shadowElevation = 4.dp
        ) {
            Box(modifier = Mdf.fillMaxSize(), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = null,
                        modifier = Mdf.size(25.dp).padding(end = 15.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.add_type),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
package com.jeckonly.choosetype

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.choosetype.ui.ChooseTypeKeyBoard
import com.jeckonly.choosetype.ui.ChooseTypePager
import com.jeckonly.choosetype.ui.state.ChooseTypeUiState
import com.jeckonly.choosetype.ui.state.KeyboardState
import com.jeckonly.designsystem.composable.pager.TabHeader
import timber.log.Timber


private const val HEADER_HEIGHT = 55

@Composable
fun ChooseTypeRoute(
    isEditOrRecordId: Int,
    onCLickSetting: () -> Unit,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChooseTypeViewModel = hiltViewModel()
) {
    Timber.d(isEditOrRecordId.toString())

    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val systemUiColor = MaterialTheme.colorScheme.secondaryContainer
    val chooseTypeUiState = viewModel.chooseTypeUiStateFlow.collectAsState()

    DisposableEffect(key1 = systemUiColor) {
        systemUiController.setStatusBarColor(
            color = systemUiColor,
            darkIcons = true
        )
        onDispose {

        }
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.initEditOrRecordId(editOrRecordId = isEditOrRecordId)
    })

    ChooseTypeScreen(
        chooseTypeUiState = chooseTypeUiState.value,
        keyboardState = viewModel.keyboardState,
        onCLickSetting = onCLickSetting,
        popBackStack = popBackStack,
        modifier = modifier
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChooseTypeScreen(
    chooseTypeUiState: ChooseTypeUiState,
    keyboardState: KeyboardState,
    onCLickSetting: () -> Unit,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(0)
    val nowChooseTypeState = chooseTypeUiState.nowChooseType
    Column(modifier = modifier.fillMaxSize()) {
        TabHeader(
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(HEADER_HEIGHT.dp)
        )
        ChooseTypePager(
            chooseTypeUiState = chooseTypeUiState,
            pagerState = pagerState,
            nowTypeUI = nowChooseTypeState.value,
            onClick = { chooseTypeTypeUI ->
                // 点击类型item的回调
                nowChooseTypeState.value = chooseTypeTypeUI
            },
            onCLickSetting = onCLickSetting,
            modifier = Modifier
                .fillMaxWidth()
                .weight(if (nowChooseTypeState.value == null) 1f else 0.5f)
        )
        AnimatedVisibility(
            visible = nowChooseTypeState.value != null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
            ,
            enter = slideIn(animationSpec = TweenSpec(durationMillis = 50)) { fullSize: IntSize ->
                IntOffset(0, fullSize.height)
            },
            exit = slideOut(animationSpec = TweenSpec(durationMillis = 50)) { fullSize: IntSize ->
                IntOffset(0, fullSize.height)
            }
        ) {
            ChooseTypeKeyBoard(
                nowChooseType = nowChooseTypeState.value,
                keyboardState = keyboardState,
                onCLickDown = {
                    nowChooseTypeState.value = null
                },
                popBackStack = popBackStack,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
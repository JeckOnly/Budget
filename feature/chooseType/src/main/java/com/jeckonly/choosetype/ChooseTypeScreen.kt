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
import com.jeckonly.core_model.ui.TypeUI
import com.jeckonly.designsystem.composable.pager.TabHeader


private const val HEADER_HEIGHT = 55

@Composable
fun ChooseTypeRoute(
    onCLickSetting: () -> Unit,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChooseTypeViewModel = hiltViewModel()
) {
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
    // 表示当前选择的类型
    var nowChooseType: TypeUI? by remember {
        mutableStateOf(null)
    }
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
            nowTypeUI = nowChooseType,
            onClick = { chooseTypeTypeUI ->
                // 点击类型item的回调
                nowChooseType = chooseTypeTypeUI
            },
            onCLickSetting = onCLickSetting,
            modifier = Modifier
                .fillMaxWidth()
                .weight(if (nowChooseType == null) 1f else 0.5f)
        )
        AnimatedVisibility(
            visible = nowChooseType != null,
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
                nowChooseType = nowChooseType,
                keyboardState = keyboardState,
                onCLickDown = {
                    nowChooseType = null
                    keyboardState.cleanState()
                },
                popBackStack = popBackStack,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
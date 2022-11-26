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
import com.jeckonly.choosetype.ui.TabHeader
import com.jeckonly.choosetype.ui.TypePager
import com.jeckonly.choosetype.ui.state.ChooseTypeUiState
import com.jeckonly.choosetype.ui.state.KeyboardState
import com.jeckonly.core_model.ui.ChooseTypeTypeUI


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
        keyboardState = viewModel.keyboardState,
        modifier = modifier
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChooseTypeScreen(
    chooseTypeUiState: ChooseTypeUiState,
    keyboardState: KeyboardState,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(0)
    // 表示当前选择的类型
    var nowChooseType: ChooseTypeTypeUI? by remember {
        mutableStateOf(null)
    }
    Column(modifier = modifier.fillMaxSize()) {
        TabHeader(
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(HEADER_HEIGHT.dp)
        )
        TypePager(
            chooseTypeUiState = chooseTypeUiState,
            pagerState = pagerState,
            nowChooseTypeTypeUI = nowChooseType,
            onClick = { chooseTypeTypeUI ->
                // 点击类型item的回调
                nowChooseType = chooseTypeTypeUI
            },
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
                keyboardState = keyboardState,
                onCLickDown = {
                    nowChooseType = null
                    keyboardState.cleanState()
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
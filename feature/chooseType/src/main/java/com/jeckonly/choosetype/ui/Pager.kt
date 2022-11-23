package com.jeckonly.choosetype.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.jeckonly.choosetype.ui.state.ChooseTypeUiState
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.ui.ChooseTypeTypeUI
import com.jeckonly.designsystem.Mdf

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TypePager(
    chooseTypeUiState: ChooseTypeUiState,
    pagerState: PagerState,
    nowChooseTypeTypeUI: ChooseTypeTypeUI?,
    onClick: (ChooseTypeTypeUI) -> Unit,
    modifier: Modifier = Modifier
) {
    HorizontalPager(count = 2, modifier = modifier, state = pagerState) { page ->
        if (chooseTypeUiState.isLoading) {
            Box(
                modifier = Mdf
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.tertiary)
            }
        } else {
            when (page) {
                0 -> {
                    TypePage(
                        expenseOrIncome = ExpenseOrIncome.Expense,
                        chooseTypeUiState = chooseTypeUiState,
                        nowChooseTypeTypeUI = nowChooseTypeTypeUI,
                        onClick = onClick,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                1 -> {
                    TypePage(
                        expenseOrIncome = ExpenseOrIncome.Income,
                        chooseTypeUiState = chooseTypeUiState,
                        nowChooseTypeTypeUI = nowChooseTypeTypeUI,
                        onClick = onClick,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun TypePage(
    expenseOrIncome: ExpenseOrIncome,
    chooseTypeUiState: ChooseTypeUiState,
    nowChooseTypeTypeUI: ChooseTypeTypeUI?,
    onClick: (ChooseTypeTypeUI) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyGridState = rememberLazyGridState()
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            state = lazyGridState,
            contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
            content = {
                // key默认为位置（position）
                items(
                    items = if (expenseOrIncome is ExpenseOrIncome.Expense) chooseTypeUiState.expenseTypeList else chooseTypeUiState.incomeTypeList,
                ) { item: ChooseTypeTypeUI ->
                    TypeItem(
                        typeUI = item,
                        nowChooseTypeTypeUI = nowChooseTypeTypeUI,
                        onClick = onClick,
                        modifier = Mdf.padding(horizontal = 7.dp, vertical = 10.dp)
                    )
                }
                // TODO 设置按钮
            })
    }
}

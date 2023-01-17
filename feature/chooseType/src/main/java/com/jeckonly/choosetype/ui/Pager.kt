package com.jeckonly.choosetype.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.jeckonly.choosetype.ui.state.ChooseTypeUiState
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.ui.TypeUI
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChooseTypePager(
    chooseTypeUiState: ChooseTypeUiState,
    pagerState: PagerState,
    nowTypeUI: TypeUI?,
    onClick: (TypeUI) -> Unit,
    onCLickSetting: () -> Unit,
    modifier: Modifier = Modifier
) {
    HorizontalPager(count = 2, modifier = modifier, state = pagerState) { page ->
        if (!chooseTypeUiState.isLoading) {
            when (page) {
                0 -> {
                    ChooseTypePage(
                        expenseOrIncome = ExpenseOrIncome.Expense,
                        chooseTypeUiState = chooseTypeUiState,
                        nowTypeUI = nowTypeUI,
                        onClick = onClick,
                        onCLickSetting = onCLickSetting,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                1 -> {
                    ChooseTypePage(
                        expenseOrIncome = ExpenseOrIncome.Income,
                        chooseTypeUiState = chooseTypeUiState,
                        nowTypeUI = nowTypeUI,
                        onClick = onClick,
                        onCLickSetting = onCLickSetting,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun ChooseTypePage(
    expenseOrIncome: ExpenseOrIncome,
    chooseTypeUiState: ChooseTypeUiState,
    nowTypeUI: TypeUI?,
    onClick: (TypeUI) -> Unit,
    onCLickSetting: () -> Unit,
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
                ) { item: TypeUI ->
                    ChooseTypeItem(
                        typeUI = item,
                        nowTypeUI = nowTypeUI,
                        onClick = onClick,
                        modifier = Mdf.padding(horizontal = 7.dp, vertical = 10.dp)
                    )
                }
                if (chooseTypeUiState.canEnterSetting.value) {
                    item {
                        UpdateTypeItem(
                            iconId = R.drawable.tallytype_set_stroke,
                            onClick = onCLickSetting,
                            modifier = Mdf.padding(horizontal = 7.dp, vertical = 10.dp)
                        )
                    }
                }
            }
        )
    }
}

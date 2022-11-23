package com.jeckonly.choosetype.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.designsystem.Mdf

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TypePager(loading: Boolean, pagerState: PagerState, modifier: Modifier = Modifier) {
    HorizontalPager(count = 2, modifier = modifier, state = pagerState) { page ->
        if (loading) {
            Box(modifier = Mdf.fillMaxSize().background(color = MaterialTheme.colorScheme.surface), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.tertiary)
            }
        } else {
            when (page) {
                0 -> {
                    TypePage(expenseOrIncome = ExpenseOrIncome.Expense, modifier = Modifier.fillMaxSize())
                }
                1 -> {
                    TypePage(expenseOrIncome = ExpenseOrIncome.Income, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun TypePage(expenseOrIncome: ExpenseOrIncome, modifier: Modifier = Modifier) {
    Surface(modifier = modifier, color = if(expenseOrIncome is ExpenseOrIncome.Expense) Color.Blue else Color.Red) {
        // TODO
    }
}

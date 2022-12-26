package com.jeckonly.designsystem.composable.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.PagerState
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabHeader(pagerState: PagerState, modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(color = MaterialTheme.colorScheme.secondaryContainer)) {
        ExpenseAndIncomeTab(
            pagerState = pagerState,
            modifier = Mdf
                .align(Alignment.BottomCenter)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ExpenseAndIncomeTab(pagerState: PagerState, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Tab(
            expenseOrIncome = ExpenseOrIncome.Expense,
            page = 0,
            currentPage = pagerState.currentPage,
            currentPageOffset = pagerState.currentPageOffset,
            modifier = Mdf.padding(end = 7.dp)
        )
        Tab(
            expenseOrIncome = ExpenseOrIncome.Income,
            page = 1,
            currentPage = pagerState.currentPage,
            currentPageOffset = pagerState.currentPageOffset,
            modifier = Mdf.padding(start = 7.dp)
        )
    }
}

@Composable
fun Tab(
    expenseOrIncome: ExpenseOrIncome,
    page: Int,
    currentPage: Int,
    currentPageOffset: Float,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(modifier = modifier.width(IntrinsicSize.Max)) {
        Text(
            text = if (expenseOrIncome is ExpenseOrIncome.Expense)
                context.getString(R.string.expense)
            else
                context.getString(R.string.income),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.titleMedium,
            modifier = Mdf.padding(start = 6.dp, end = 6.dp)
        )
        Spacer(modifier = Mdf.height(10.dp))
        Box(
            modifier = Mdf
                .height(2.dp)
                .fillMaxWidth()
                .scale(
                    scaleX = calculateScaleXForTabIndicatorByPage(
                        page, currentPage, currentPageOffset
                    ),
                    scaleY = 1f
                )
        ) {
            Box(
                modifier = Mdf
                    .background(
                        color = contentColorFor(
                            backgroundColor = MaterialTheme.colorScheme.onSecondaryContainer
                        ),
                        shape = RoundedCornerShape(30)
                    )
                    .align(Alignment.Center)
                    .fillMaxSize()
            )
        }
    }
}


/**
 * 参考[PagerScope.calculateCurrentOffsetForPage]方法。
 * 这个函数不具有拓展性，本质上：([currentPage] - [page]) + [currentPageOffset] 得出的是：
 * 对于给定的[page]，当前位置偏离它多少倍的[HorizontalPager]宽度。
 * 在本例中经过修改可以用来描绘下划线的宽度比值。
 */
fun calculateScaleXForTabIndicatorByPage(
    page: Int,
    currentPage: Int,
    currentPageOffset: Float
): Float {
    return 1f - (((currentPage - page) + currentPageOffset).absoluteValue)
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewTab() {
    Tab(
        expenseOrIncome = ExpenseOrIncome.Expense,
        page = 0,
        currentPage = 0,
        currentPageOffset = 0f
    )
}
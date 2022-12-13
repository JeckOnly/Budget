package com.jeckonly.chart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jeckonly.chart.ui.state.ChartScreenState
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.composable.header.HeaderItem
import com.jeckonly.designsystem.noIndicationClickable

@Composable
fun ChartHeader(
    chartScreenState: ChartScreenState,
    whenClickBack: () -> Unit,
    whenClickAhead: () -> Unit,
    updateExpenseOrIncome: (ExpenseOrIncome) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = MaterialTheme.colorScheme.surface
    val contentColor = contentColorFor(backgroundColor = backgroundColor)
    val selectedContentColor = MaterialTheme.colorScheme.primary
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier,
        tonalElevation = 1.dp
    ) {
        Column(
            modifier = Mdf
                .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Mdf.fillMaxWidth()) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = null,
                    modifier = Mdf
                        .rotate(90f)
                        .align(Alignment.CenterStart)
                        .noIndicationClickable(onClick = whenClickBack),
                    tint = contentColor
                )
                Text(
                    text = chartScreenState.chartHeaderUI.monthYearText,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Mdf.align(Alignment.Center),
                    color = contentColor
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = null,
                    modifier = Mdf
                        .rotate(-90f)
                        .align(Alignment.CenterEnd)
                        .noIndicationClickable(onClick = whenClickAhead),
                    tint = contentColor
                )
            }
            Spacer(modifier = Mdf.height(20.dp))
            Divider(
                modifier = Mdf.fillMaxWidth(0.9f),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Spacer(modifier = Mdf.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Mdf.fillMaxWidth(0.9f)
            ) {
                HeaderItem(
                    textColor = if (chartScreenState.expenseOrIncome == ExpenseOrIncome.Income) selectedContentColor else contentColor,
                    itemName = stringResource(id = R.string.income),
                    itemValue = chartScreenState.chartHeaderUI.totalIncome,
                    modifier = Mdf.noIndicationClickable {
                        updateExpenseOrIncome(ExpenseOrIncome.Income)
                    }
                )
                HeaderItem(
                    textColor = if (chartScreenState.expenseOrIncome == ExpenseOrIncome.Expense) selectedContentColor else contentColor,
                    itemName = stringResource(id = R.string.expense),
                    itemValue = chartScreenState.chartHeaderUI.totalExpense,
                    modifier = Mdf.noIndicationClickable {
                        updateExpenseOrIncome(ExpenseOrIncome.Expense)
                    }
                )
                HeaderItem(
                    textColor = contentColor,
                    itemName = stringResource(id = R.string.balance),
                    itemValue = chartScreenState.chartHeaderUI.totalBalance
                )
            }
        }
    }
}



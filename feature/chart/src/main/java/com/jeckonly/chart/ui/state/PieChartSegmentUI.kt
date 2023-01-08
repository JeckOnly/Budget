package com.jeckonly.chart.ui.state

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.designsystem.theme.color.*

@Stable
data class PieChartSegmentUI(
    val typeName: String,
    val color: Color,
    val startDegree: Double,
    val sweepDegree: Double
)

@Stable
fun orderToColor(order: Int, expenseOrIncome: ExpenseOrIncome): Color {
    return if (expenseOrIncome is ExpenseOrIncome.Expense) {
        when (order) {
            1 -> {
                percent1_expense
            }
            2 -> {
                percent2_expense
            }
            3 -> {
                percent3_expense
            }
            4 -> {
                percent4_expense
            }
            else -> {
                percent5_expense
            }
        }
    } else {
        when (order) {
            1 -> {
                percent1_income
            }
            2 -> {
                percent2_income
            }
            3 -> {
                percent3_income
            }
            4 -> {
                percent4_income
            }
            else -> {
                percent5_income
            }
        }
    }
}
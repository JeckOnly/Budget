package com.jeckonly.chart.ui.state

import androidx.compose.runtime.Stable
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome

@Stable
data class ChartScreenState(
    val expenseOrIncome: ExpenseOrIncome,
    val chartHeaderUI: ChartHeaderUI,
    val chartData: ChartData,
)

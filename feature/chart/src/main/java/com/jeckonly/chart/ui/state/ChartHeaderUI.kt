package com.jeckonly.chart.ui.state

import androidx.compose.runtime.Stable

@Stable
data class ChartHeaderUI(
    val monthYearText: String = "",
    val totalExpense: String = "",
    val totalIncome: String = "",
    val totalBalance: String = ""
)

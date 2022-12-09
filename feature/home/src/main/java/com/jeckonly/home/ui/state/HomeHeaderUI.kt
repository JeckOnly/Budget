package com.jeckonly.home.ui.state

import androidx.compose.runtime.Stable

@Stable
data class HomeHeaderUI(
    val monthYearText: String = "",
    val totalExpense: String = "",
    val totalIncome: String = "",
    val totalBalance: String = ""
)
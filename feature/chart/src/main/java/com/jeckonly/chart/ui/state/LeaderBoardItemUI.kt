package com.jeckonly.chart.ui.state

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome

@Stable
data class LeaderBoardItemUI(
    val typeName: String,
    val iconId: Int,
    /**
     * 0-1
     */
    val percent: Double,
    val number: Double,
    val color: Color,
    val expenseOrIncome: ExpenseOrIncome
)


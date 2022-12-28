package com.jeckonly.addtype.ui.state

import androidx.compose.runtime.Stable
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.designsystem.R

@Stable
data class AddTypeScreenState(
    val iconId: Int = R.drawable.cc_entertainmente_archery_stroke,
    val typeName: String = "",
    val expenseOrIncome: ExpenseOrIncome = ExpenseOrIncome.Expense
)

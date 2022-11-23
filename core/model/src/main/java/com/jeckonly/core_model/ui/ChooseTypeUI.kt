package com.jeckonly.core_model.ui

import com.jeckonly.core_model.entity.helper.ExpenseOrIncome

data class ChooseTypeTypeUI(
    val iconId: Int,
    val typeName: String,
    val order: Int,
    val expenseOrIncome: ExpenseOrIncome
)

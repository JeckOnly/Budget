package com.jeckonly.core_model.ui

import com.jeckonly.core_model.entity.helper.ExpenseOrIncome

data class HomeRecordItemUI(
    val year: Int,
    val month: Int,
    val dayOfMonth: Int,
    val number: Double,
    val expenseOrIncome: ExpenseOrIncome,
    val iconId: Int,
    val typeName: String,
    val remark: String,
) {
    val showText: String
        get() = if (remark == "") typeName else remark
}


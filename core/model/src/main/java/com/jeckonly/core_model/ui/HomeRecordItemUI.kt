package com.jeckonly.core_model.ui

import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.util.FormatNumberUtil

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
    val showText: String = if (remark == "") typeName else remark
    val showNumber: String = FormatNumberUtil.format(number)
}


package com.jeckonly.core_model.entity.update

import androidx.room.ColumnInfo
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome

data class TypeUpdateNoOrder(
    @ColumnInfo(name = "type_type_id") val typeId: Int,
    @ColumnInfo(name = "type_name") val name: String,
    @ColumnInfo(name = "icon_id") val iconId: Int,
    @ColumnInfo(name = "expense_or_income") val expenseOrIncome: ExpenseOrIncome,
)

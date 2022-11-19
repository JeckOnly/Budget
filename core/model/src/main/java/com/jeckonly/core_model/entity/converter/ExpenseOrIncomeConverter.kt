package com.jeckonly.core_model.entity.converter

import androidx.room.TypeConverter
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome


class ExpenseOrIncomeConverter {
    @TypeConverter
    fun expenseOrIncome2Int(value: ExpenseOrIncome): Int {
        return when (value) {
            ExpenseOrIncome.Expense -> 0
            ExpenseOrIncome.Income -> 1
        }
    }

    @TypeConverter
    fun int2ExpenseOrIncome(i: Int): ExpenseOrIncome {
        if (i == 0) return ExpenseOrIncome.Expense
        if (i == 1) return ExpenseOrIncome.Income
        throw IllegalStateException("int2ExpenseOrIncome int值不合法")
    }
}
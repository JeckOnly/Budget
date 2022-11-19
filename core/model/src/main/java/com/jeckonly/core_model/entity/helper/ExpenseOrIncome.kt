package com.jeckonly.core_model.entity.helper

sealed interface ExpenseOrIncome {
    object Expense: ExpenseOrIncome
    object Income: ExpenseOrIncome
}
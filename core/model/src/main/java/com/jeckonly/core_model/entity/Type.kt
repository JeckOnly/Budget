package com.jeckonly.core_model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome


@Entity(tableName = "type_table")
data class Type(
    @PrimaryKey @ColumnInfo(name = "type_name") val name: String,
    @ColumnInfo(name = "icon_id") val iconId: Int,
    @ColumnInfo(name = "order") val order: Int,
    @ColumnInfo(name = "expense_or_income") val expenseOrIncome: ExpenseOrIncome,
    @ColumnInfo(name = "is_customise") val isCustomise: Boolean,
    @ColumnInfo(name = "should_show") val shouldShow: Boolean,
)

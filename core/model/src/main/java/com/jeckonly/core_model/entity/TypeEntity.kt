package com.jeckonly.core_model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome


@Entity(tableName = "type_table")
data class TypeEntity(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "type_type_id") val typeId: Int = 0,
    @ColumnInfo(name = "type_name") val name: String,
    @ColumnInfo(name = "icon_id") val iconId: Int,
    @ColumnInfo(name = "order") val order: Int,
    @ColumnInfo(name = "expense_or_income") val expenseOrIncome: ExpenseOrIncome,
)

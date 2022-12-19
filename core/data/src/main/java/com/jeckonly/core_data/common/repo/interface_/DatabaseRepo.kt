package com.jeckonly.core_data.common.repo.interface_

import com.jeckonly.core_model.entity.NameNumberIconId
import com.jeckonly.core_model.entity.RecordEntity
import com.jeckonly.core_model.entity.TypeEntity
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.ui.ChooseTypeTypeUI
import com.jeckonly.core_model.ui.HomeRecordItemUI
import kotlinx.coroutines.flow.Flow

interface DatabaseRepo {

    suspend fun initTypeInDatabase(list: List<TypeEntity>, onSuccess: suspend () -> Unit, onFail: suspend () -> Unit)

    fun getAllExpenseTypeAndShouldShown(): Flow<List<ChooseTypeTypeUI>>

    fun getAllIncomeTypeAndShouldShown(): Flow<List<ChooseTypeTypeUI>>

    suspend fun insertRecord(recordEntity: RecordEntity, onSuccess: suspend () -> Unit, onFail: suspend(Exception) -> Unit)

    suspend fun getAllRecordByYearAndMonth(year: Int, month: Int): List<RecordEntity>

    suspend fun getAllHomeRecordItemUIByYearAndMonth(year: Int, month: Int): List<HomeRecordItemUI>

    suspend fun getTotalNUmberByYearAndMonthAndExpenseOrIncome(year: Int, month: Int, expenseOrIncome: ExpenseOrIncome): Double

    suspend fun getTypeAndTotalNumberByYearAndMonthAndExpenseOrIncome(
        year: Int,
        month: Int,
        expenseOrIncome: ExpenseOrIncome
    ): List<NameNumberIconId>

}
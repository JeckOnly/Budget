package com.jeckonly.core_data.common.repo.impl

import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_database.dao.BgtDao
import com.jeckonly.core_model.entity.NameNumberIconId
import com.jeckonly.core_model.entity.RecordEntity
import com.jeckonly.core_model.entity.TypeEntity
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.mapper.toChooseTypeTypeUI
import com.jeckonly.core_model.ui.ChooseTypeTypeUI
import com.jeckonly.core_model.ui.HomeRecordItemUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepoImpl @Inject constructor(
    private val dao: BgtDao
): DatabaseRepo {
    override suspend fun initTypeInDatabase(list: List<TypeEntity>, onSuccess: suspend () -> Unit, onFail: suspend () -> Unit) {
        try {
            dao.insertTypes(list)
            onSuccess()
        } catch (e: Exception) {
            onFail()
        }
    }

    override fun getAllExpenseTypeAndShouldShown(): Flow<List<ChooseTypeTypeUI>> {
        return dao.getAllTypeByExpenseOrIncomeAndShouldShown(ExpenseOrIncome.Expense).map {
            it.map { typeEntity ->
                typeEntity.toChooseTypeTypeUI()
            }
        }
    }

    override fun getAllIncomeTypeAndShouldShown(): Flow<List<ChooseTypeTypeUI>> {
        return dao.getAllTypeByExpenseOrIncomeAndShouldShown(ExpenseOrIncome.Income).map {
            it.map { typeEntity ->
                typeEntity.toChooseTypeTypeUI()
            }
        }
    }

    override suspend fun insertRecord(
        recordEntity: RecordEntity,
        onSuccess: suspend () -> Unit,
        onFail: suspend (Exception) -> Unit
    ) {
        try {
            dao.insertRecord(recordEntity)
            onSuccess()
        } catch (e: Exception) {
            onFail(e)
        }
    }

    override suspend fun getAllRecordByYearAndMonth(year: Int, month: Int): List<RecordEntity>{
        return dao.getAllRecordByYearAndMonth(year, month)
    }

    override suspend fun getAllHomeRecordItemUIByYearAndMonth(
        year: Int,
        month: Int
    ): List<HomeRecordItemUI> {
        return getAllRecordByYearAndMonth(year, month).map {
            val type = dao.getTypeByName(it.typeName)
            HomeRecordItemUI(
                year = it.year,
                month = it.month,
                dayOfMonth = it.dayOfMonth,
                number = it.number,
                expenseOrIncome = type.expenseOrIncome,
                iconId = type.iconId,
                typeName = type.name,
                remark = it.remark
            )
        }
    }

    override suspend fun getTotalNUmberByYearAndMonthAndExpenseOrIncome(
        year: Int,
        month: Int,
        expenseOrIncome: ExpenseOrIncome
    ): Double {
        return dao.getTotalNumberByYearAndMonth(year_ = year, month_ = month, expenseOrIncome_ = expenseOrIncome)?:0.00
    }

    override suspend fun getTypeAndTotalNumberByYearAndMonthAndExpenseOrIncome(
        year: Int,
        month: Int,
        expenseOrIncome: ExpenseOrIncome
    ): List<NameNumberIconId> {
        return dao.getTypeAndTotalNumberByYearAndMonth(year, month, expenseOrIncome)
    }
}




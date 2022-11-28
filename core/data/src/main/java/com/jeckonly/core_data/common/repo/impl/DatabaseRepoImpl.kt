package com.jeckonly.core_data.common.repo.impl

import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_database.dao.BgtDao
import com.jeckonly.core_model.entity.RecordEntity
import com.jeckonly.core_model.entity.TypeEntity
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.mapper.toChooseTypeTypeUI
import com.jeckonly.core_model.ui.ChooseTypeTypeUI
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
}




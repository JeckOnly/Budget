package com.jeckonly.core_data.common.repo.impl

import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_database.dao.BgtDao
import com.jeckonly.core_model.entity.NameNumberIconId
import com.jeckonly.core_model.entity.RecordEntity
import com.jeckonly.core_model.entity.TypeEntity
import com.jeckonly.core_model.entity.delete.RecordDelete
import com.jeckonly.core_model.entity.delete.TypeDelete
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.entity.update.TypeOrderUpdate
import com.jeckonly.core_model.entity.update.TypeUpdateNoOrder
import com.jeckonly.core_model.mapper.toChooseTypeTypeUI
import com.jeckonly.core_model.ui.HomeRecordItemUI
import com.jeckonly.core_model.ui.RecordDetailUI
import com.jeckonly.core_model.ui.TypeUI
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

    override fun getAllExpenseType(): Flow<List<TypeUI>> {
        return dao.getAllTypeByExpenseOrIncome(ExpenseOrIncome.Expense).map {
            it.map { typeEntity ->
                typeEntity.toChooseTypeTypeUI()
            }
        }
    }

    override fun getAllIncomeType(): Flow<List<TypeUI>> {
        return dao.getAllTypeByExpenseOrIncome(ExpenseOrIncome.Income).map {
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
            val type = dao.getTypeById(it.typeId)
            HomeRecordItemUI(
                recordId = it.recordId,
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

    override suspend fun updateTypeOrder(typeOrderUpdates: List<TypeOrderUpdate>) {
        dao.updateTypeOrder(typeOrderUpdates)
    }

    override suspend fun checkTypeHasRecordOrNot(typeId: Int): Boolean {
        return dao.countTypeByRecordId(typeId_ = typeId) > 0
    }

    override suspend fun deleteTypeById(typeId: Int) {
        dao.deleteTypeById(TypeDelete(typeId = typeId))
    }

    override suspend fun getTypeById(typeId: Int): TypeEntity {
        return dao.getTypeById(typeId)
    }

    override suspend fun insertType(vararg typeEntity: TypeEntity) {
        dao.insertTypes(typeEntity.toList())
    }

    override suspend fun updateType(typeUpdateNoOrder: TypeUpdateNoOrder) {
        dao.updateType(typeUpdateNoOrder)
    }

    override suspend fun getMaxOrder(): Int {
        return dao.getMaxOrder()
    }

    override suspend fun isTypeNameExited(typeName: String): Boolean {
        return dao.getTypeByName(typeName).isNotEmpty()
    }

    override suspend fun getRecordDetailUIById(id: Int): RecordDetailUI {
        val recordEntity =  dao.getRecordById(id)
        val type = dao.getTypeById(recordEntity.typeId)
        return RecordDetailUI(
            year = recordEntity.year,
            month = recordEntity.month,
            dayOfMonth = recordEntity.dayOfMonth,
            number = recordEntity.number,
            expenseOrIncome = type.expenseOrIncome,
            iconId = type.iconId,
            typeName = type.name,
            remark = recordEntity.remark
        )
    }

    override suspend fun deleteRecordById(id: Int) {
        dao.deleteRecordById(RecordDelete(recordId = id))
    }
}




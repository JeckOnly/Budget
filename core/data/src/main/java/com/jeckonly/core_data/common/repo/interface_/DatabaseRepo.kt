package com.jeckonly.core_data.common.repo.interface_

import com.jeckonly.core_model.entity.TypeEntity
import com.jeckonly.core_model.ui.ChooseTypeTypeUI
import kotlinx.coroutines.flow.Flow

interface DatabaseRepo {

    suspend fun initTypeInDatabase(list: List<TypeEntity>, onSuccess: suspend () -> Unit, onFail: suspend () -> Unit)

    fun getAllExpenseTypeAndShouldShown(): Flow<List<ChooseTypeTypeUI>>

    fun getAllIncomeTypeAndShouldShown(): Flow<List<ChooseTypeTypeUI>>
}
package com.jeckonly.core_data.common.repo.interface_

import kotlinx.coroutines.flow.Flow

interface UserPrefsRepo {

    suspend fun nowTypeDatabaseInitState(): Boolean

    fun getTypeDatabaseInitState(): Flow<Boolean>

    suspend fun updateTypeDatabaseInitStateToFinished()

    fun getThemeNumber(): Flow<Int>

    suspend fun updateThemeNumber(newValue: Int)
}

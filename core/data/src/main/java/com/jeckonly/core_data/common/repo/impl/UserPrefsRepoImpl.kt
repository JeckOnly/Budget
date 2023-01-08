package com.jeckonly.core_data.common.repo.impl

import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_datastore.UserPrefsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPrefsRepoImpl @Inject constructor(
    private val dataSource: UserPrefsDataSource
): UserPrefsRepo {
    override suspend fun nowTypeDatabaseInitState(): Boolean {
        return dataSource.isTypeDatabaseHasInit()
    }

    override fun getTypeDatabaseInitState(): Flow<Boolean> {
        return dataSource.getTypeDatabaseInitState()
    }

    override suspend fun updateTypeDatabaseInitStateToFinished() {
        dataSource.updateTypeDatabaseInitState(true)
    }

    override fun getThemeNumber(): Flow<Int> {
        return dataSource.getThemeNumber()
    }

    override suspend fun updateThemeNumber(newValue: Int) {
        dataSource.updateThemeNumber(newValue)
    }
}
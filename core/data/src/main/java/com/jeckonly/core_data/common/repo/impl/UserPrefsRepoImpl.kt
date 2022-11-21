package com.jeckonly.core_data.common.repo.impl

import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_datastore.UserPrefsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

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

}
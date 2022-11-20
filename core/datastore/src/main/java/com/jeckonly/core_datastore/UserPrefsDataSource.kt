package com.jeckonly.core_datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPrefsDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    fun getDatabaseInitState(): Flow<Boolean> {
        return userPreferences.data.map {
           it.databaseTypeHasInit
        }
    }

    suspend fun updateDatabaseInitState(hasInit: Boolean) {
        userPreferences.updateData { preferences ->
            preferences.toBuilder().setDatabaseTypeHasInit(hasInit).build()
        }
    }
}
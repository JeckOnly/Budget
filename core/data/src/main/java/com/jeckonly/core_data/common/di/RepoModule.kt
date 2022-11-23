package com.jeckonly.core_data.common.di

import com.jeckonly.core_data.common.repo.impl.DatabaseRepoImpl
import com.jeckonly.core_data.common.repo.impl.UserPrefsRepoImpl
import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindUserPrefsRepo(
        userPrefsRepoImpl: UserPrefsRepoImpl
    ): UserPrefsRepo

    @Binds
    @Singleton
    abstract fun bindDatabaseRepo(
        databaseRepoImpl: DatabaseRepoImpl
    ): DatabaseRepo
}
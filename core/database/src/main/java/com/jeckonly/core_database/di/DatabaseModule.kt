package com.jeckonly.core_database.di

import android.app.Application
import androidx.room.Room
import com.jeckonly.core_database.dao.BgtDao
import com.jeckonly.core_database.database.BgtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideBgtDatabase(app: Application): BgtDatabase {
        return Room.databaseBuilder(
            app,
            BgtDatabase::class.java, "budget-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBgtDao(database: BgtDatabase): BgtDao {
        return database.bgtDao()
    }
}
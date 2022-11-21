package com.jeckonly.core_database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeckonly.core_database.dao.BgtDao
import com.jeckonly.core_model.entity.RecordEntity
import com.jeckonly.core_model.entity.TypeEntity
import com.jeckonly.core_model.entity.converter.ExpenseOrIncomeConverter

@Database(
    entities = [TypeEntity::class, RecordEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ExpenseOrIncomeConverter::class)
abstract class BgtDatabase : RoomDatabase() {

    abstract fun bgtDao(): BgtDao
}
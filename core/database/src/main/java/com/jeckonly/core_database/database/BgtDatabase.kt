package com.jeckonly.core_database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeckonly.core_database.dao.BgtDao
import com.jeckonly.core_model.entity.Record
import com.jeckonly.core_model.entity.Type
import com.jeckonly.core_model.entity.converter.ExpenseOrIncomeConverter

@Database(
    entities = [Type::class, Record::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ExpenseOrIncomeConverter::class)
abstract class BgtDatabase : RoomDatabase() {

    abstract fun bgtDao(): BgtDao
}
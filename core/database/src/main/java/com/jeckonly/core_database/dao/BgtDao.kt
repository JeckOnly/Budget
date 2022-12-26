package com.jeckonly.core_database.dao

import androidx.room.*
import com.jeckonly.core_model.entity.NameNumberIconId
import com.jeckonly.core_model.entity.RecordEntity
import com.jeckonly.core_model.entity.TypeEntity
import com.jeckonly.core_model.entity.delete.TypeDelete
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.entity.update.TypeOrderUpdate
import kotlinx.coroutines.flow.Flow

@Dao
interface BgtDao {

    // Type

    /**
     * 如果有冲突，拒绝本次插入事务（整个列表都不成功）。
     *
     * @return list rowId
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTypes(typeEntities: List<TypeEntity>): List<Long>

    /**
     * NOTE 级联删除对应[RecordEntity]
     *
     * @return 返回成功删除的行数
     */
    @Delete
    suspend fun deleteType(typeEntity: TypeEntity): Int

    /**
     * NOTE 级联更新对应[RecordEntity]
     * @return 返回成功更新的行数
     */
    @Update
    suspend fun updateType(typeEntity: TypeEntity): Int

    /**
     * @return 所有类型是[expenseOrIncome_]的类型, 且应该显示。
     */
    @Query("SELECT * FROM type_table where expense_or_income = :expenseOrIncome_")
    fun getAllTypeByExpenseOrIncome(expenseOrIncome_: ExpenseOrIncome): Flow<List<TypeEntity>>

    /**
     * 根据名字查找类型
     */
    @Query("select * from type_table where type_name == :typeName_")
    fun getTypeByName(typeName_: String): TypeEntity

    /**
     * 根据id查找类型
     */
    @Query("select * from type_table where type_type_id == :typeId_")
    suspend fun getTypeById(typeId_: Int): TypeEntity

    @Update(entity = TypeEntity::class)
    suspend fun updateTypeOrder(typeOrderUpdates: List<TypeOrderUpdate>)

    @Delete(entity = TypeEntity::class)
    suspend fun deleteTypeById(typeDelete: TypeDelete)


    // Record

    /**
     * 理论上来说，[RecordEntity]的主键是[androidx.room.PrimaryKey.autoGenerate]，不会有冲突.
     *
     * NOTE 如果[RecordEntity.typeName]没有对应的[TypeEntity]， 即外键找不到对应的项————报错。
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertRecord(recordEntity: RecordEntity): Long

    /**
     * @return 返回成功删除的行数
     */
    @Delete
    suspend fun deleteRecord(recordEntity: RecordEntity): Int

    /**
     * @return 返回成功更新的行数
     */
    @Update
    suspend fun updateRecord(recordEntity: RecordEntity): Int

    /**
     * @return 返回指定月的所有记录，按照日期降序排列
     */
    @Query("select * from record_table where year == :year_ and month == :month_ order by day_month desc")
    suspend fun getAllRecordByYearAndMonth(year_: Int, month_: Int): List<RecordEntity>

    /**
     * @return 指定月的收入或支出的总金额
     */
    @Query("select sum(number) from record_table " +
            "inner join type_table on record_table.record_type_id == type_table.type_type_id and type_table.expense_or_income == :expenseOrIncome_ " +
            "where year == :year_ and month == :month_")
    suspend fun getTotalNumberByYearAndMonth(year_: Int, month_: Int, expenseOrIncome_: ExpenseOrIncome): Double?

    /**
     * @return
     */
    @Query("select type_table.type_name as type_name, sum(number) as number, type_table.icon_id as icon_id from record_table " +
            "inner join type_table on record_table.record_type_id == type_table.type_type_id and type_table.expense_or_income == :expenseOrIncome_ " +
            "where year == :year_ and month == :month_ group by type_table.type_name order by number desc")
    suspend fun getTypeAndTotalNumberByYearAndMonth(year_: Int, month_: Int, expenseOrIncome_: ExpenseOrIncome): List<NameNumberIconId>

    /**
     * 根据名字查找类型
     */
    @Query("select count(*) from record_table where record_type_id == :typeId_")
    suspend fun countTypeByRecordId(typeId_: Int): Int
}

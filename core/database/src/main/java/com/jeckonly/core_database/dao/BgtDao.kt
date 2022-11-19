package com.jeckonly.core_database.dao

import androidx.room.*
import com.jeckonly.core_model.entity.Record
import com.jeckonly.core_model.entity.Type
import kotlinx.coroutines.flow.Flow

@Dao
interface BgtDao {

    /**
     * 如果有冲突，拒绝本次插入。
     *
     * @return rowId
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertType(type: Type): Long

    /**
     * NOTE 级联删除对应[Record]
     *
     * @return 返回成功删除的行数
     */
    @Delete
    suspend fun deleteType(type: Type): Int

    /**
     * NOTE 级联更新对应[Record]
     * @return 返回成功更新的行数
     */
    @Update
    suspend fun updateType(type: Type): Int

    /**
     * @return 所有类型
     */
    @Query("SELECT * FROM type_table")
    suspend fun getAllType(): Flow<List<Type>>

    /**
     * 理论上来说，[Record]的主键是[androidx.room.PrimaryKey.autoGenerate]，不会有冲突.
     *
     * NOTE 如果[Record.typeName]没有对应的[Type]， 即外键找不到对应的项————报错。
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertRecord(record: Record): Long

    /**
     * @return 返回成功删除的行数
     */
    @Delete
    suspend fun deleteRecord(type: Type): Int

    /**
     * NOTE 级联更新对应[Record]
     * @return 返回成功更新的行数
     */
    @Update
    suspend fun updateRecord(record: Record): Int

    // TODO 对应Entity的查找dto
}

//@Dao
//interface PokemonInfoEntityDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertPokemonList(pokemonList: List<PokemonInfoEntity>)
//
//    @Query("SELECT * FROM PokemonInfoEntity WHERE page = :page_")
//    suspend fun getPokemonListOnePage(page_: Int): List<PokemonInfoEntity>
//
//    @Query("SELECT * FROM PokemonInfoEntity WHERE page <= :page_")
//    suspend fun getAllPokemonListLessThanPage(page_: Int): List<PokemonInfoEntity>
//
//    /**
//     * 根据id精确匹配
//     */
//    @Query("SELECT * FROM PokemonInfoEntity WHERE id == :id_")
//    suspend fun getPokemonById(id_: Int): List<PokemonInfoEntity>
//
//    /**
//     * 根据字符串模糊匹配名字
//     */
//    @Query("SELECT * FROM PokemonInfoEntity WHERE name LIKE '%' || :search || '%'")
//    suspend fun getPokemonByLikeName(search: String): List<PokemonInfoEntity>
//}
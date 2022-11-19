package com.jeckonly.core_model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


/**
 * 子表的外键需要开启“索引”，否则Room会发出Warning。
 *
 * ForeignKey可以设置是否deferred(推迟)，默认为false，如果设置为true，则在出现不一致冲突时不会立即报错，而会在一个
 * transaction(事务)结束之后才报错。在本例中由于没有“显式”定义事务，所以这个字段是否为true表现一样——在冲突发生时报错。
 */
@Entity(tableName = "record_table",
    foreignKeys = [ForeignKey(
        entity = Type::class,
        childColumns = ["type_name"],
        parentColumns = ["type_name"],
        onDelete = ForeignKey.CASCADE,// 删除类型的时候，级联删除记录
        onUpdate = ForeignKey.CASCADE// 更改类型的时候，级联更改
    )])
data class Record(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "record_id") val id: Int = 0,
    @ColumnInfo(name = "time_stamp") val timeStamp: Long,
    @ColumnInfo(name = "number") val number: Double,
    @ColumnInfo(name = "type_name", index = true) val typeName: String,
    @ColumnInfo(name = "remark") val remark: String,
)

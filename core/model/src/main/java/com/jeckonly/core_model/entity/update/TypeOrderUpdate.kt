package com.jeckonly.core_model.entity.update

import androidx.room.ColumnInfo

data class TypeOrderUpdate (
    @ColumnInfo(name = "type_type_id") val typeId: Int,
    @ColumnInfo(name = "order") val order: Int,
)

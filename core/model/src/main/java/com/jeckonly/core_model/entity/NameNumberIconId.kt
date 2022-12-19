package com.jeckonly.core_model.entity

import androidx.room.ColumnInfo

data class NameNumberIconId(
    @ColumnInfo(name = "type_name") val typeName: String,
    @ColumnInfo(name = "number") val number: Double,
    @ColumnInfo(name = "icon_id") val iconId: Int
)

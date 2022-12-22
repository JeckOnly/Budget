package com.jeckonly.choosetype.ui.state

import com.jeckonly.core_model.entity.RecordEntity

data class ChooseTypeFinishState(
    val year: Int,
    val month: Int,
    val dayOfMonth: Int,
    val number: Double,
    val typeId: Int,
    val remark: String,
) {
    fun toRecordEntity(): RecordEntity {
        return RecordEntity(
            year = this.year,
            month = this.month,
            dayOfMonth = this.dayOfMonth,
            number = this.number,
            typeId = typeId,
            remark = this.remark
        )
    }
}


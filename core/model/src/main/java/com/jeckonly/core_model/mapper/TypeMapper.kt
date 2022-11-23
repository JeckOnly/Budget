package com.jeckonly.core_model.mapper

import com.jeckonly.core_model.entity.TypeEntity
import com.jeckonly.core_model.ui.ChooseTypeTypeUI

fun TypeEntity.toChooseTypeTypeUI(): ChooseTypeTypeUI {
    return ChooseTypeTypeUI(
        iconId = this.iconId,
        typeName = this.name,
        order = this.order,
        expenseOrIncome = expenseOrIncome
    )
}
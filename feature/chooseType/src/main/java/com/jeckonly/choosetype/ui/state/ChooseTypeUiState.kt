package com.jeckonly.choosetype.ui.state

import androidx.compose.runtime.Stable
import com.jeckonly.core_model.ui.TypeUI

@Stable
data class ChooseTypeUiState(
    val isLoading: Boolean = true,
    val expenseTypeList: List<TypeUI> = emptyList(),
    val incomeTypeList: List<TypeUI> = emptyList(),
)

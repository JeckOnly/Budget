package com.jeckonly.choosetype.ui.state

import androidx.compose.runtime.Stable
import com.jeckonly.core_model.ui.ChooseTypeTypeUI

@Stable
data class ChooseTypeUiState(
    val isLoading: Boolean = true,
    val expenseTypeList: List<ChooseTypeTypeUI> = emptyList(),
    val incomeTypeList: List<ChooseTypeTypeUI> = emptyList(),
)

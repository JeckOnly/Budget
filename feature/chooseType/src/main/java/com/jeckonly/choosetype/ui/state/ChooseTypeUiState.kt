package com.jeckonly.choosetype.ui.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import com.jeckonly.core_model.ui.TypeUI

@Stable
data class ChooseTypeUiState(
    val isLoading: Boolean = true,
    val nowChooseType: MutableState<TypeUI?> = mutableStateOf(null),
    val canEnterSetting: MutableState<Boolean> = mutableStateOf(true),
    val expenseTypeList: List<TypeUI> = emptyList(),
    val incomeTypeList: List<TypeUI> = emptyList(),
)

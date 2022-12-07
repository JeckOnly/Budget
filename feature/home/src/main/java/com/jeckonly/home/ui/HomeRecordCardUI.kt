package com.jeckonly.home.ui

import androidx.compose.runtime.Stable
import com.jeckonly.core_model.ui.HomeRecordItemUI
import java.time.LocalDate

@Stable
data class HomeRecordCardUI(
    val localDate: LocalDate,
    val totalBalance: Double,
    val recordList: List<HomeRecordItemUI>
)
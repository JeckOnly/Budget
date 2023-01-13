package com.jeckonly.recorddetail.ui.state

import androidx.compose.runtime.Stable
import com.jeckonly.designsystem.R
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Stable
data class RecordDetailUIState(
    val iconId: Int = R.drawable.warning,
    val typeName: String = "",
    val expenseOrIncome: String = "",
    val showNumber: String = "",
    val showDate: String = "",
    val remark: String = ""
) {
    companion object {
        fun makeShowDate(year: Int, month: Int, dayOfMonth: Int): String {
            val localDate = LocalDate.of(year, month, dayOfMonth)
            return "${localDate.year}/${localDate.month.value}/${localDate.dayOfMonth} ${
                localDate.dayOfWeek.getDisplayName(
                    TextStyle.FULL_STANDALONE, Locale.getDefault()
                )
            }"
        }
    }
}

package com.jeckonly.home.ui.state

import androidx.compose.runtime.Stable
import com.jeckonly.core_model.ui.HomeRecordItemUI
import com.jeckonly.util.FormatNumberUtil
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Stable
data class HomeRecordCardUI(
    val localDate: LocalDate,
    val totalBalance: Double,
    val recordList: List<HomeRecordItemUI>
) {
    val showDate: String =
        localDate.dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())
            .uppercase() + "," + localDate.dayOfMonth + " " + localDate.month.getDisplayName(
            TextStyle.SHORT,
            Locale.getDefault()
        ).uppercase()
    val showBalance: String = FormatNumberUtil.format(totalBalance)
}

fun main() {
    val homeRecordCardUI =
        HomeRecordCardUI(localDate = LocalDate.of(2000, 12, 25), 255.0, emptyList())
    print(homeRecordCardUI.showDate)
}
package com.jeckonly.util

import java.text.DecimalFormat

object FormatNumberUtil {
    /**
     * 格式化数字，使6.00显示为6
     */
    private val decimalFormat = DecimalFormat("#.##")

    fun format(d: Double): String {
        return decimalFormat.format(d)
    }
}
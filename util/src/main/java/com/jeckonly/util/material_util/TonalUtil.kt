package com.jeckonly.util.material_util

import com.jeckonly.util.material_util.palettes.TonalPalette

object TonalUtil {
    private var oldArgb = 0

    private var tonalPalette: TonalPalette? = null


    private fun orderToTonalPercent(order: Int): Int {
        return when (order) {
            1 -> {
                20
            }
            2 -> {
                35
            }
            3 -> {
                50
            }
            4 -> {
                75
            }
            else -> {
                90
            }
        }
    }

    fun argbToTonalSpecific(argb: Int, order: Int): Int {
        if (argb != oldArgb) {
            oldArgb = argb
            tonalPalette = TonalPalette.fromInt(oldArgb)
        }
        return tonalPalette?.tone(orderToTonalPercent(order)) ?: argb
    }
}
package com.jeckonly.chart.ui.state

import androidx.compose.runtime.Stable

@Stable
data class PieChartSegmentUI(
    val typeName: String,
    val colorOrder: Int,
    val startDegree: Double,
    val sweepDegree: Double
)

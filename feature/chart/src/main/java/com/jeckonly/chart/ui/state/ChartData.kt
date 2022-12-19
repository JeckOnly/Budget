package com.jeckonly.chart.ui.state

import androidx.compose.runtime.Stable

@Stable
data class ChartData(
    val leaderBoardItemUIList: List<LeaderBoardItemUI> = emptyList(),
    val theOtherLeaderBoardSpecificItem: LeaderBoardItemUI? = null,
    val pieChartSegmentUIList: List<PieChartSegmentUI> = emptyList()
)

package com.jeckonly.chart.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeckonly.chart.ChartRoute
import com.jeckonly.navigation.BgtBottomNavigationDestination

object ChartDestination : BgtBottomNavigationDestination {
    override val route = "chart_route"
    override val destination = "chart_destination"
}

fun NavGraphBuilder.chartGraph() {
    composable(route = ChartDestination.route) {
        ChartRoute()
    }
}
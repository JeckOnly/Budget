package com.jeckonly.chart.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jeckonly.chart.ChartRoute
import com.jeckonly.navigation.BgtNavigationDestination
import com.jeckonly.navigation.topLevelDestinationIsSelected
import com.jeckonly.navigation.topLevelDestinationNavigate

object ChartDestination : BgtNavigationDestination {
    override val route = "chart_route"
    override val destination = "chart_destination"

    override fun navigate(navController: NavHostController) {
        topLevelDestinationNavigate(navController, route)
    }

    override fun isSelected(currentDestination: NavDestination?): Boolean {
        return topLevelDestinationIsSelected(currentDestination, route)
    }
}

fun NavGraphBuilder.chartGraph() {
    composable(route = ChartDestination.route) {
        ChartRoute()
    }
}
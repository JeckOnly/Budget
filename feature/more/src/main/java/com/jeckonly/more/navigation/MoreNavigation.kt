package com.jeckonly.more.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jeckonly.more.MoreRoute
import com.jeckonly.navigation.BgtNavigationDestination
import com.jeckonly.navigation.topLevelDestinationIsSelected
import com.jeckonly.navigation.topLevelDestinationNavigate

object MoreDestination : BgtNavigationDestination {
    override val route = "more_route"
    override val destination = "more_destination"
    override fun navigate(navController: NavHostController) {
        topLevelDestinationNavigate(navController, route)
    }
    override fun isSelected(currentDestination: NavDestination?): Boolean {
        return topLevelDestinationIsSelected(currentDestination, route)
    }
}

fun NavGraphBuilder.moreGraph() {
    composable(route = MoreDestination.route) {
        MoreRoute()
    }
}
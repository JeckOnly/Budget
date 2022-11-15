package com.jeckonly.home.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jeckonly.home.HomeRoute
import com.jeckonly.navigation.BgtNavigationDestination
import com.jeckonly.navigation.topLevelDestinationIsSelected
import com.jeckonly.navigation.topLevelDestinationNavigate

object HomeDestination : BgtNavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
    override fun navigate(navController: NavHostController) {
        topLevelDestinationNavigate(navController, route)
    }
    override fun isSelected(currentDestination: NavDestination?): Boolean {
        return topLevelDestinationIsSelected(currentDestination, route)
    }
}

fun NavGraphBuilder.homesGraph() {
    composable(route = HomeDestination.route) {
        HomeRoute()
    }
}
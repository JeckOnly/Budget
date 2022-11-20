package com.jeckonly.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeckonly.home.HomeRoute
import com.jeckonly.navigation.BgtBottomNavigationDestination

object HomeDestination : BgtBottomNavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.homesGraph() {
    composable(route = HomeDestination.route) {
        HomeRoute()
    }
}
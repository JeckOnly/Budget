package com.jeckonly.more.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeckonly.more.MoreRoute
import com.jeckonly.navigation.BgtBottomNavigationDestination

object MoreDestination : BgtBottomNavigationDestination {
    override val route = "more_route"
    override val destination = "more_destination"
}

fun NavGraphBuilder.moreGraph() {
    composable(route = MoreDestination.route) {
        MoreRoute()
    }
}
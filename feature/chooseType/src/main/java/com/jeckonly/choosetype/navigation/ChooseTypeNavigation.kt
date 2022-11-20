package com.jeckonly.choosetype.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeckonly.choosetype.ChooseTypeRoute
import com.jeckonly.navigation.BgtTopLevelNavigationDestination

object ChooseTypeNavigation : BgtTopLevelNavigationDestination {
    override val route = "choose_type_route"
    override val destination = "choose_type_destination"
}

fun NavGraphBuilder.chooseTypeGraph() {
    composable(route = ChooseTypeNavigation.route) {
        ChooseTypeRoute()
    }
}
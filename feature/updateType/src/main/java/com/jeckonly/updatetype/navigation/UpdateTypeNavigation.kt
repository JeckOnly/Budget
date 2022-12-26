package com.jeckonly.updatetype.navigation

import androidx.compose.animation.*
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.jeckonly.navigation.BgtTopLevelNavigationDestination
import com.jeckonly.updatetype.UpdateTypeRoute

object UpdateTypeNavigation : BgtTopLevelNavigationDestination {
    override val route = "update_type_route"
    override val destination = "update_type_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.updateTypeGraph() {
    composable(
        route = UpdateTypeNavigation.route,
        enterTransition = {
            slideInVertically {
                it
            }
        },
        exitTransition = {
            slideOutVertically {
                it
            }
        }
    ) {
        UpdateTypeRoute()
    }
}
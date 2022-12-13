package com.jeckonly.more.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.jeckonly.more.MoreRoute
import com.jeckonly.navigation.BgtBottomNavigationDestination

object MoreDestination : BgtBottomNavigationDestination {
    override val route = "more_route"
    override val destination = "more_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.moreGraph() {
    composable(route = MoreDestination.route,
        enterTransition = {
            slideInHorizontally {
                it
            }

        },
        exitTransition = {
            slideOutHorizontally {
                it
            }
        }) {
        MoreRoute()
    }
}
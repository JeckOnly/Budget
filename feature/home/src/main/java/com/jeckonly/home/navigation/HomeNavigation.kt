package com.jeckonly.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.jeckonly.home.HomeRoute
import com.jeckonly.navigation.BgtBottomNavigationDestination

object HomeDestination : BgtBottomNavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homesGraph() {
    composable(
        route = HomeDestination.route,
        enterTransition = {
            slideInHorizontally {
                -it
            }

        },
        exitTransition = {
            slideOutHorizontally {
                -it
            }
        }
    ) {
        HomeRoute()
    }
}
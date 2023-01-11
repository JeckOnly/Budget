package com.jeckonly.home.navigation

import androidx.compose.animation.*
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
            when (initialState.destination.route) {
                "chart_route", "more_route" -> {
                    slideInHorizontally {
                        -it
                    }
                }
                else -> {
                    slideInVertically {
                        it
                    }
                }
            }

        },
        exitTransition = {
            when (targetState.destination.route) {
                "chart_route", "more_route" -> {
                    slideOutHorizontally {
                        -it
                    }
                }
                else -> {
                    slideOutVertically {
                        it
                    }
                }
            }
        }
    ) {
        HomeRoute()
    }
}
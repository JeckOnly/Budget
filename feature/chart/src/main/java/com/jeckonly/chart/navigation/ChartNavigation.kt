package com.jeckonly.chart.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.jeckonly.chart.ChartRoute
import com.jeckonly.navigation.BgtBottomNavigationDestination

object ChartDestination : BgtBottomNavigationDestination {
    override val route = "chart_route"
    override val destination = "chart_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.chartGraph() {
    composable(
        route = ChartDestination.route,
        enterTransition = {
            when (initialState.destination.route) {
                "home_route" -> {
                    slideInHorizontally {
                        it
                    }
                }
                "more_route" -> {
                    slideInHorizontally {
                        -it
                    }
                }
                else -> {
                    null
                }
            }
        },
        exitTransition = {
            when (targetState.destination.route) {
                "home_route" -> {
                    slideOutHorizontally {
                        it
                    }
                }
                "more_route" -> {
                    slideOutHorizontally {
                        -it
                    }
                }
                else -> {
                    null
                }
            }
        }
    ) {
        ChartRoute()
    }
}
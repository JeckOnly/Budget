package com.jeckonly.more.navigation

import androidx.compose.animation.*
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.jeckonly.more.MoreRoute
import com.jeckonly.navigation.BgtBottomNavigationDestination

object MoreDestination : BgtBottomNavigationDestination {
    override val route = "more_route"
    override val destination = "more_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.moreGraph(toChangeThemeScreen: () -> Unit, toChangeLangScreen: () -> Unit) {
    composable(route = MoreDestination.route,
        enterTransition = {
            when (initialState.destination.route) {
                "home_route", "chart_route" -> {
                    slideInHorizontally {
                        it
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
                "home_route", "chart_route" -> {
                    slideOutHorizontally {
                        it
                    }
                }
                else -> {
                    slideOutVertically {
                        it
                    }
                }
            }
        }) {
        MoreRoute(toChangeThemeScreen = toChangeThemeScreen, toChangeLangScreen = toChangeLangScreen)
    }
}
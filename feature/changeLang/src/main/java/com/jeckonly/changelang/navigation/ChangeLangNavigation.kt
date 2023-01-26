package com.jeckonly.changelang.navigation

import androidx.compose.animation.*
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.jeckonly.changelang.ChangeLangRoute
import com.jeckonly.navigation.BgtTopLevelNavigationDestination

object ChangeLangNavigation : BgtTopLevelNavigationDestination {
    override val route = "change_lang_route"
    override val destination = "change_lang_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.changeLangGraph() {
    composable(
        route = ChangeLangNavigation.route,
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
        ChangeLangRoute()
    }
}
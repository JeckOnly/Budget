package com.jeckonly.changetheme.navigation

import androidx.compose.animation.*
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.jeckonly.changetheme.ChangeThemeRoute
import com.jeckonly.navigation.BgtTopLevelNavigationDestination

object ChangeThemeNavigation : BgtTopLevelNavigationDestination {
    override val route = "change_theme_route"
    override val destination = "change_theme_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.changeThemeGraph() {
    composable(
        route = ChangeThemeNavigation.route,
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
        ChangeThemeRoute()
    }
}
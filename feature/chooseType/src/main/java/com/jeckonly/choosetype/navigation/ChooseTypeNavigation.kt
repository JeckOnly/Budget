package com.jeckonly.choosetype.navigation

import androidx.compose.animation.*
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.jeckonly.choosetype.ChooseTypeRoute
import com.jeckonly.navigation.BgtTopLevelNavigationDestination

object ChooseTypeNavigation : BgtTopLevelNavigationDestination {
    override val route = "choose_type_route"
    override val destination = "choose_type_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.chooseTypeGraph(onCLickSetting: () -> Unit) {
    composable(
        route = ChooseTypeNavigation.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        ChooseTypeRoute(onCLickSetting = onCLickSetting)
    }
}
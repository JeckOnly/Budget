package com.jeckonly.choosetype.navigation

import androidx.compose.animation.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.jeckonly.choosetype.ChooseTypeRoute
import com.jeckonly.navigation.BgtTopLevelNavigationDestination

object ChooseTypeNavigation : BgtTopLevelNavigationDestination {
    override val route = "choose_type_route/{isEditOrNewAdd}"
    override val destination = "choose_type_destination"

    const val EDIT = -1

    fun buildRoute(isEditOrNewAdd: Int): String {
        return "choose_type_route/${isEditOrNewAdd}"
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.chooseTypeGraph(onCLickSetting: () -> Unit, popBackStack: () -> Unit) {
    composable(
        route = ChooseTypeNavigation.route,
        arguments = listOf(
            navArgument("isEditOrNewAdd") { type = NavType.IntType }
        ),
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
    ) { backStackEntry ->
        val isEditOrRecordId = backStackEntry.arguments?.getInt("isEditOrNewAdd")
        ChooseTypeRoute(isEditOrRecordId = isEditOrRecordId!!, onCLickSetting = onCLickSetting, popBackStack = popBackStack)
    }
}
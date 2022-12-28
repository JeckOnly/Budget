package com.jeckonly.addtype.navigation

import androidx.compose.animation.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.jeckonly.addtype.AddTypeRoute
import com.jeckonly.navigation.BgtTopLevelNavigationDestination

object AddTypeNavigation : BgtTopLevelNavigationDestination {
    override val route = "add_type_route/{id}"
    override val destination = "add_type_destination"

    fun buildRoute(id: Int): String {
        return "add_type_route/${id}"
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addTypeGraph(onClickBack: () -> Unit, onFinish: () -> Unit,) {
    composable(
        route = AddTypeNavigation.route,
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
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
        val id = backStackEntry.arguments?.getInt("id")
        AddTypeRoute(typeId = id?:-1, onClickBack = onClickBack, onFinish = onFinish)
    }
}
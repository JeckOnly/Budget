package com.jeckonly.recorddetail.navigation

import androidx.compose.animation.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.jeckonly.navigation.BgtTopLevelNavigationDestination
import com.jeckonly.recorddetail.RecordDetailRoute

object RecordDetailNavigation : BgtTopLevelNavigationDestination {
    override val route = "record_detail_route/{recordId}"
    override val destination = "record_detail_destination"

    fun buildRoute(recordId: Int): String {
        return "record_detail_route/${recordId}"
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.recordDetailGraph(
    toEditRecord: (Int) -> Unit,
    popBackStack: () -> Unit,
) {
    composable(
        route = RecordDetailNavigation.route,
        arguments = listOf(
            navArgument("recordId") { type = NavType.IntType }
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
        val recordId = backStackEntry.arguments?.getInt("recordId")
        RecordDetailRoute(recordId = recordId ?: -1,toEditRecord = toEditRecord ,popBackStack = popBackStack)
    }
}
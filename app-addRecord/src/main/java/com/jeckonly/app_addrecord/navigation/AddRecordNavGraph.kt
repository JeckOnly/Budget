package com.jeckonly.app_addrecord.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.jeckonly.choosetype.navigation.ChooseTypeNavigation
import com.jeckonly.choosetype.navigation.chooseTypeGraph
import com.jeckonly.updatetype.navigation.UpdateTypeNavigation
import com.jeckonly.updatetype.navigation.updateTypeGraph

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddRecordNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    AnimatedNavHost(
        navController = navController,
        startDestination = ChooseTypeNavigation.route,
        modifier = modifier
    ) {
        chooseTypeGraph {
            UpdateTypeNavigation.navigate(navController)
        }
        updateTypeGraph()
    }
}
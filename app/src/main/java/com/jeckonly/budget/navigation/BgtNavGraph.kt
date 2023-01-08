package com.jeckonly.budget.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.jeckonly.changetheme.navigation.ChangeThemeNavigation
import com.jeckonly.changetheme.navigation.changeThemeGraph
import com.jeckonly.chart.navigation.chartGraph
import com.jeckonly.home.navigation.HomeDestination
import com.jeckonly.home.navigation.homesGraph
import com.jeckonly.more.navigation.moreGraph

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BgtNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    AnimatedNavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        homesGraph()
        chartGraph()
        moreGraph(
            toChangeThemeScreen = {
                ChangeThemeNavigation.navigate(navController)
            }
        )
        changeThemeGraph()
    }
}
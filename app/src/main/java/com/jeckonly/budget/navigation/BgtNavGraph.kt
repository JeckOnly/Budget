package com.jeckonly.budget.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jeckonly.chart.navigation.chartGraph
import com.jeckonly.home.navigation.HomeDestination
import com.jeckonly.home.navigation.homesGraph
import com.jeckonly.more.navigation.moreGraph

@Composable
fun BgtNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        homesGraph()
        chartGraph()
        moreGraph()
    }
}
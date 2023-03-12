package com.jeckonly.budget.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jeckonly.changetheme.ChangeThemeViewModel
import com.jeckonly.chart.navigation.ChartDestination
import com.jeckonly.choosetype.navigation.ChooseTypeNavigation
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.composable.navigation.BgtBottomBar
import com.jeckonly.designsystem.theme.BudgetTheme
import com.jeckonly.home.navigation.HomeDestination
import com.jeckonly.more.navigation.MoreDestination

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BgtApp(
    viewModel: ChangeThemeViewModel = hiltViewModel()
) {

    val budgetColorTheme = viewModel.themeFlow.collectAsState()
    BudgetTheme(
        colorScheme = budgetColorTheme.value,
        wantDynamic = false
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Mdf
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            val navController = rememberAnimatedNavController()
            val currentBackStackEntry = navController.currentBackStackEntryAsState()

            Box(modifier = Mdf.fillMaxSize()) {
                BgtNavGraph(navController = navController, modifier = Mdf.fillMaxSize())
                AnimatedVisibility(
                    visible = shouldShowBottomBar(currentBackStackEntry),
                    modifier = Mdf.align(Alignment.BottomCenter),
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it }) {
                    BgtBottomBar(
                        onClickHome = {
                            if (!HomeDestination.isInHierarchy(navController.currentDestination)) {
                                HomeDestination.navigate(navController)
                            }
                        },
                        onClickChart = {
                            if (!ChartDestination.isInHierarchy(navController.currentDestination)) {
                                ChartDestination.navigate(navController)
                            }
                        },
                        onClickMore = {
                            if (!MoreDestination.isInHierarchy(navController.currentDestination)) {
                                MoreDestination.navigate(navController)
                            }
                        },
                        onClickPlus = {
                              ChooseTypeNavigation.navigate(navController, ChooseTypeNavigation.buildRoute(ChooseTypeNavigation.EDIT))
                        },
                        navController = navController,
                        homeDestination = HomeDestination,
                        chartDestination = ChartDestination,
                        moreDestination = MoreDestination,
                    )
                }
            }
        }
    }
}

private fun shouldShowBottomBar(currentBackStackEntry: State<NavBackStackEntry?>) =
    HomeDestination.checkCurrentIsMe(currentBackStackEntry.value?.destination) ||
            ChartDestination.checkCurrentIsMe(currentBackStackEntry.value?.destination) ||
            MoreDestination.checkCurrentIsMe(currentBackStackEntry.value?.destination)
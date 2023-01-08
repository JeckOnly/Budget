package com.jeckonly.budget.navigation

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jeckonly.app_addrecord.AddRecordActivity
import com.jeckonly.chart.navigation.ChartDestination
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.composable.navigation.BgtBottomBar
import com.jeckonly.designsystem.theme.BudgetTheme
import com.jeckonly.home.navigation.HomeDestination
import com.jeckonly.more.navigation.MoreDestination

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BgtApp() {

    val activity = LocalContext.current as Activity
    BudgetTheme(
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
                            activity.startActivity(
                                Intent(activity, AddRecordActivity::class.java),
                                ActivityOptions.makeSceneTransitionAnimation(activity)
                                    .toBundle()
                            )
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
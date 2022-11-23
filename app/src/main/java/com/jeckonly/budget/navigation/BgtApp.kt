package com.jeckonly.budget.navigation

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.jeckonly.app_addrecord.AddRecordActivity
import com.jeckonly.chart.navigation.ChartDestination
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.composable.navigation.BgtBottomBar
import com.jeckonly.designsystem.theme.BudgetTheme
import com.jeckonly.home.navigation.HomeDestination
import com.jeckonly.more.navigation.MoreDestination

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
            val navController = rememberNavController()

            Box(modifier = Mdf.fillMaxSize()) {
                BgtNavGraph(navController = navController, modifier = Mdf.fillMaxSize())
                BgtBottomBar(
                    onClickHome = { HomeDestination.navigate(navController) },
                    onClickChart = { ChartDestination.navigate(navController) },
                    onClickMore = { MoreDestination.navigate(navController) },
                    onClickPlus = {
                        activity.startActivity(Intent(activity, AddRecordActivity::class.java),
                            ActivityOptions.makeSceneTransitionAnimation(activity)
                                .toBundle()
                        )
                    },
                    navController = navController,
                    homeDestination = HomeDestination,
                    chartDestination = ChartDestination,
                    moreDestination = MoreDestination,
                    modifier = Mdf.align(Alignment.BottomCenter)
                )
            }
        }
    }
}
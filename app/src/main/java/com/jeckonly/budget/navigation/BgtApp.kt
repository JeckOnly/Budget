package com.jeckonly.budget.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.rememberNavController
import com.jeckonly.chart.navigation.ChartDestination
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.composable.navigation.BgtBottomBar
import com.jeckonly.designsystem.theme.BudgetTheme
import com.jeckonly.home.navigation.HomeDestination
import com.jeckonly.more.navigation.MoreDestination

@Composable
fun BgtApp() {

    BudgetTheme(
        wantDynamic = false
    ) {
        Surface(
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
                    onClickPlus = { /*TODO*/ },
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
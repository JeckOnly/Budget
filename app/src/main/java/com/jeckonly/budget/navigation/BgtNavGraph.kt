package com.jeckonly.budget.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.jeckonly.addtype.navigation.AddTypeNavigation
import com.jeckonly.addtype.navigation.addTypeGraph
import com.jeckonly.changelang.navigation.ChangeLangNavigation
import com.jeckonly.changelang.navigation.changeLangGraph
import com.jeckonly.changetheme.navigation.ChangeThemeNavigation
import com.jeckonly.changetheme.navigation.changeThemeGraph
import com.jeckonly.chart.navigation.chartGraph
import com.jeckonly.choosetype.navigation.ChooseTypeNavigation
import com.jeckonly.choosetype.navigation.chooseTypeGraph
import com.jeckonly.home.navigation.HomeDestination
import com.jeckonly.home.navigation.homesGraph
import com.jeckonly.more.navigation.moreGraph
import com.jeckonly.recorddetail.navigation.RecordDetailNavigation
import com.jeckonly.recorddetail.navigation.recordDetailGraph
import com.jeckonly.updatetype.navigation.UpdateTypeNavigation
import com.jeckonly.updatetype.navigation.updateTypeGraph

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
        homesGraph {
            RecordDetailNavigation.navigate(navController, RecordDetailNavigation.buildRoute(it))
        }
        chartGraph()
        moreGraph(
            toChangeThemeScreen = { ChangeThemeNavigation.navigate(navController) },
            toChangeLangScreen = { ChangeLangNavigation.navigate(navController) }
        )
        changeThemeGraph()
        changeLangGraph()
        chooseTypeGraph(
            onCLickSetting = { UpdateTypeNavigation.navigate(navController) },
            popBackStack = { navController.popBackStack() }
        )
        updateTypeGraph {
            AddTypeNavigation.navigate(navController, AddTypeNavigation.buildRoute(it))
        }
        addTypeGraph(onClickBack = {
            navController.popBackStack()
        }, onFinish = {
            navController.popBackStack()
        })
        recordDetailGraph(
            toEditRecord = {
                ChooseTypeNavigation.navigate(navController, ChooseTypeNavigation.buildRoute(it))
            },
            popBackStack = { navController.popBackStack() }
        )
    }
}
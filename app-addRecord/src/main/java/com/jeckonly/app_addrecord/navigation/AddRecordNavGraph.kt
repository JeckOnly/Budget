package com.jeckonly.app_addrecord.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jeckonly.choosetype.navigation.ChooseTypeNavigation
import com.jeckonly.choosetype.navigation.chooseTypeGraph

@Composable
fun AddRecordNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = ChooseTypeNavigation.route,
        modifier = modifier
    ) {
       chooseTypeGraph()
    }
}
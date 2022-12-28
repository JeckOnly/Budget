package com.jeckonly.navigation

import androidx.navigation.NavHostController

interface BgtBottomNavigationDestination: BgtTopLevelNavigationDestination {
    override fun navigate(navController: NavHostController, route: String) {
        bottomNavigationDestinationNavigate(navController, route)
    }
}

fun bottomNavigationDestinationNavigate(navController: NavHostController, route: String) {
    val currentDestination = navController.currentDestination?.route

    navController.navigate(route){
        launchSingleTop = true
        // 避免按返回键在底部导航栏来回切换
        currentDestination?.let {
            popUpTo(it) {
                inclusive = true
            }
        }
    }
}
/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jeckonly.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController

/**
 * Interface for describing the Now in Android navigation destinations
 */

interface BgtTopLevelNavigationDestination {
    /**
     * Defines a specific route this destination belongs to.
     * Route is a String that defines the path to your composable.
     * You can think of it as an implicit deep link that leads to a specific destination.
     * Each destination should have a unique route.
     */
    val route: String

    /**
     * Defines a specific destination ID.
     * This is needed when using nested graphs via the navigation DLS, to differentiate a specific
     * destination's route from the route of the entire nested graph it belongs to.
     */
    val destination: String

    /**
     * 抽象出来的导航逻辑
     */
    fun navigate(navController: NavHostController, route: String = this.route) {
        navController.navigate(route)
    }

    /**
     * 抽象出来的判断逻辑
     */
    fun isSelected(currentDestination: NavDestination?): Boolean {
        return topLevelDestinationIsSelected(currentDestination, route)
    }
}

fun topLevelDestinationNavigate(navController: NavHostController, route: String) {
    navController.navigate(route)
}

fun topLevelDestinationIsSelected(currentDestination: NavDestination?, route: String): Boolean {
    return currentDestination?.hierarchy?.any { it.route == route } == true
}

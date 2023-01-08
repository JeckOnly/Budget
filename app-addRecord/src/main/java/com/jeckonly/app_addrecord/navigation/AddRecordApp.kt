package com.jeckonly.app_addrecord.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jeckonly.app_addrecord.AddRecordViewModel
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.theme.BudgetTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddRecordApp(
    viewModel: AddRecordViewModel = hiltViewModel()
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

            Box(modifier = Mdf.fillMaxSize()) {
                AddRecordNavGraph(navController = navController, modifier = Mdf.fillMaxSize())
            }
        }
    }
}
package com.jeckonly.app_addrecord.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.theme.BudgetTheme

@Composable
fun AddRecordApp() {

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
                AddRecordNavGraph(navController = navController, modifier = Mdf.fillMaxSize())
            }
        }
    }
}
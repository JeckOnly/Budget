package com.jeckonly.budget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 兼容Android12的启动屏
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}


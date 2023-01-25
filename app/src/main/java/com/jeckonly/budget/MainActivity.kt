package com.jeckonly.budget

import android.os.Bundle
import android.transition.Fade
import android.view.Window
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.jeckonly.budget.navigation.BgtApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 兼容Android12的启动屏
        installSplashScreen()
        // 沉浸式状态栏
        WindowCompat.setDecorFitsSystemWindows(window, false)
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            allowEnterTransitionOverlap = true
            val fadeAnimation = Fade()
            enterTransition = fadeAnimation
            exitTransition = fadeAnimation
        }
        super.onCreate(savedInstanceState)
        setContent {
            BgtApp()
        }
    }
}


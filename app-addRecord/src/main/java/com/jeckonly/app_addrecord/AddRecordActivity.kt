package com.jeckonly.app_addrecord

import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.jeckonly.app_addrecord.navigation.AddRecordApp

const val SLIDE_DURATION = 250L

class AddRecordActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 沉浸式状态栏
        WindowCompat.setDecorFitsSystemWindows(window, false)
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            allowEnterTransitionOverlap = true
            val slideAnimation = Slide(Gravity.BOTTOM).apply {
                duration = SLIDE_DURATION
            }
            enterTransition = slideAnimation
            exitTransition = slideAnimation
        }
        super.onCreate(savedInstanceState)
        setContent {
            AddRecordApp()
        }
    }
}
package com.jeckonly.budget

import android.app.Application
import com.jeckonly.util.FileClassMethodTag
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BgtApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        // Timber注册
        if (BuildConfig.DEBUG) {
            Timber.plant(FileClassMethodTag())
        }
    }
}
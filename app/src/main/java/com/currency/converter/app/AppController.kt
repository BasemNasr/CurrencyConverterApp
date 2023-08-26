package com.currency.converter.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp



@HiltAndroidApp
class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(applicationContext)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
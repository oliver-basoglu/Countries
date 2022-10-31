package com.oliver.countries

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CountryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        }
    }
}

package org.jayhsu.rfhub

import android.app.Application
import timber.log.Timber

class RfHubApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
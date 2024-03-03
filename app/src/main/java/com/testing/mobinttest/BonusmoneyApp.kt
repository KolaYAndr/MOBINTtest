package com.testing.mobinttest

import android.app.Application
import com.testing.mobinttest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BonusmoneyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BonusmoneyApp)
            modules(appModule)
        }
    }
}
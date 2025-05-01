package com.aamir.compose.eBooksLibrary.app

import android.app.Application
import com.aamir.compose.eBooksLibrary.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppClass)
            modules(appModule)
        }
    }
}
package com.aamir.compose.eBooksLibrary.app

import android.app.Application
import android.preference.PreferenceManager
import com.aamir.compose.eBooksLibrary.data.di.dataModule
import com.aamir.compose.eBooksLibrary.di.appModule
import com.aamir.compose.eBooksLibrary.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.osmdroid.config.Configuration

class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        startKoin {
            androidContext(this@AppClass)
            modules(
                appModule,
                dataModule,
                domainModule
            )
        }
    }
}
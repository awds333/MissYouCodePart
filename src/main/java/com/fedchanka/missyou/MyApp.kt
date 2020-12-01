package com.fedchanka.missyou

import android.app.Application
import com.fedchanka.missyou.di.*
import com.google.firebase.functions.FirebaseFunctions
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.LOCAL) {
            FirebaseFunctions.getInstance().useEmulator("10.0.2.2", 5001)
        }
        startKoin {
            androidContext(this@MyApp)
            modules(viewModel, loginAction, service, navigation, uiUtils)
        }
    }
}
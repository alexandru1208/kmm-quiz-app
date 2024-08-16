package com.mcg.trivia

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent

class TriviaApp : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@TriviaApp)
            modules(androidModule())
        }
    }

}
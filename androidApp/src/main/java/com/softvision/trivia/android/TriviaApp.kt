package com.softvision.trivia.android

import android.app.Application
import com.softvision.trivia.initKoin
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
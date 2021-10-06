package ru.vdv.myapp.mydictionary

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.vdv.myapp.mydictionary.di.Di.allModules

class DictionaryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DictionaryApplication)
            modules(allModules)
        }
    }
}
package com.thescore.proficiencyexercise

import android.app.Application
import com.thescore.proficiencyexercise.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


/**
 * Purpose: Application class which is used for the timber logging, koin starting for dependencies etc.
 */
open class ProficiencyApp : Application() {
    val appComponent = listOf(appModule,networkModule,retrofitModule,utilModule)

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@ProficiencyApp)
            modules(provideDependency())
        }
    }


    companion object {
        lateinit var instance: ProficiencyApp
    }

    open fun provideDependency() = appComponent
}
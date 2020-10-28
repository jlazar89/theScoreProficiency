package com.thescore.proficiencyexercise.di

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.thescore.proficiencyexercise.data.prefs.AppPrefsHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created Date: 10/27/2020
 * Purpose: KOIN Module for providing App Utility related dependencies.
 */

val utilModule = module {

    single { provideSharedPreference(androidContext()) }
}

/**
 * Created Date: 10/27/2020
 * Purpose: Function provides the shared preference instance.
 */

fun provideSharedPreference(context: Context) = AppPrefsHelper(context)
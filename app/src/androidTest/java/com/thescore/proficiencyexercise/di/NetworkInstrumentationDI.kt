package com.thescore.proficiencyexercise.di

import com.google.gson.GsonBuilder
import com.thescore.proficiencyexercise.BuildConfig
import com.thescore.proficiencyexercise.data.datasources.api.TeamService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit Koin DI component for Instrumentation Testing
 */
fun configureNetworkForInstrumentationTest(baseTestApi: String) = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
    factory{ get<Retrofit>().create(TeamService::class.java) }
}


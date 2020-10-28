package com.thescore.proficiencyexercise.di

import com.thescore.proficiencyexercise.BuildConfig
import com.thescore.proficiencyexercise.data.datasources.api.TeamService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created Date: 10/27/2020
 * Purpose: KOIN Module provides retrofit related services which can
 * provides the various required API Interfaces.
 */
val retrofitModule = module {

    single { provideRetrofit(get(), get()) }
    single { provideTeamService(get()) }

}

/**
 * Created By: dev1618
 * Created Date: 6/19/2019
 * Purpose: Function to provide retrofit instance.
 */
fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .baseUrl(BuildConfig.ENDPOINT)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

// GET THE INSTANCE OF THE TEAM SERVICE
fun provideTeamService(retrofit: Retrofit): TeamService = retrofit.create(TeamService::class.java)
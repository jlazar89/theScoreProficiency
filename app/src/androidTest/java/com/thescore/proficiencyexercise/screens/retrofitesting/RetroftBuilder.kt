package com.thescore.proficiencyexercise.screens.retrofitesting

import com.thescore.proficiencyexercise.data.datasources.api.TeamService
import com.thescore.proficiencyexercise.test.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: TeamService = getRetrofit().create(TeamService::class.java)

}
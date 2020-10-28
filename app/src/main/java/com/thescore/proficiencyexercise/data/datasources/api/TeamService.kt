package com.thescore.proficiencyexercise.data.datasources.api

import com.thescore.proficiencyexercise.data.entity.TeamResponse
import retrofit2.http.GET

/**
 * Created Date: 10/27/2020
 * Purpose: Interface for the APIs of Team List Module.
 */
interface TeamService {

    /**
     * Created Date: 10/27/2020
     * Purpose: Function to get all the Teams list which are available on server.
     *
     * @return TeamResponse
     */
    @GET("input.json")
    suspend fun getAllTeamASync(): MutableList<TeamResponse>
}
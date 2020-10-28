package com.thescore.proficiencyexercise.repository

import com.thescore.proficiencyexercise.data.entity.TeamResponse
import com.thescore.proficiencyexercise.data.datasources.api.TeamService

/**
 * Created Date: 10/27/2020
 * Purpose: Repository Class for managing the Teams related data, repository is responsible
 * for providing the data to viewmodel from local database, network or cached data.
 */
class TeamListRepository(
    private val teamService: TeamService
) {
    /**
     * Created Date: 10/27/2020
     * Purpose: This function will return all the Teams stored on local database.
     */
    suspend fun getAllTeams(): MutableList<TeamResponse> {
        return teamService.getAllTeamASync()
    }
}
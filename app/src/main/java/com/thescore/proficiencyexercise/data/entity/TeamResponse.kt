package com.thescore.proficiencyexercise.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created Date: 10/27/2020
 * Purpose: Class to handle the data which we received from server for Teams.
 */
@Parcelize
data class TeamResponse(
    val full_name: String,
    val id: Int,
    val losses: Int,
    val players: ArrayList<Player>,
    val wins: Int
) : Parcelable {
    @Parcelize
    data class Player(
        val first_name: String,
        val id: Int,
        val last_name: String,
        val number: Int,
        val position: String
    ) : Parcelable
}
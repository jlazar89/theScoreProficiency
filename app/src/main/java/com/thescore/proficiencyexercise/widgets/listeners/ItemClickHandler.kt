package com.thescore.proficiencyexercise.widgets.listeners

import android.view.View
import com.thescore.proficiencyexercise.data.entity.TeamResponse

/**
* Purpose: Interface to handle the item click of recyclerview or any other view
*/
interface ItemClickHandler {
    fun onItemClick(view: View, item: TeamResponse)
}
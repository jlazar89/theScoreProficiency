package com.thescore.proficiencyexercise.widgets.recycler.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created Date: 10/27/2020
 * Purpose: Base holder for all the recycler view holders.
 */
abstract class BaseHolder<M>(itemView: View?) : RecyclerView.ViewHolder(
    itemView!!
)
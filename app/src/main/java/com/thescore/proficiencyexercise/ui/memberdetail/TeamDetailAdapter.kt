package com.thescore.proficiencyexercise.ui.memberdetail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thescore.proficiencyexercise.R
import com.thescore.proficiencyexercise.data.entity.TeamResponse.Player
import com.thescore.proficiencyexercise.databinding.ItemFooterProgressRowBinding
import com.thescore.proficiencyexercise.databinding.ItemTeamDetailRowBinding

import com.thescore.proficiencyexercise.utils.extensions.autoNotify
import com.thescore.proficiencyexercise.utils.extensions.inflate
import com.thescore.proficiencyexercise.widgets.recycler.adapter.RecyclerBaseAdapter
import com.thescore.proficiencyexercise.widgets.recycler.adapter.RecyclerListAdapter
import com.thescore.proficiencyexercise.widgets.recycler.holder.BaseHolder
import com.thescore.proficiencyexercise.widgets.recycler.holder.SimpleHolder
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TeamDetailAdapter:
    RecyclerListAdapter<Player, RecyclerView.ViewHolder>(),
    CoroutineScope {

    private val parentJob = Job()
    override val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RecyclerBaseAdapter.FOOTER -> {
                val binding = parent.inflate<ItemFooterProgressRowBinding>(R.layout.item_footer_progress_row)
                SimpleHolder(binding.root)
            }
            else -> {
                val binding = parent.inflate<ItemTeamDetailRowBinding>(R.layout.item_team_detail_row)
                TeamDetailViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, model: Player?, position: Int) {
        // set the data item to TeamDetailViewHolder class data item instance.
        (holder as? TeamDetailViewHolder)?.binding?.apply {
            dataItem = model
        }

    }

    /**
     * Purpose: This function will set the data to recyclerview using diffutils.
     */
    fun setTeamData(trans: List<Player>) {
        if (list == null) {
            list = ArrayList()
        }

        launch {
            val diffResult: DiffUtil.DiffResult = withContext(Dispatchers.IO) {
                autoNotify(list, trans) { o, n -> o.id == n.id }
            }

            diffResult.dispatchUpdatesTo(this@TeamDetailAdapter)
            list = trans
        }
    }


    // ViewHolder class for the Team Detail.
    inner class TeamDetailViewHolder(val binding: ItemTeamDetailRowBinding) :
        BaseHolder<Player>(binding.root)
}
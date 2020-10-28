package com.thescore.proficiencyexercise.utils.extensions

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Created Date: 27-08-2020
 * Purpose: Extension function allow diffutils to autonotify for the adapter.
 */
fun <T> RecyclerView.Adapter<*>.autoNotify(
    oldList: List<T>,
    newList: List<T>,
    compare: (T, T) -> Boolean
) : DiffUtil.DiffResult {

    return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return compare(oldList[oldItemPosition], newList[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

    }, true)

}
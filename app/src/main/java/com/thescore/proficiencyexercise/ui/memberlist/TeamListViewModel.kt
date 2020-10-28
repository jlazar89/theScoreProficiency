package com.thescore.proficiencyexercise.ui.memberlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thescore.proficiencyexercise.data.entity.TeamResponse
import com.thescore.proficiencyexercise.repository.TeamListRepository
import com.thescore.proficiencyexercise.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created Date: 10/27/2020
 * Purpose: TeamListViewModel is associated with TeamListFragment class which has TeamListRepository
 * instance provided by the koin and having functions to get teams list
 */
class TeamListViewModel(
    private val teamListRepository: TeamListRepository?
) : RxViewModel() {
    private var mListData: MutableLiveData<MutableList<TeamResponse>>? = null

    fun loadList(tag: Int) {
        launch {
            // Loading state
            mState.value = LoadingState()
            try {
                withContext(Dispatchers.IO) {

                    // Get records per page from server.
                    val response = teamListRepository?.getAllTeams()

                    //Sort in alphabetical order
                    response?.sortBy { it.full_name }

                    // Set the data based on various tags.
                    when (tag) {
                        Constants.Tag.LIST -> {
                            mListData?.postValue(response?.toMutableList())
                        }
                        else -> {

                        }
                    }
                }

                // If no List data then set the empty state else set the success state.
                if (mListData?.value.isNullOrEmpty()) {
                    mState.value = EmptyState(tag)
                } else {
                    mState.value = SuccessState(tag)
                }
            } catch (e: Throwable) {
                // if any error to get the data then set the errorstate.
                mState.value = ErrorState(tag, e)
            }
        }
    }


    /**
     * Function returns the Team list
     */
    fun getTeamList(): LiveData<MutableList<TeamResponse>> {
        if (mListData == null) {
            mListData = MutableLiveData()
        }
        return mListData as MutableLiveData<MutableList<TeamResponse>>
    }

    /**
     * Function used for sorting Teams list by wins in descending order,
     */
    fun sortBy(sortBy: Int) {
        launch {
            // Loading state
            mState.value = LoadingState()
            try {
                var list = ArrayList(mListData?.value!!)

                when (sortBy) {
                    0 -> {
                        list.sortBy{ team -> team.full_name }
                    }
                    1 -> {
                        list.sortByDescending { team -> team.full_name }
                    }
                    2 -> {
                        list.sortBy { team -> team.wins }
                    }
                    3 -> {
                        list.sortByDescending{ team -> team.wins }
                    }
                    4 -> {
                        list.sortBy { team -> team.losses }
                    }
                    5 -> {
                        list.sortByDescending { team -> team.losses }
                    }
                }

                // update the live data .
                mListData?.value = list.toMutableList()

                // set the state to success state.
                mState.value = SuccessState()


            } catch (e: Throwable) {
                // If any error, set the ErrorState along with error Message.
                mState.value = ErrorState("remove", throwable = e)
            }
        }
    }
}

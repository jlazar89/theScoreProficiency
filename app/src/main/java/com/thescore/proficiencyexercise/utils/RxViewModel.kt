package com.thescore.proficiencyexercise.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * Created Date: 10/27/2020
 * Purpose:  ViewModel for Coroutines Jobs
 *
 * launch() - launch a Rx request
 * clear all request on stop
 */
abstract class RxViewModel : ViewModel(), CoroutineScope {

    val mState = MutableLiveData<State>()

     private val parentJob = Job()

    override val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    override fun onCleared() {
        Timber.e("VIEW MODEL CLEARED $this")
        super.onCleared()
        parentJob.cancel()
    }
}
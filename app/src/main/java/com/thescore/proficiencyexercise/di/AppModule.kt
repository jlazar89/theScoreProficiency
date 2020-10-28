package com.thescore.proficiencyexercise.di

import com.thescore.proficiencyexercise.repository.TeamListRepository
import com.thescore.proficiencyexercise.ui.memberlist.TeamListViewModel

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created Date: 10/27/2020
 * Purpose: KOIN module to manage app level dependencies.
 */
val appModule = module {
    /**
     *  Here we list all the view models which are injected.
     */

    viewModel { TeamListViewModel( get()) }

    single { TeamListRepository(get()) }
}

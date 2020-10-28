package com.thescore.proficiencyexercise.app

import com.thescore.proficiencyexercise.ProficiencyApp
import org.koin.core.module.Module

/**
 * Helps to configure required dependencies for Instru Tests.
 * Method provideDependency can be overrided and new dependencies can be supplied.
 */
class TestMainApp : ProficiencyApp() {
    override fun provideDependency() = listOf<Module>()
}
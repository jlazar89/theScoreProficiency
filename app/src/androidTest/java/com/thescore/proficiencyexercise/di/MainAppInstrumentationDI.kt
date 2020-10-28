package com.thescore.proficiencyexercise.di

/**
 * Main Koin DI component for Instrumentation Testing
 */
fun generateTestAppComponent(baseApi: String)
        = listOf(
    configureNetworkForInstrumentationTest(baseApi),
    MockWebServerInstrumentationTest,
)
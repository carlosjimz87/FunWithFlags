package com.carlosjimz87.funwithflags.utils

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FormatterTests {
    private val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun formatPopulationTest() {
        assertEquals("168", formatPopulation(168, appContext))
        assertEquals("1.7 k", formatPopulation(1680, appContext))
        assertEquals("168.0 k", formatPopulation(168000, appContext))
        assertEquals("168.7 M", formatPopulation(168700000, appContext))
        assertEquals("168.0 B", formatPopulation(168000000000, appContext))
    }


    @Test
    fun formatCurrencyTest() {
        assertEquals("EUR (€)", formatCurrency("EUR", "€", appContext).second)
        assertEquals("GBP (£)", formatCurrency("GBP", "£", appContext).second)
        assertEquals("USD ($)", formatCurrency("USD", "$", appContext).second)
        assertEquals("ARS", formatCurrency("ARS", null, appContext).second)
    }
}
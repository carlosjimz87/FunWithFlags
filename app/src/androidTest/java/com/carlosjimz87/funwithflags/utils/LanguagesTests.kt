package com.carlosjimz87.funwithflags.utils

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class LanguagesTests {
    private val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun testModifyLocale() {
        val locales = listOf("en", "es", "de", "it", "fr", "pt", "fa", "ja")
        for (newLocale in locales) {
            setLocale(appContext, newLocale)
            val currentLocale = getLocale(appContext)
            Assert.assertEquals(newLocale, currentLocale)
        }
    }
}
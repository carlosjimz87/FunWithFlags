package com.carlosjimz87.funwithflags.adapters


import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.models.Translations
import com.carlosjimz87.funwithflags.utils.Languages
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NameAdapterTests {
    private val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private val country = Country(
        code = "ESP",
        name = "Spain",
        flag = "flag",

        translations = Translations(
            de = "Spanien",
            es = "España",
            fr = "Espagne",
            ja = "スペイン",
            it = "Spagna",
            fa = "اسپانیا",
            pt = "Espanha"
        )
    )

    @Test
    fun getTranslatedNameTest() {
        val locales = listOf("en", "es", "de", "it", "fr", "pt", "fa", "ja")
        val names =
            listOf("Spain", "España", "Spanien", "Spagna", "Espagne", "Espanha", "اسپانیا", "スペイン")
        for (pair in locales.zip(names)) {
            Languages.setLocale(appContext, pair.first)
            val name = NameAdapter.getTranslatedName(country, appContext)
            Assert.assertEquals(pair.second, name)
        }
    }
}
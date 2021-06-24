package com.carlosjimz87.funwithflags

import com.carlosjimz87.funwithflags.utils.justify
import org.junit.Assert
import org.junit.Test


class ExtensionsTest {
    @Test
    fun testJustifyString() {
        val text = "SPAIN SPAIN (VR) SPAIN SPAIN SPAIN"
        val expectedText = "SPAIN SPAIN (VR)\nSPAIN SPAIN SPAIN"
        Assert.assertEquals(expectedText, text.justify())
    }
}
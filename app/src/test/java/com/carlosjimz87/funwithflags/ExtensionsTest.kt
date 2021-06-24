package com.carlosjimz87.funwithflags

import com.carlosjimz87.funwithflags.utils.justify
import org.junit.Assert
import org.junit.Test


class ExtensionsTest {
    @Test
    fun testJustifyString() {
        val text = "SPAIN FRANCE (VR) USA POLAND PORTUGAL"
        val expectedText = "SPAIN FRANCE (VR)\nUSA POLAND PORTUGAL"
        Assert.assertEquals(expectedText, text.justify())
    }
}
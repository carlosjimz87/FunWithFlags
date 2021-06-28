package com.carlosjimz87.funwithflags.db.converters

import androidx.test.filters.SmallTest
import org.junit.Assert
import org.junit.Test

@SmallTest
class StringListConverterTest{

    @Test
    fun toListTest(){
        val str = "1,2,3,4"
        Assert.assertEquals(listOf("1","2","3","4"),StringListConverter.toList(str))
    }

    @Test
    fun toStringTest(){
        val list = listOf("1","2","3","4")
        Assert.assertEquals("1,2,3,4",StringListConverter.fromList(list))
    }
}
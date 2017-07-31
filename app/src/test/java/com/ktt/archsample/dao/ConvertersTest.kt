package com.ktt.archsample.dao

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * @author luke_kao
 */
class ConvertersTest {
    @Test
    fun fromTimestamp() {
        val converters = Converters()
        Assert.assertNull(converters.fromTimestamp(null))

        val date = Date()
        val convertedDate = converters.fromTimestamp(date.time)
        Assert.assertEquals(date.time, convertedDate?.time)
    }

    @Test
    fun dateToTimestamp() {
        val converters = Converters()
        Assert.assertNull(converters.dateToTimestamp(null))

        val date = Date()
        val convertedTime = converters.dateToTimestamp(date)
        Assert.assertEquals(date.time, convertedTime)
    }

}
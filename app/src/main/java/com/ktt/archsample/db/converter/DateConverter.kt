package com.ktt.archsample.db.converter

import androidx.room.TypeConverter
import java.util.*

/**
 * @author luke_kao
 */
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(value: Date?): Long? {
        return value?.time
    }
}
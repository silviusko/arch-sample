package com.ktt.archsample.dao

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * @author luke_kao
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(value: Date?): Long? {
        return value?.time
    }
}
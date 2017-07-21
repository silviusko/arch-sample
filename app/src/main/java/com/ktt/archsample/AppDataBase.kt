package com.ktt.archsample

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

/**
 * @author luke_kao
 */
@Database(entities = arrayOf(Record::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun recordDao(): RecordDao
}
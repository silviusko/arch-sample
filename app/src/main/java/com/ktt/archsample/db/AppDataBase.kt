package com.ktt.archsample.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.ktt.archsample.db.converter.DateConverter
import com.ktt.archsample.db.dao.RecordDao
import com.ktt.archsample.db.entity.Record

/**
 * @author luke_kao
 */
@Database(entities = arrayOf(Record::class), version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun recordDao(): RecordDao
}
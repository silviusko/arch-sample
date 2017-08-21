package com.ktt.archsample.dao

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * @author luke_kao
 */
@Entity(tableName = "records")
class Record(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "latest_time") var time: Date = Date(),
        var value: Int = 0
)
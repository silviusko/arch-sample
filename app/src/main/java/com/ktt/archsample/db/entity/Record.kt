package com.ktt.archsample.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
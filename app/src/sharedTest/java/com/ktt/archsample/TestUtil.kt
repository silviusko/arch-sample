package com.ktt.archsample

import com.ktt.archsample.db.entity.Record
import java.util.*

/**
 * @author luke_kao
 */
class TestUtil {
    companion object {
        private val random = Random()

        fun newRecord(): Record {
            val record = Record()
            record.value = random.nextInt()
            return record
        }

        fun newRecords(num: Int): List<Record> {
            val records = List(num, {
                val record = Record()
                record.value = random.nextInt(1000)
                record
            })

            return records
        }
    }
}
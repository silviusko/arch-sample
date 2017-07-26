package com.ktt.archsample.dao

import android.arch.persistence.room.*

/**
 * @author luke_kao
 */
@Dao
interface RecordDao {
    @Query("SELECT * FROM records")
    fun load(): List<Record>

    // replace the :arg0 to the :id
    @Query("SELECT * FROM records WHERE id = :arg0 LIMIT 1")
    fun loadById(id: Int): Record

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(record: Record)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(records: List<Record>)

    @Update
    fun update(vararg records: Record)

    @Delete
    fun delete(record: Record)
}
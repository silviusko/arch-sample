package com.ktt.archsample.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.ktt.archsample.db.entity.Record

/**
 * @author luke_kao
 */
@Dao
interface RecordDao {
    @Query("SELECT * FROM records")
    fun load(): List<Record>

    @Query("SELECT * FROM records")
    fun loadAsync(): LiveData<List<Record>>

    // replace the :arg0 to the :id
    @Query("SELECT * FROM records WHERE id = :arg0 LIMIT 1")
    fun loadById(id: Int): Record?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(record: Record)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(records: List<Record>)

    @Update
    fun update(record: Record)

    @Update
    fun update(records: List<Record>)

    @Delete
    fun delete(record: Record)
}
package com.ktt.archsample

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * @author luke_kao
 */
@Dao
interface RecordDao {
    @Query("SELECT * FROM records")
    fun load(): LiveData<List<Record>>

    // replace the :arg0 to the :id
    @Query("SELECT * FROM records WHERE id = :arg0 LIMIT 1")
    fun loadById(id: Int): Record

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg records: Record)

    @Update
    fun update(vararg records: Record)

    @Delete
    fun delete(record: Record)
}
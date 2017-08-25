package com.ktt.archsample.db

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author luke_kao
 */
open class DatabaseCreator private constructor() {
    companion object {
        val instance = DatabaseCreator()
    }

    lateinit var database: AppDataBase
        get
        private set

    private val isDbCreated = MutableLiveData<Boolean>()
    private val isInitialized = AtomicBoolean(false)

    fun create(context: Context) {
        if (!isInitialized.compareAndSet(false, true)) return

        isDbCreated.value = false

        Thread({
            database = Room.databaseBuilder(context.applicationContext!!, AppDataBase::class.java, "arch_db")
                    .build()

            isDbCreated.postValue(true)
        }).start()
    }

    fun isCreated(): LiveData<Boolean> {
        return isDbCreated
    }
}
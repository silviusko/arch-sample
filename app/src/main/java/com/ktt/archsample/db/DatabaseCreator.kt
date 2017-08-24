package com.ktt.archsample.db

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author luke_kao
 */
open class DatabaseCreator {
    private object Holder {
        val INSTANCE = DatabaseCreator()
    }

    companion object {
        val instance: DatabaseCreator by lazy { Holder.INSTANCE }
    }

    lateinit var database: AppDataBase
        get
        private set

    private val isDbCreated = MutableLiveData<Boolean>()
    private var isInitialized = AtomicBoolean(false)

    fun init(context: Context) {
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
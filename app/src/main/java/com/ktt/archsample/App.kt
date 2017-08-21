package com.ktt.archsample

import android.app.Application
import android.arch.persistence.room.Room
import com.ktt.archsample.dao.AppDataBase
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @author luke_kao
 */
class App : Application() {
    companion object {
        var dataBase: AppDataBase? = null
            get
            private set
        var executor: Executor? = null
            get
            private set
    }

    override fun onCreate() {
        super.onCreate()

        dataBase = Room.databaseBuilder(applicationContext, AppDataBase::class.java, "arch_db").build()
        executor = Executors.newFixedThreadPool(5)
    }
}
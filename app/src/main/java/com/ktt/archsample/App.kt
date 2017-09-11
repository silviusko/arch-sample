package com.ktt.archsample

import android.app.Application

/**
 * @author luke_kao
 */
class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule())
                .build()
    }
}
package com.ktt.archsample

import android.app.Application

/**
 * @author luke_kao
 */
class App : Application() {
    companion object {
        var INSTANCE: App? = null
            get() {
                synchronized(this) {
                    return field
                }
            }
            private set(value) {
                synchronized(this) {
                    field = value
                }
            }

        fun appComponent() = INSTANCE!!.component
    }

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        component = DaggerAppComponent.builder()
                .appModule(AppModule())
                .build()
    }
}
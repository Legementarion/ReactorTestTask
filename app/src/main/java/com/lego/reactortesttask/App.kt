package com.lego.reactortesttask

import android.app.Application
import android.content.Context
import com.lego.reactortesttask.di.appModule
import org.koin.android.ext.android.startKoin
import org.koin.core.Koin
import org.koin.log.EmptyLogger

class App : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
        fun applicationContext(): Context = instance.applicationContext
    }


    override fun onCreate() {
        super.onCreate()

        initKoin()
    }


    private fun initKoin() {
        startKoin(this, appModule)
        Koin.logger = EmptyLogger()
    }

}
package ru.juraogurcov.multitool

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class AppContext:Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this.applicationContext
    }
    companion object{
        private lateinit var instance: Context
        fun getAppContext() = instance
    }
}
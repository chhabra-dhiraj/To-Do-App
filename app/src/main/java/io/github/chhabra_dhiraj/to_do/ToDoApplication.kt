package io.github.chhabra_dhiraj.to_do

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree


class ToDoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }

}
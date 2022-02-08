package com.example.taskmanagement

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class CustomApplication :Application(){
    override fun onCreate(){
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(config)
    }
}
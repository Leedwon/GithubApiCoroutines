package com.ledwon.jakub.githubapicoroutines

import android.app.Application
import com.ledwon.jakub.githubapicoroutines.network.networkModules
import com.ledwon.jakub.githubapicoroutines.view.query.queryModule
import com.ledwon.jakub.githubapicoroutines.view.repo_list.reposModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                networkModules,
                queryModule,
                reposModule
            )
        }
    }
}
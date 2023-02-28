package com.alacrity.randomUsers

import android.app.Application
import com.alacrity.randomUsers.di.ApiModule
import com.alacrity.randomUsers.di.AppComponent
import com.alacrity.randomUsers.di.AppModule
import com.alacrity.randomUsers.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent
            .builder()
            .apiModule(ApiModule(BuildConfig.BASE_URL))
            .appModule(AppModule(this))
            .build()
            .apply { inject(this@App) }
    }
    companion object {
        lateinit var appComponent: AppComponent
    }

}
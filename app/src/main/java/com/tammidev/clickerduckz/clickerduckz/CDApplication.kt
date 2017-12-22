package com.tammidev.clickerduckz.clickerduckz

import android.app.Application

class CDApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .gameModule(GameModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    fun getAppComponent(): AppComponent {
        return component;
    }
}

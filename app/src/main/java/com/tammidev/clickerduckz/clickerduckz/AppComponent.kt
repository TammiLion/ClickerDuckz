package com.tammidev.clickerduckz.clickerduckz

import dagger.Component
import javax.inject.Singleton

/**
 * Created by troep on 9/24/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, GameModule::class, ViewModelFactoryModule::class))
interface AppComponent {
    fun inject(application: CDApplication)
    fun inject(activity: MainActivity)
    fun inject(viewModel: MainViewModel)
}
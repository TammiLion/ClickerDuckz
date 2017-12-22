package com.tammidev.clickerduckz.clickerduckz

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(eggsController: EggsController) = MainViewModelFactory(eggsController)

}
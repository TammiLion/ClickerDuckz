package com.tammidev.clickerduckz.clickerduckz

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by troep on 9/24/17.
 */
@Module
class GameModule {
    @Provides
    @Singleton
    fun provideGameController() = GameController()

    @Provides
    @Singleton
    fun provideEggsController(gameController: GameController) = EggsController(gameController)
}
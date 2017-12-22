package com.tammidev.clickerduckz.clickerduckz

import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

const val EGGS_MILLISECONDS = 800

class EggsController @Inject constructor(var gameController: GameController) {
    val eggs: BehaviorSubject<Int> = BehaviorSubject.createDefault(1)
    private var lastTimeStamp: Long = System.currentTimeMillis()
    private var timePassed: Long = 0

    init {
        gameController.timer.timeInterval()
                .subscribe({ t ->
                    timePassed += System.currentTimeMillis() - lastTimeStamp
                    lastTimeStamp = System.currentTimeMillis()
                    val eggsGenerated = eggsGenerated(timePassed)
                    if (eggsGenerated > 0) {
                        timePassed = timePassed % EGGS_MILLISECONDS;
                        eggs.onNext(eggs.value + eggsGenerated)
                    }
                })
    }

    fun eggsGenerated(timePassedMilliSeconds: Long): Int {
        return ((timePassedMilliSeconds / EGGS_MILLISECONDS) * (gameController.ducks.value / 2)).toInt()
    }

    fun onHatchBtnClicked() {
        gameController.ducks.onNext(gameController.ducks.value + eggs.value)
        eggs.onNext(0)
    }
}
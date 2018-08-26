package com.tammidev.clickerduckz.clickerduckz

import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GameController @Inject constructor() {

    val timer = Observable.interval(60, TimeUnit.MILLISECONDS)
    private var lastTimeStamp: Long = System.currentTimeMillis()

    init {
        timer.timeInterval()
                .subscribe { t ->
                    val timePassed: Long = System.currentTimeMillis() - lastTimeStamp
                    lastTimeStamp = System.currentTimeMillis()
                }
    }
}
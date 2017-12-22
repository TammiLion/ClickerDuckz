package com.tammidev.clickerduckz.clickerduckz

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GameController @Inject constructor() {

    val ducks: BehaviorSubject<Int> = BehaviorSubject.createDefault(2)

    val timer = Observable.interval(50, TimeUnit.MILLISECONDS)
    private var lastTimeStamp: Long = System.currentTimeMillis()

    init {
        timer.timeInterval()
                .subscribe({ t ->
                    val timePassed: Long = System.currentTimeMillis() - lastTimeStamp
                    lastTimeStamp = System.currentTimeMillis()
                })
    }
}
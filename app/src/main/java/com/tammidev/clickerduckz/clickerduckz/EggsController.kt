package com.tammidev.clickerduckz.clickerduckz

import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

const val EGGS_PRODUCE_TIME: Double = 4000.0
const val DUCKLINGS_MATURE_TIME: Double = 6000.0

class EggsController @Inject constructor(gameController: GameController) {
    private var eggs: Int = 0
    private var ducklings: Int = 0
    private var ducks: Int = 2

    val eggsObservable: BehaviorSubject<Int> = BehaviorSubject.createDefault(eggs)
    val ducklingsObservable: BehaviorSubject<Int> = BehaviorSubject.createDefault(ducklings)
    val ducksObservable: BehaviorSubject<Int> = BehaviorSubject.createDefault(ducks)

    private var lastTimeStamp: Long = System.currentTimeMillis()
    private var timePassedForEggs: Long = 0
    private var timePassedForDucklings: Long = 0

    init {
        gameController.timer.timeInterval()
                .subscribe({ t ->
                    val timePassed = System.currentTimeMillis() - lastTimeStamp
                    timePassedForEggs += timePassed
                    timePassedForDucklings += timePassed
                    lastTimeStamp = System.currentTimeMillis()
                    processDucksLayingEggs()
                    processDucklingsMaturing()
                })
    }

    private fun processDucklingsMaturing() {
        if (ducklings == 0) {
            timePassedForDucklings = 0
            return
        }
        val newDucks = ducklingsMatured(timePassedForDucklings)
        if (newDucks > 0) {
            ducks += newDucks
            ducklings -= newDucks
            timePassedForDucklings = 0
            ducklingsObservable.onNext(ducklings)
            ducksObservable.onNext(ducks)
        }
    }

    private fun processDucksLayingEggs() {
        if (ducks == 0) {
            timePassedForEggs = 0
            return
        }
        val eggsGenerated = eggsGenerated(timePassedForEggs)
        if (eggsGenerated > 0) {
            eggs += eggsGenerated
            timePassedForEggs = 0
            eggsObservable.onNext(eggs)
        }
    }

    private fun ducklingsMatured(timePassedMilliSeconds: Long): Int {
        val percentage: Double = percentageOfProduceTime(timePassedMilliSeconds, DUCKLINGS_MATURE_TIME)
        return (ducklings * percentage).toInt()
    }

    private fun percentageOfProduceTime(timePassedInMillis: Long, produceTimeInMillis: Double): Double {
        return timePassedInMillis.toDouble() / produceTimeInMillis
    }

    fun eggsGenerated(timePassedInMillis: Long): Int {
        val percentage: Double = percentageOfProduceTime(timePassedInMillis, EGGS_PRODUCE_TIME)
        val total: Double = ducks.toDouble() / 2.0
        return (total * percentage).toInt()
    }

    fun onHatchBtnClicked() {
        ducklings += eggs
        eggs = 0
        eggsObservable.onNext(eggs)
        ducklingsObservable.onNext(ducklings)
    }
}
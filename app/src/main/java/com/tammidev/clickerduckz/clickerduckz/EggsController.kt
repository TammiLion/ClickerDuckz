package com.tammidev.clickerduckz.clickerduckz

import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class EggsController @Inject constructor(gameController: GameController) {

    private var resources: MutableList<Resource> = mutableListOf(Resource("Eggs", "Hatch"))
    private var lastTimeStamp: Long = System.currentTimeMillis()

    val observable: BehaviorSubject<List<Resource>> = BehaviorSubject.create()

    init {
        gameController.timer.timeInterval()
                .subscribe { _ ->
                    val timePassed = System.currentTimeMillis() - lastTimeStamp
                    resources.filter { it.isProducing }.forEach { it.timePassed(timePassed) }
                    observable.onNext(resources)
                    lastTimeStamp = System.currentTimeMillis()
                }
    }
}

data class Resource(val name: String, val buttonLabel: String, var produceTime: Long = 4000, var count: Int = 0, var isProducing: Boolean = false,
                    var amountProducedPerProduction: Int = 2, var timePassedForResource: Long = 0, var level: Int = 1) {

    fun timePassed(t: Long) {
        timePassedForResource += t
        if (timePassedForResource >= produceTime) {
            timePassedForResource %= produceTime
            count += amountProducedPerProduction * level
            level += 1
            isProducing = false
        }
    }

    fun onClick() {
        isProducing = true
    }
}

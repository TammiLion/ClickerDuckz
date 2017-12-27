package com.tammidev.clickerduckz.clickerduckz

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

class MainViewModel constructor(val eggsController: EggsController) : ViewModel() {

    val eggsCountText: EggsCountTextLiveData = EggsCountTextLiveData()
    val ducklingsCountText: DucklingsCountTextLiveData = DucklingsCountTextLiveData()
    val ducksCountText: DucksCountTextLiveData = DucksCountTextLiveData()
    val canClickToHatch: CanClickToHatchLiveData = CanClickToHatchLiveData()

    inner class EggsCountTextLiveData : LiveData<String>() {
        init {
            eggsController.eggsObservable.subscribe { value -> postValue("Eggs: " + value.toString()) }
        }
    }

    inner class DucklingsCountTextLiveData : LiveData<String>() {
        init {
            eggsController.ducklingsObservable.subscribe { value -> postValue("Ducklings: " + value.toString()) }
        }
    }

    inner class DucksCountTextLiveData : LiveData<String>() {
        init {
            eggsController.ducksObservable.subscribe { value -> postValue("Ducks: " + value.toString()) }
        }
    }

    inner class CanClickToHatchLiveData : LiveData<Boolean>() {
        init {
            eggsController.eggsObservable.subscribe { value -> postValue(value > 0) }
        }
    }
}
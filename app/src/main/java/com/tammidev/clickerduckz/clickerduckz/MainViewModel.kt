package com.tammidev.clickerduckz.clickerduckz

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel constructor(val eggsController: EggsController) : ViewModel() {

    val eggsCountText: EggsCountTextLiveData = EggsCountTextLiveData()
    private val ducklings: MutableLiveData<Int> = MutableLiveData<Int>()
    val ducklingsCountText: MutableLiveData<String> = MutableLiveData<String>()
    val canClickToHatch: CanClickToHatchLiveData = CanClickToHatchLiveData()

    inner class EggsCountTextLiveData : LiveData<String>() {
        init {
            eggsController.eggs.subscribe { value -> postValue("Eggs: " + value.toString()) }
        }
    }

    inner class CanClickToHatchLiveData : LiveData<Boolean>() {
        init {
            eggsController.eggs.subscribe { value -> postValue(value > 0) }
        }
    }
}
package com.tammidev.clickerduckz.clickerduckz

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject


class MainViewModelFactory @Inject constructor(val eggsController: EggsController) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(eggsController) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
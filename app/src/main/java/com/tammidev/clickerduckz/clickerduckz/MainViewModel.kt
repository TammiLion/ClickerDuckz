package com.tammidev.clickerduckz.clickerduckz

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel constructor(val eggsController: EggsController) : ViewModel() {

    val viewState: LiveData<List<ResourceViewState>> = ViewStateLiveData()

    inner class ViewStateLiveData : LiveData<List<ResourceViewState>>() {
        init {
            eggsController.observable.observeOn(Schedulers.newThread()).subscribeOn(AndroidSchedulers.mainThread()).subscribe { value ->
                value?.let {
                    postValue(value.map { resource ->
                        ResourceViewState(resource.name, resource.count.toString(), resource.buttonLabel, R.drawable.egg_animation,
                                resource.level, resource.produceTime,
                                !resource.isProducing, resource::onClick)
                    })
                }
            }
        }
    }
}

data class ResourceViewState(val label: String, val count: String, val buttonLabel: String,
                             val drawableRes: Int,
                             val levelProgress: Int, val produceTime: Long,
                             val enableButton: Boolean, val onClick: () -> Unit)
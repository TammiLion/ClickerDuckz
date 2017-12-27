package com.tammidev.clickerduckz.clickerduckz

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var eggsController: EggsController
    @Inject lateinit var viewModelFactory: MainViewModelFactory

    val model by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as CDApplication).getAppComponent().inject(this)

        setViewEnabledListeners()
        setTextChangeListeners()
        setBtnInteractors()
    }

    private fun setTextChangeListeners() {
        model.eggsCountText.observe(this, Observer { text -> eggsLabel.setText(text) })
        model.ducklingsCountText.observe(this, Observer { text -> ducklingsLabel.setText(text) })
        model.ducksCountText.observe(this, Observer { text -> ducksLabel.setText(text) })
    }

    private fun setViewEnabledListeners() {
        model.canClickToHatch.observe(this, Observer { canClickToHatch -> hatchBtn.isEnabled = canClickToHatch ?: false })
    }

    private fun setBtnInteractors() {
        hatchBtn.setOnClickListener({ v -> eggsController.onHatchBtnClicked() })
    }
}

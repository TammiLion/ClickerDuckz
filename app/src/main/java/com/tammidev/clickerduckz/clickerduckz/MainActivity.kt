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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as CDApplication).getAppComponent().inject(this)

        val model = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        model.canClickToHatch.observe(this, Observer { t -> hatchBtn.isEnabled = t ?: false })

        hatchBtn.setOnClickListener({ v -> eggsController.onHatchBtnClicked() })

        model.eggsCountText.observe(this, Observer { text -> eggsLabel.setText(text) })
    }
}

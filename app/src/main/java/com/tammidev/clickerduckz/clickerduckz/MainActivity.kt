package com.tammidev.clickerduckz.clickerduckz

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val model by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private val adapter = ResourceAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as CDApplication).getAppComponent().inject(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setHasStableIds(true)
        recyclerView.adapter = adapter

        model.viewState.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }
}

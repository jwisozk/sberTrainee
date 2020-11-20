package com.example.sbertrainee.view

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.sbertrainee.App
import com.example.sbertrainee.R
import com.example.sbertrainee.presenter.MainPresenter


class MainActivity : AppCompatActivity() {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val app = applicationContext as App
        val model = app.model
        mainPresenter = MainPresenter(model, supportFragmentManager, this)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        mainPresenter.onDispatchTouchEvent(event, currentFocus)
        return super.dispatchTouchEvent(event)
    }
}
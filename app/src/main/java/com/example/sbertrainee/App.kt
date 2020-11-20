package com.example.sbertrainee

import android.app.Application
import com.example.sbertrainee.model.Model

class App : Application() {

    lateinit var model: Model
        private set

    override fun onCreate() {
        super.onCreate()
        model = Model()
    }
}
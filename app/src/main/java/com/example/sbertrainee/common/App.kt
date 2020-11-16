package com.example.sbertrainee.common

import android.app.Application
import com.example.sbertrainee.model.Model

class App: Application() {

    lateinit var model: Model
        private set

    override fun onCreate() {
        super.onCreate()
        model = Model()
    }

//    companion object {
//        lateinit var model: Model
//            private set
//    }
}
package com.example.sbertrainee.common

import android.app.Application
import com.example.sbertrainee.model.TraineeData

class App : Application() {

    companion object {
        var traineeList: MutableList<TraineeData> = ArrayList()
    }
}
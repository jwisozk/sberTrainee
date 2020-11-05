package com.example.sbertrainee

import com.example.sbertrainee.mvp.TraineeData

interface Contract {

    interface View {
//        fun getFullName(): String
//        fun getGender(): String
//        fun hasAlpha(): Boolean
//        fun hasSigma(): Boolean
//        fun hasSComputer(): Boolean
//        fun addTrainee()
        fun getTraineeData(): TraineeData
        fun showErrorMessage(msgErrorId: Int)
    }

    interface Presenter {
        fun onButtonWasClicked()
    }
}
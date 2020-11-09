package com.example.sbertrainee.common

import com.example.sbertrainee.model.TraineeData

interface Contract {

    interface View {
        fun getFullName(): String
        fun getGenderId(): Int
        fun hasAlphaAccount(): Boolean
        fun hasSigmaAccount(): Boolean
        fun hasComputer(): Boolean
        fun showErrorMessage(msgErrorId: Int)
        fun addTrainee(traineeData: TraineeData)
    }

    interface Presenter {
        fun onButtonWasClicked()
    }
}
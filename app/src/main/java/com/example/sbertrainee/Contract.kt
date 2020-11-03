package com.example.sbertrainee

interface Contract {

    interface View {
//        fun getFullName(): String
//        fun getGender(): String
//        fun hasAlpha(): Boolean
//        fun hasSigma(): Boolean
//        fun hasSComputer(): Boolean
//        fun addTrainee()
        fun getTraineeData(): TraineeData
        fun showErrorMessage(msgError: String)
    }

    interface Presenter {
        fun onButtonWasClicked()
    }
}
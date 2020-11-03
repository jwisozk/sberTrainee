package com.example.sbertrainee

import android.util.Log

class Presenter(val model: TraineeViewModel) : Contract.Presenter {
    private var view: Contract.View? = null


    fun attachView(view: Contract.View) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun viewIsReady() {

    }

    private fun isValidData(traineeData: TraineeData?): Boolean {
        var status = true
        val msgError = when (null) {
            traineeData -> {
                status = false
                "Объект не создан"
            }
            traineeData.fullName -> {
                status = false
                "Укажите ФИО"
            }
            traineeData.gender -> {
                status = false
                "Укажите пол"

            }
            else -> ""
        }
        if (!status) {
            Log.d(this.toString(), "status: $status, view: $view")
            view?.showErrorMessage(msgError)
        }
        return status
    }

    override fun onButtonWasClicked() {
        val traineeData = view?.getTraineeData()
        if (!isValidData(traineeData))
            return
    }

//    fun getGender(radioButtonId: Int): String? {
//        val radioButton: RadioButton = view.find
//    }
}
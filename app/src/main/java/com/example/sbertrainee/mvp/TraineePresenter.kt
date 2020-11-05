package com.example.sbertrainee.mvp

import android.view.View
import com.example.sbertrainee.Contract
import com.example.sbertrainee.R

class TraineePresenter : Contract.Presenter {
    private var view: Contract.View? = null


    fun attachView(view: Contract.View) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    private fun isValidData(data: TraineeData?): Boolean {
        val checkResult = when {
            data == null -> Pair(false, ErrorType.DATA_NOT_RECEIVED.value)
            data.fullName.isEmpty() -> Pair(false, ErrorType.FULL_NAME_ABSENT.value)
            data.gender == null -> Pair(false, ErrorType.GENDER_ABSENT.value)
            else -> Pair(true, 0)
        }
        if (!checkResult.first) {
            view?.showErrorMessage(checkResult.second)
        }
        return checkResult.first
    }

    override fun onButtonWasClicked() {
        val traineeData = view?.getTraineeData()
        if (!isValidData(traineeData))
            return
        traineeData?.let {
            view?.showTraineeInfo(it)
        }
    }


//    fun getGender(radioButtonId: Int): String? {
//        val radioButton: RadioButton = view.find
//    }

    private enum class ErrorType(val value: Int) {
        DATA_NOT_RECEIVED(R.string.data_not_received),
        FULL_NAME_ABSENT(R.string.full_name_absent),
        GENDER_ABSENT(R.string.gender_absent)
    }
}
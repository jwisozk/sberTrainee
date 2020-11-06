package com.example.sbertrainee.presenter

import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.R
import com.example.sbertrainee.model.TraineesCell

class TraineePresenter : Contract.Presenter {
    private var view: Contract.View? = null

    companion object {
        private const val MAN = "м"
        private const val WOMAN = "ж"
    }

    enum class GenderType(val value: Int) {
        MAN (R.id.radioGenderMan),
        WOMAN (R.id.radioGenderWoman)
    }

    fun attachView(view: Contract.View) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    override fun onButtonWasClicked() {
        view?.let { v ->
            val fullName = v.getFullName()
            if (fullName.isEmpty()) {
                v.showErrorMessage(ErrorType.FULL_NAME_ABSENT.value)
                return
            }
            val gender = when(GenderType.values().singleOrNull { it.value == v.getGenderId()}) {
                GenderType.MAN -> MAN
                GenderType.WOMAN -> WOMAN
                else -> null
            }
            if (gender == null) {
                v.showErrorMessage(ErrorType.GENDER_ABSENT.value)
                return
            }
            val cell = TraineesCell(fullName, gender, v.hasAlphaAccount(), v.hasSigmaAccount(), v.hasComputer())
            v.addTrainee(cell)
        }
    }

    private enum class ErrorType(val value: Int) {
        FULL_NAME_ABSENT(R.string.full_name_absent),
        GENDER_ABSENT(R.string.gender_absent)
    }
}
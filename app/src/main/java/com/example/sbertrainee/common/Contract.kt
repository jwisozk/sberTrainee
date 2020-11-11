package com.example.sbertrainee.common

import com.example.sbertrainee.model.TraineeData

interface Contract {

    interface View {
        fun showErrorMessage(errorId: Int)
        fun showTrainee(traineeList: List<TraineeData>)
        fun clear()
    }

    interface Presenter {
        fun onTextChanged(s: CharSequence?)
        fun onGenderCheckedChange(checkId: Int)
        fun onHasAlphaCheckedChange(isChecked: Boolean)
        fun onHasSigmaCheckedChange(isChecked: Boolean)
        fun onHasComputerCheckedChange(isChecked: Boolean)
        fun onButtonClicked()
        fun detachView()
        fun getCurrentItemViewPager(): Int
    }

    interface ViewModel {
        fun addTrainee(traineeData: TraineeData)
        fun getTraineeList(): MutableList<TraineeData>
        fun getFullName(): CharSequence?
        fun setFullName(s: CharSequence?)
        fun getGender(): String?
        fun setGender(s: String?)
        fun getGenderById(id: Int): String?
        fun setHasAlphaAccount(isChecked: Boolean)
        fun getHasAlphaAccount(): Boolean
        fun setHasSigmaAccount(isChecked: Boolean)
        fun getHasSigmaAccount(): Boolean
        fun setHasComputer(isChecked: Boolean)
        fun getHasComputer(): Boolean
        fun checkValid(): Int?
        fun clear()
    }
}
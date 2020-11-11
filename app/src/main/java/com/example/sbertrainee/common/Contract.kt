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
        fun onAddButtonClicked()
        fun getCurrentItemViewPager(): Int
    }
}
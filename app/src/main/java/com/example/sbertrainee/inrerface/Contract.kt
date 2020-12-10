package com.example.sbertrainee.inrerface

import com.example.sbertrainee.model.Trainee

interface Contract {

    interface InputView {
        fun clearInputGender()
        fun clearInputAlphaAccount()
        fun clearInputSigmaAccount()
        fun clearInputComputer()
        fun setInputName(name: String)
        fun setEnabledAddButton(value: Boolean)
        fun notifyNewTraineeAdded()
    }

    interface TraineeCatalogView {
        fun setTraineeList(traineeList: List<Trainee>)
        fun setSelectedItemPosition(position: Int)
        fun setVisibleFragmentView()
        fun updateTraineeList(traineeList: List<Trainee>)
    }

    interface InputPresenter {
        fun onInputNameChanged(s: CharSequence?)
        fun onInputGenderMaleChecked()
        fun onInputGenderFemaleChecked()
        fun onInputAlphaAccountChecked(isChecked: Boolean)
        fun onInputSigmaAccountChecked(isChecked: Boolean)
        fun onInputComputerChecked(isChecked: Boolean)
        fun onClearInputNameButtonClicked()
        fun onAddButtonClicked()
    }

    interface TraineeCatalogPresenter {
        fun onItemPositionSelected(position: Int)
        fun onNewTraineeAdded()
    }
}
package com.example.sbertrainee.inrerface

import com.example.sbertrainee.model.Trainee

interface Contract {

    interface InputView {
        fun clearInputGender()
        fun clearInputAlphaAccount()
        fun clearInputSigmaAccount()
        fun clearInputComputer()
        fun setInputName(name: String, start: Int)
        fun setEnabledAddButton(enabled: Boolean)
        fun notifyNewTraineeAdded()
    }

    interface TraineeCatalogView {
        fun setTraineeList(traineeList: List<Trainee>)
        fun setSelectedItemPosition(position: Int)
        fun setVisibilityFragmentView(value: Int)
        fun updateTraineeList(traineeList: List<Trainee>)
    }

    interface InputPresenter {
        fun onInputNameChanged(s: CharSequence?, start: Int)
        fun onInputGenderMaleChecked()
        fun onInputGenderFemaleChecked()
        fun onInputAlphaAccountChecked(isChecked: Boolean)
        fun onInputSigmaAccountChecked(isChecked: Boolean)
        fun onInputComputerChecked(isChecked: Boolean)
        fun onAddButtonClicked()
    }

    interface TraineeCatalogPresenter {
        fun onItemPositionSelected(position: Int)
        fun refreshTraineeList()
        fun onRemoveTraineeClicked(trainee: Trainee)
        fun onStop()
    }
}
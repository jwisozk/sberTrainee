package com.example.sbertrainee.inrerface

import com.example.sbertrainee.model.Trainee

interface Contract {

    interface InputView {
        fun clearInputName()
        fun clearInputGender()
        fun clearInputAlphaAccount()
        fun clearInputSigmaAccount()
        fun clearInputComputer()
        fun setInputName(name: String)
        fun setEnabledAddButton(value: Boolean)
        fun notifyTraineeCatalogFragment()
    }

    interface TraineeCatalogView {
        fun setAdapter(traineeList: List<Trainee>)
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
        fun onAddButtonClicked()
        fun onClearButtonClicked()
    }

    interface TraineeCatalogPresenter {
        fun onItemPositionSelected(position: Int)
        fun onAddButtonClicked()
    }
}
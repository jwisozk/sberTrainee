package com.example.sbertrainee.inrerface

import com.example.sbertrainee.presenter.adapter.TraineeAdapter

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
        fun setAdapter(adapter: TraineeAdapter)
        fun setSelectedItemPosition(num: Int)
        fun setVisibleFragmentView()
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
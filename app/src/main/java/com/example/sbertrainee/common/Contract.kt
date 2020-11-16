package com.example.sbertrainee.common

import android.text.Editable
import com.example.sbertrainee.model.TraineeData
import com.example.sbertrainee.presenter.adapter.TraineeAdapter

interface Contract {

    interface InputView {
        fun clear()
        fun setTextToEditText(text: String)
    }

    interface ViewPagerView {
        fun setAdapter(adapter: TraineeAdapter)
        fun showTrainee(traineeList: List<TraineeData>)
    }

    interface InputPresenter {
        fun onTextChanged(s: CharSequence?)
        fun onGenderCheckedChange(checkId: Int)
        fun onHasAlphaCheckedChange(isChecked: Boolean)
        fun onHasSigmaCheckedChange(isChecked: Boolean)
        fun onHasComputerCheckedChange(isChecked: Boolean)
        fun onAddButtonClicked()
//        fun getCurrentItemViewPager(): Int
//        fun getTraineeList(): MutableList<TraineeData>
    }

    interface ViewPagerPresenter {
//        fun setAdapter(adapter: TraineeAdapter)
    }
}
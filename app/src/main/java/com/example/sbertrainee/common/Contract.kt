package com.example.sbertrainee.common

import android.text.Editable
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.sbertrainee.model.TraineeData
import com.example.sbertrainee.presenter.adapter.TraineeAdapter

interface Contract {

    interface MainView {
//        fun getFocus(): View?
//        fun getInputMethodManager(): InputMethodManager
//        fun dispatchTouchEvent(event: MotionEvent): Boolean
    }
    
    interface InputView {
        fun clear()
        fun setTextToEditText(text: String)
        fun setSelection(position: Int)

    }

    interface ViewPagerView {
        fun setAdapter(adapter: TraineeAdapter)
        fun showTrainee(traineeList: List<TraineeData>)
    }

    interface MainPresenter {
        fun onDispatchTouchEvent(event: MotionEvent)
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
package com.example.sbertrainee.presenter

import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.model.TraineeData

class TraineePresenter(
    private var view: Contract.View?,
    private val viewModel: Contract.ViewModel
) : Contract.Presenter {

    override fun onTextChanged(s: CharSequence?) {
        viewModel.setFullName(s)
    }

    override fun onGenderCheckedChange(checkId: Int) {
        val gender = viewModel.getGenderById(checkId)
        viewModel.setGender(gender)
    }

    override fun onHasAlphaCheckedChange(isChecked: Boolean) {
        viewModel.setHasAlphaAccount(isChecked)
    }

    override fun onHasSigmaCheckedChange(isChecked: Boolean) {
        viewModel.setHasSigmaAccount((isChecked))
    }

    override fun onHasComputerCheckedChange(isChecked: Boolean) {
        viewModel.setHasComputer(isChecked)
    }

    override fun onButtonClicked() {
        view?.let { v ->
            val errorId = viewModel.checkValid()
            if (errorId != null) {
                v.showErrorMessage(errorId)
                return
            }
            val traineeData = TraineeData(
                viewModel.getFullName().toString(),
                viewModel.getGender()!!,
                viewModel.getHasAlphaAccount(),
                viewModel.getHasSigmaAccount(),
                viewModel.getHasComputer()
            )
            viewModel.addTrainee(traineeData)
            v.showTrainee(viewModel.getTraineeList())
            v.clear()
            viewModel.clear()
        }
    }

    override fun getCurrentItemViewPager(): Int = viewModel.getTraineeList().size - 1

    override fun detachView() {
        view = null
    }
}
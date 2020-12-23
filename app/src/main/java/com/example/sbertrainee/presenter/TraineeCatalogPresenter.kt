package com.example.sbertrainee.presenter

import android.view.View
import androidx.annotation.VisibleForTesting
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.model.Trainee
import java.io.File


class TraineeCatalogPresenter(
    private val view: Contract.TraineeCatalogView,
    private val model: Model,
    private val path: File
) : Contract.TraineeCatalogPresenter {

    init {
        val traineeList = model.readWriteFile.readFile(path)
        if (traineeList?.isNotEmpty() == true) {
            view.setVisibilityFragmentView(View.VISIBLE)
            model.setTraineeList(traineeList)
        }

        view.setTraineeList(model.traineeList)
        view.setSelectedItemPosition(model.selectedItemPosition)
    }

    override fun refreshTraineeList() {
        val traineeList = model.traineeList
        if (traineeList.isNotEmpty()) {
            updateTraineeList(traineeList)
            showLastAddedTrainee()
        } else {
            view.setVisibilityFragmentView(View.INVISIBLE)
        }
    }

    override fun onRemoveTraineeClicked(trainee: Trainee) {
        model.removeTrainee(trainee)
        refreshTraineeList()
    }

    @VisibleForTesting
    fun updateTraineeList(traineeList: List<Trainee>) {
        view.updateTraineeList(traineeList)
    }

    @VisibleForTesting
    fun showLastAddedTrainee() {
        view.setSelectedItemPosition(model.lastItemIndex)
    }

    override fun onItemPositionSelected(position: Int) {
        model.setSelectedItemPosition(position)
    }

    override fun onStop() {
        if (model.traineeList.isNotEmpty()) {
            model.readWriteFile.writeToFile(path, model.traineeList)
        }
    }
}
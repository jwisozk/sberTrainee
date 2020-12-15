package com.example.sbertrainee.presenter

import android.view.View
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.model.Trainee
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException


class TraineeCatalogPresenter(
    private val view: Contract.TraineeCatalogView,
    private val model: Model,
    private val path: File
) : Contract.TraineeCatalogPresenter {

    init {
        val traineeList = readFile()
        traineeList?.let {
            if (it.isNotEmpty()) {
                view.setVisibilityFragmentView(View.VISIBLE)
                model.traineeList = ArrayList(it)
            }
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

    override fun onRemoveButtonClicked() {
        model.removeCurrentTrainee()
        refreshTraineeList()
    }

    private fun updateTraineeList(traineeList: List<Trainee>) {
        view.updateTraineeList(traineeList)
    }

    private fun showLastAddedTrainee() {
        view.setSelectedItemPosition(model.traineeIdShow - 1)
    }

    override fun onItemPositionSelected(position: Int) {
        model.selectedItemPosition = position
    }

    private fun writeToFile(traineeList: List<Trainee>) {
        val traineeListJson = Json.encodeToString(traineeList)
        try {
            File(path, TRAINEE_LIST_FILE).bufferedWriter().use { out -> out.write(traineeListJson) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readFile(): List<Trainee>? {
        try {
            val traineeListJson =
                File(path, TRAINEE_LIST_FILE).bufferedReader().use { input -> input.readText() }
            return Json.decodeFromString(traineeListJson)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    override fun onStop() {
        if (model.traineeList.isNotEmpty())
            writeToFile(model.traineeList)
    }

    companion object {
        private const val TRAINEE_LIST_FILE = "trainee_list_file"
    }
}
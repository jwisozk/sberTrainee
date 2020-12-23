package com.example.sbertrainee.view.fragments.util

import android.util.Log
import com.example.sbertrainee.model.Trainee
import com.example.sbertrainee.presenter.TraineeCatalogPresenter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException
import java.lang.NullPointerException

class ReadWriteFile {

    fun writeToFile(path: File, traineeList: List<Trainee>) {
        val traineeListJson = Json.encodeToString(traineeList)
        try {
            File(path, TRAINEE_LIST_FILE).bufferedWriter().use { out -> out.write(traineeListJson) }
        } catch (e: IOException) {
            Log.e(this.toString(), e.toString())
        } catch (e: NullPointerException) {
            Log.e(this.toString(), e.toString())
        }
    }

    fun readFile(path: File): List<Trainee>? {
        try {
            val traineeListJson =
                File(path, TRAINEE_LIST_FILE).bufferedReader().use { input -> input.readText() }
            return Json.decodeFromString(traineeListJson)
        } catch (e: IOException) {
            Log.e(this.toString(), e.toString())
        } catch (e: NullPointerException) {
            Log.e(this.toString(), e.toString())
        }
        return null
    }

    companion object {
        private const val TRAINEE_LIST_FILE = "trainee_list_file"
    }
}
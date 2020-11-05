package com.example.sbertrainee.mvp

import android.content.ContentValues
import android.database.Cursor
import android.os.AsyncTask
import com.example.sbertrainee.common.Trainee
import com.example.sbertrainee.common.TraineeTable
import com.example.sbertrainee.database.DbHelper
import java.util.*
import java.util.concurrent.TimeUnit

class TraineesModel(val dbHelper: DbHelper) {

    fun loadTrainees(callback: LoadTraineeCallback?) {
        val loadTraineesTask = LoadTraineesTask(callback)
        loadTraineesTask.execute()
    }

    fun addTrainee(contentValues: ContentValues?, callback: CompleteCallback?) {
        val addTraineeTask = AddTraineeTask(callback)
        addTraineeTask.execute(contentValues)
    }

    fun clearTrainees(completeCallback: CompleteCallback?) {
        val clearTraineesTask = ClearTraineesTask(completeCallback)
        clearTraineesTask.execute()
    }

    interface LoadTraineeCallback {
        fun onLoad(trainees: List<Trainee>?)
    }

    interface CompleteCallback {
        fun onComplete()
    }

    inner class LoadTraineesTask(private val callback: LoadTraineeCallback?) :
        AsyncTask<Void?, Void?, List<Trainee>>() {
        override fun doInBackground(vararg params: Void?): List<Trainee>? {
            val trainees: MutableList<Trainee> = LinkedList()
            val cursor: Cursor = dbHelper.readableDatabase
                .query(TraineeTable.TABLE, null, null, null, null, null, null)
            while (cursor.moveToNext()) {
                val trainee: Trainee = Trainee().apply {
                    id = cursor.getLong(cursor.getColumnIndex(TraineeTable.COLUMN.ID))
                    fullName =
                        cursor.getString(cursor.getColumnIndex(TraineeTable.COLUMN.FULL_NAME))
                    gender = cursor.getString(cursor.getColumnIndex(TraineeTable.COLUMN.GENDER))
                    hasAlphaAccount =
                        cursor.getInt(cursor.getColumnIndex(TraineeTable.COLUMN.HAS_ALPHA))
                    hasSigmaAccount =
                        cursor.getInt(cursor.getColumnIndex(TraineeTable.COLUMN.HAS_SIGMA))
                    hasComputer =
                        cursor.getInt(cursor.getColumnIndex(TraineeTable.COLUMN.HAS_COMPUTER))
                }
                trainees.add(trainee)
            }
            cursor.close()
            return trainees
        }

        override fun onPostExecute(trainees: List<Trainee>) {
            callback?.onLoad(trainees)
        }
    }

    inner class AddTraineeTask(private val callback: CompleteCallback?) :
        AsyncTask<ContentValues?, Void?, Void?>() {
        override fun doInBackground(vararg params: ContentValues?): Void? {
            val cvTrainee = params[0]
            dbHelper.writableDatabase.insert(TraineeTable.TABLE, null, cvTrainee)
            try {
                TimeUnit.SECONDS.sleep(1)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            callback?.onComplete()
        }
    }

    inner class ClearTraineesTask(private val callback: CompleteCallback?) :
        AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg params: Void?): Void? {
            dbHelper.writableDatabase.delete(TraineeTable.TABLE, null, null)
            try {
                TimeUnit.SECONDS.sleep(1)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            callback?.onComplete()
        }
    }
}
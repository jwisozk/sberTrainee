package com.example.sbertrainee.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sbertrainee.common.TraineeTable

class DbHelper(context: Context, name: String, version: Int):
    SQLiteOpenHelper(context, name, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(TraineeTable.CREATE_SCRIPT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
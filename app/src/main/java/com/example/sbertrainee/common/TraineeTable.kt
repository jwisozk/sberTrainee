package com.example.sbertrainee.common

class TraineeTable {

    companion object {
        const val TABLE = "trainees"

        val CREATE_SCRIPT = String.format(
            "create table %s ("
                    + "%s integer primary key autoincrement,"
                    + "%s text,"
                    + "%s text,"
                    + "%s integer,"
                    + "%s integer,"
                    + "%s integer" + ");",
            TABLE,
            COLUMN.ID,
            COLUMN.FULL_NAME,
            COLUMN.GENDER,
            COLUMN.HAS_ALPHA,
            COLUMN.HAS_SIGMA,
            COLUMN.HAS_COMPUTER
        )
    }

    object COLUMN {
        const val ID = "_id"
        const val FULL_NAME = "full_name"
        const val GENDER = "gender"
        const val HAS_ALPHA = "has_alpha"
        const val HAS_SIGMA = "has_sigma"
        const val HAS_COMPUTER = "has_computer"
    }
}
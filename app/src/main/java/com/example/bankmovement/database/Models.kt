package com.example.bankmovement.database

class MovementModel {

    // CONSTANTS
    // If you change the database schema, you must increment the database version.
    companion object {

        const val DATABASE_NAME = "Movement.db"
        const val DATABASE_VERSION = 1

    }

    /*
     *  Table of Movements
     */
    object MovementTable {
        const val TABLE_NAME = "MovementTable"
        const val COLUMN_NAME_ID = "_id"
        const val COLUMN_AMOUNT = "Title"
        const val COLUMN_DATE = "Date"
        private const val TABLE_MOVEMENT = MovementTable.COLUMN_NAME_ID

        val COLUMN_NAMES = arrayOf(
            COLUMN_NAME_ID,
            COLUMN_AMOUNT,
            COLUMN_DATE,
        )
        /*
        *  Josué Ruiz
        */

        const val SORT_ORDER = "$COLUMN_DATE DESC"

        const val SQL_CREATE_TABLE_MOVEMENT =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_AMOUNT INTEGER, " +
                    "$COLUMN_DATE INTEGER);"

        const val SQL_DELETE_TABLE_MOVEMENT = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}
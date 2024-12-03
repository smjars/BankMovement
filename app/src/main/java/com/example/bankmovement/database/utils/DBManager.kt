package com.example.bankmovement.database.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.bankmovement.database.MovementModel

class DBManager (context: Context) :
    SQLiteOpenHelper(context, MovementModel.DATABASE_NAME, null, MovementModel.DATABASE_VERSION){

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        db?.execSQL("PRAGMA foreign_keys = ON;")
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(MovementModel.MovementTable.SQL_CREATE_TABLE_MOVEMENT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        onDelete(db)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    private fun onDelete(db: SQLiteDatabase) {
        db.execSQL(MovementModel.MovementTable.SQL_CREATE_TABLE_MOVEMENT)
    }
}
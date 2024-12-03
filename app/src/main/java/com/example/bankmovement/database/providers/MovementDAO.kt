package com.example.bankmovement.database.providers

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.bankmovement.database.Movement
import com.example.bankmovement.database.MovementModel
import com.example.bankmovement.database.utils.DBManager

class MovementDAO(context: Context) {

    private val dbManager: DBManager = DBManager(context)

    fun insert(movement: Movement): Movement {

        // Gets the data repository in write mode
        val db = dbManager.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(MovementModel.MovementTable.COLUMN_AMOUNT, movement.amount)
            put(MovementModel.MovementTable.COLUMN_DATE, movement.date)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(MovementModel.MovementTable.TABLE_NAME, null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        db.close()

        movement.id = newRowId.toInt()

        return movement

    }

    fun update(movement: Movement){

        val db = dbManager.writableDatabase

        val values = ContentValues().apply {
            put(MovementModel.MovementTable.COLUMN_AMOUNT, movement.amount)
            put(MovementModel.MovementTable.COLUMN_DATE, movement.date)
        }

        val updatedRows = db.update(MovementModel.MovementTable.TABLE_NAME, values, "${MovementModel.MovementTable.COLUMN_NAME_ID} = ${movement.id}", null)
        Log.i("DATABASE", "Updated $updatedRows record, " +
                "\nid: ${movement.id} with Amount: ${movement.amount}, " +
                "\nDate: ${movement.date}"
        )

        db.close()
    }

    fun delete(movement: Movement){
        val db = dbManager.writableDatabase

        val deleteRows = db.delete(MovementModel.MovementTable.TABLE_NAME, "${MovementModel.MovementTable.COLUMN_NAME_ID} = ${movement.id}", null)
        Log.i("DATABASE", "Deleted rows: $deleteRows")

        db.close()
    }

    @SuppressLint("Range")
    fun find(id:Int): Movement? {

        val db = dbManager.writableDatabase

        val cursor = db.query(
            MovementModel.MovementTable.TABLE_NAME,                         // The Table to query
            MovementModel.MovementTable.COLUMN_NAMES,                        // The array of columns to return (pass null to get all)
            "${MovementModel.MovementTable.COLUMN_NAME_ID} = $id",   // The columns for the WHERE clause
            null,                                      // The values for the WHERE clause
            null,                                          // don't group the rows
            null,                                           // don't filter by row groups
            null                                           // The sort order
        )

        var movement: Movement? = null

        if (cursor.moveToNext()){
            val movementId = cursor.getInt(cursor.getColumnIndex(MovementModel.MovementTable.COLUMN_NAME_ID))
            val movementAmount = cursor.getInt(cursor.getColumnIndex(MovementModel.MovementTable.COLUMN_AMOUNT))
            val movementDate = cursor.getString(cursor.getColumnIndex(MovementModel.MovementTable.COLUMN_DATE)).toLong()

            // Every post is added to a list
            movement = Movement(movementId, movementAmount, movementDate)
        }
        cursor.close()
        db.close()

        return movement

    }

    @SuppressLint("Range")
    fun findAll(): List<Movement>{

        val db = dbManager.writableDatabase

        val cursor = db.query(
            MovementModel.MovementTable.TABLE_NAME,                         // The Table to query
            MovementModel.MovementTable.COLUMN_NAMES,                        // The array of columns to return (pass null to get all)
            null,                                         // The columns for the WHERE clause
            null,                                      // The values for the WHERE clause
            null,                                          // don't group the rows
            null,                                           // don't filter by row groups
            MovementModel.MovementTable.SORT_ORDER                        // The sort order
        )

        val list:MutableList<Movement> = mutableListOf()
        /*
         *  Josué Ruiz
        */

        while (cursor.moveToNext()){
            val movementId = cursor.getInt(cursor.getColumnIndex(MovementModel.MovementTable.COLUMN_NAME_ID))
            val movementAmount = cursor.getInt(cursor.getColumnIndex(MovementModel.MovementTable.COLUMN_AMOUNT))
            val movementDate = cursor.getString(cursor.getColumnIndex(MovementModel.MovementTable.COLUMN_DATE)).toLong()

            // Every post is added to a list
            val movement = Movement(movementId, movementAmount, movementDate)
            list.add(movement)
        }
        cursor.close()
        db.close()

        return list

    }
}
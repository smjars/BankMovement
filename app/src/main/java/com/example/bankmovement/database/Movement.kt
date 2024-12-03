package com.example.bankmovement.database

class Movement (var id:Int, var amount:Int, var date:Long) {

    override fun toString(): String {
        return "Id: $id -> Amount: $amount, Date: $date"
    }
}
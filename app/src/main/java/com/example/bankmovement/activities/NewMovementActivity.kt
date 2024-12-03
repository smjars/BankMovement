package com.example.bankmovement.activities

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bankmovement.R
import com.example.bankmovement.database.Movement
import com.example.bankmovement.database.providers.MovementDAO
import com.example.bankmovement.databinding.ActivityMainBinding
import com.example.bankmovement.databinding.ActivityNewMovementBinding
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar
import kotlin.random.Random

class NewMovementActivity : AppCompatActivity() {

    private lateinit var binding:ActivityNewMovementBinding
    private lateinit var movementDAO: MovementDAO
    private lateinit var saveButton:Button
    private lateinit var date:TextView
    private lateinit var amount:EditText
    private lateinit var newMovement:Movement
    private var dateText:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewMovementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saveButton = binding.saveButton
        date = binding.dateTV
        amount = binding.amountTextField.editText!!

        dateText = getCurrentDate()
        val dateFormat = DateFormat.format("dd-MMMM-yyyy HH:mm", dateText)
        date.setText("Date: $dateFormat")

        movementDAO = MovementDAO(this)

        initView()
    }

    private fun initView(){

        saveButton.setOnClickListener {
            val newAmount:String = amount.text.toString()
            newMovement(newAmount)

            val intent = Intent(this, MovementsActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    /*
    * Method to create a new post into DB
     */
    private fun newMovement(newAmount: String) {
        //Get the current Date
        val date:Long = getCurrentDate()
        val amountInt = newAmount.toInt()

        //Save the new Post in the DB
        newMovement = Movement(-1,amountInt, date)
        movementDAO.insert(newMovement)
    }

    private fun getCurrentDate(): Long {
        return Calendar.getInstance().timeInMillis


    }
}
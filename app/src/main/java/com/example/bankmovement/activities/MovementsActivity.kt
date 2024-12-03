package com.example.bankmovement.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankmovement.database.Movement
import com.example.bankmovement.database.adapters.MovementAdapter
import com.example.bankmovement.database.providers.MovementDAO
import com.example.bankmovement.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout

class MovementsActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding
    private lateinit var newMovement:FloatingActionButton
    private lateinit var recycler:RecyclerView
    private lateinit var movementDAO: MovementDAO
    private lateinit var adapter:MovementAdapter

    private lateinit var movementList:List<Movement>
    private lateinit var balanceTV:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movementDAO = MovementDAO(this)
        movementList = movementDAO.findAll()

        adapter = MovementAdapter(movementList)

        recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        newMovement = binding.newMovementFloatingActionButton

        balanceTV = binding.balanceTV

        loadData()

        initView()
    }


    private fun initView() {

        newMovement.setOnClickListener{
            val intent = Intent(this, NewMovementActivity::class.java)
            startActivity(intent)
        }
    }


    private fun loadData(){
        movementList = movementDAO.findAll()
        getBalance()
        adapter.updateItems(movementList)

    }

    private fun getBalance(){
        movementList = movementDAO.findAll()
        var balance = 0
        for (movement in movementList){
            balance += movement.amount
        }

        balanceTV.setText(balance.toString())
    }
}
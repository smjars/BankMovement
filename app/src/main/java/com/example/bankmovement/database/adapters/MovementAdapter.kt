package com.example.bankmovement.database.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankmovement.R
import com.example.bankmovement.database.Movement
import com.example.bankmovement.database.providers.MovementDAO
import com.example.bankmovement.databinding.MovementRecyclerviewBinding

class MovementAdapter (
    private var items:List<Movement> = listOf()): RecyclerView.Adapter<PostViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            val binding = MovementRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PostViewHolder(binding)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder( holder: PostViewHolder, position: Int) {
            holder.render(items[position])

        }

        @SuppressLint("NotifyDataSetChanged")
        fun updateItems(results: List<Movement>?) {
            items = results!!
            notifyDataSetChanged()
        }
    }

    class PostViewHolder(
        val binding: MovementRecyclerviewBinding,

        ): RecyclerView.ViewHolder(binding.root){

        fun render(
            movement:Movement,
        ){
            val dateFormat = DateFormat.format("dd-MMMM-yyyy HH:mm", movement.date)
            val amount:Int = movement.amount

            if(amount < 0){
                binding.valueAmountMovementTV.setTextColor(binding.root.context.getColor(R.color.red))
                binding.valueAmountMovementTV.text = movement.amount.toString()
            }else{
                binding.valueAmountMovementTV.setTextColor(binding.root.context.getColor(R.color.green))
                binding.valueAmountMovementTV.text = movement.amount.toString()
            }

            binding.valueIdMovementTV.text = movement.id.toString()

            binding.valueDateMovementTV.text = dateFormat


        }
}

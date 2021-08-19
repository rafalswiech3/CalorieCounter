package com.rafal.caloriecounter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafal.caloriecounter.databinding.MealItemBinding

class MealsAdapter(
    private val items: List<String>,
    private val addItemListener: AddItemClickListener
) : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String, pos: Int) {
            binding.titleTv.text = title

            binding.addBtn.setOnClickListener {
                addItemListener.addItemClicked(pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface AddItemClickListener {
        fun addItemClicked(pos: Int)
    }
}
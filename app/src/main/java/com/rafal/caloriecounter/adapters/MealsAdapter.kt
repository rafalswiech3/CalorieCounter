package com.rafal.caloriecounter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.rafal.caloriecounter.data.MealTotals
import com.rafal.caloriecounter.databinding.MealItemBinding

class MealsAdapter(
    private val items: List<String>,
    private val mealsListener: MealsAdapterListener
) : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    val viewHolders = mutableListOf<ViewHolder>()

    inner class ViewHolder(val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String, pos: Int) {
            binding.titleTv.text = title

            binding.addBtn.setOnClickListener {
                mealsListener.addItemClicked(pos)
            }

            binding.root.setOnClickListener {
                binding.apply {
                    if(rv.isVisible) rv.visibility = View.GONE else rv.visibility = View.VISIBLE
                }
            }

            viewHolders.add(this)
            mealsListener.viewHolderBind(pos, this)
        }

        fun updateTotals(totals: MealTotals) {
            binding.apply {
                kcalTv.text = totals.kcal.toString() + " kcal"
                fatTv.text = totals.fats.toString() + " g"
                proteinTv.text = totals.proteins.toString() + " g"
                carbsTv.text = totals.carbs.toString() + " g"
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

    interface MealsAdapterListener {
        fun addItemClicked(pos: Int)
        fun viewHolderBind(pos: Int, holder: ViewHolder)
    }
}
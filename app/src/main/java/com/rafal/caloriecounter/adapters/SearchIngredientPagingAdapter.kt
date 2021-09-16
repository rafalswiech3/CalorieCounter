package com.rafal.caloriecounter.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafal.caloriecounter.data.IngredientSearch
import com.rafal.caloriecounter.databinding.MealItemSearchBinding

class SearchIngredientPagingAdapter(
    private val listener: OnItemClickListener
) : PagingDataAdapter<IngredientSearch, SearchIngredientPagingAdapter.ViewHolder>(
    IngredientComparator()
) {
    inner class ViewHolder(private val binding: MealItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IngredientSearch) {

            Log.d("TAG", "LOADING!!!")

            Glide.with(itemView)
                .load("https://spoonacular.com/cdn/ingredients_100x100/${item.image}")
                .circleCrop()
                .into(binding.itemSearchIv)

            binding.apply {
                itemSearchTitle.text = item.name.replaceFirstChar { it.uppercase() }

                root.setOnClickListener {
                    listener.onItemClick(0, item)
                }

                itemSearchWeight.text = "${String.format("%.2f", item.amount)} ${item.unit}"

                itemSearchKcal.text = item.nutrients?.getCalories()?.let {
                    "${String.format("%.2f", it.amount)} ${it.unit}"
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MealItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class IngredientComparator : DiffUtil.ItemCallback<IngredientSearch>() {
        override fun areItemsTheSame(
            oldItem: IngredientSearch,
            newItem: IngredientSearch
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: IngredientSearch,
            newItem: IngredientSearch
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: Int, ingredient: IngredientSearch)
    }
}
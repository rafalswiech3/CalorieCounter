package com.rafal.caloriecounter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafal.caloriecounter.data.IngredientSearch
import com.rafal.caloriecounter.databinding.ProductItemBinding

class ProductAdapter(
    private val data: List<IngredientSearch>,
    private val listener: ProductAdapterListener,
    private val mealIndex: Int
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: IngredientSearch) {
            binding.apply {
                productItemTitleTv.text = ingredient.name.replaceFirstChar { it.uppercase() }

                productItemQuantityTv.text = ingredient.let {
                    "${it.amount} ${it.unit}"
                }

                productItemKcalTv.text = ingredient.nutrients.getCalories().let {
                    "${String.format("%.2f", it.amount)} ${it.unit}"
                }
                productItemFatTv.text = ingredient.nutrients.getFat().let {
                    "${String.format("%.2f", it.amount)} ${it.unit}"
                }
                productItemCarbsTv.text = ingredient.nutrients.getCarbs().let {
                    "${String.format("%.2f", it.amount)} ${it.unit}"
                }
                productItemProteinTv.text =
                    ingredient.nutrients.getProtein().let {
                        "${String.format("%.2f", it.amount)} ${it.unit}"
                    }

                productItemRemoveBtn.setOnClickListener {
                    listener.onProductRemoveClick(ingredient, mealIndex)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ProductAdapterListener {
        fun onProductRemoveClick(ingredient: IngredientSearch, mealIndex: Int)
    }
}
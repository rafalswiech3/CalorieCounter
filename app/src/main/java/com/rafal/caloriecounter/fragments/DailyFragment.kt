package com.rafal.caloriecounter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rafal.caloriecounter.R
import com.rafal.caloriecounter.adapters.MealsAdapter
import com.rafal.caloriecounter.adapters.ProductAdapter
import com.rafal.caloriecounter.data.IngredientSearch
import com.rafal.caloriecounter.data.MealTotals
import com.rafal.caloriecounter.databinding.FragmentDailyBinding
import com.rafal.caloriecounter.viewmodels.DailyViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val MEALS_COUNT = 5;

@AndroidEntryPoint
class DailyFragment : Fragment(), MealsAdapter.MealsAdapterListener,
    ProductAdapter.ProductAdapterListener {
    private var _binding: FragmentDailyBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MealsAdapter

    val viewModel: DailyViewModel by viewModels()

    private val products = Array<MutableList<IngredientSearch>>(MEALS_COUNT) { mutableListOf() }

    private lateinit var meals: List<String>

    private val totalsList = Array<MealTotals>(MEALS_COUNT) { MealTotals(0f, 0f, 0f, 0f) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDailyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meals = listOf(
            getString(R.string.breakfast),
            getString(R.string.second_breakfast),
            getString(R.string.lunch),
            getString(R.string.diner),
            getString(R.string.supper)
        )

        adapter = MealsAdapter(meals, this)
        val rv = binding.dailyRv
        rv.adapter = adapter

        loadAllProducts()
    }

    private fun loadAllProducts() {
        meals.forEachIndexed { index, s ->
            viewModel.loadProducts(index)
        }
    }

    override fun viewHolderBind(pos: Int, holder: MealsAdapter.ViewHolder) {
        viewModel.productsLiveDataArray.also {
            holder.binding.rv.adapter = ProductAdapter(products[pos], this, pos)
            it[pos].observe(viewLifecycleOwner) { list ->
                products[pos].clear()
                products[pos].addAll(list)

                totalsList[pos].clearAll()
                list.forEach { item ->
                    totalsList[pos].update(
                        kcal = item.nutrients.getCalories().amount,
                        fat = item.nutrients.getFat().amount,
                        carbs = item.nutrients.getCarbs().amount,
                        proteins = item.nutrients.getProtein().amount,
                    )
                }

                adapter.viewHolders[pos].updateTotals(totalsList[pos])

                holder.binding.rv.adapter?.let { adapter ->
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun addItemClicked(pos: Int) {
        val action = DailyFragmentDirections.actionDailyFragmentToSearchFragment(mealID = pos)
        findNavController().navigate(action)
    }

    override fun onProductRemoveClick(ingredient: IngredientSearch, mealIndex: Int) {
        viewModel.deleteProduct(ingredient, mealIndex)
    }
}
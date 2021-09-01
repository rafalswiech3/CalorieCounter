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
import com.rafal.caloriecounter.databinding.FragmentDailyBinding
import com.rafal.caloriecounter.viewmodels.DailyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFragment : Fragment(), MealsAdapter.MealsAdapterListener {
    private var _binding: FragmentDailyBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MealsAdapter

    val viewModel: DailyViewModel by viewModels()

    private val products = Array<MutableList<IngredientSearch>>(5) { mutableListOf() }

    private lateinit var meals: List<String>

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
        viewModel.productsLiveDataArray.apply {
            holder.binding.rv.adapter = ProductAdapter(products[pos])
            this[pos].observe(viewLifecycleOwner) {
                products[pos].clear()
                products[pos].addAll(it)

                holder.binding.rv.adapter?.let {
                    it.notifyDataSetChanged()
                }
            }
        }
    }

    override fun addItemClicked(pos: Int) {
        val action = DailyFragmentDirections.actionDailyFragmentToSearchFragment(mealID = pos)
        findNavController().navigate(action)
    }
}
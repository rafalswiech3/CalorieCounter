package com.rafal.caloriecounter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.rafal.caloriecounter.R
import com.rafal.caloriecounter.adapters.SearchIngredientPagingAdapter
import com.rafal.caloriecounter.api.FoodAPI
import com.rafal.caloriecounter.data.IngredientSearch
import com.rafal.caloriecounter.databinding.FragmentSearchBinding
import com.rafal.caloriecounter.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchIngredientPagingAdapter.OnItemClickListener {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private val args: SearchFragmentArgs by navArgs()

    @Inject
    lateinit var api: FoodAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagingAdapter = SearchIngredientPagingAdapter(this)
        val recyclerView = binding.searchRv

        recyclerView.adapter = pagingAdapter

        viewModel.searchIngredientsLiveData.observe(viewLifecycleOwner) {
            pagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        viewModel.searchIngredients("cheese", false)
    }

    override fun onItemClick(id: Int, ingredient: IngredientSearch) {
        viewModel.addIngredient(args.mealID, ingredient)
    }
}
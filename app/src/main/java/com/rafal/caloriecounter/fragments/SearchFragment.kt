package com.rafal.caloriecounter.fragments

import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.rafal.caloriecounter.R
import com.rafal.caloriecounter.adapters.SearchIngredientPagingAdapter
import com.rafal.caloriecounter.api.FoodAPI
import com.rafal.caloriecounter.data.IngredientSearch
import com.rafal.caloriecounter.databinding.FragmentSearchBinding
import com.rafal.caloriecounter.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
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

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNotEmpty()) viewModel.searchIngredients(it, false)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onItemClick(id: Int, ingredient: IngredientSearch) {
        MaterialDialog(requireContext()).show {
            title(R.string.add_ingredient_dialog_title)
            input(
                hintRes = R.string.amount,
                inputType = InputType.TYPE_CLASS_NUMBER
            ) { dialog, text ->
                viewModel.addIngredient(args.mealID, args.date, ingredient, text.toString().toInt())
            }
            positiveButton(R.string.add)
            negativeButton(R.string.cancel)
        }
    }
}
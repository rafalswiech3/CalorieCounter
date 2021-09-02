package com.rafal.caloriecounter.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rafal.caloriecounter.data.IngredientSearch
import com.rafal.caloriecounter.db.IngredientsDao
import com.rafal.caloriecounter.repos.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: SearchRepository,
    private val ingredientDao: IngredientsDao
) : ViewModel() {
    private val _searchIngredientsLiveData: MutableLiveData<PagingData<IngredientSearch>> =
        MutableLiveData()
    val searchIngredientsLiveData: LiveData<PagingData<IngredientSearch>> =
        _searchIngredientsLiveData

    fun searchIngredients(query: String, metaInformation: Boolean) {
        viewModelScope.launch() {
            repo.searchIngredients(query, metaInformation).cachedIn(viewModelScope).collect {
                _searchIngredientsLiveData.value = it
            }
        }
    }

    fun addIngredient(meal: Int, ingredient: IngredientSearch) {
        ingredient.meal = meal
        viewModelScope.launch {
            ingredientDao.insertAll(ingredient)
        }
    }
}
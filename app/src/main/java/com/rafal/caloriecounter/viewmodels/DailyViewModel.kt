package com.rafal.caloriecounter.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafal.caloriecounter.data.IngredientSearch
import com.rafal.caloriecounter.repos.DailyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val repo: DailyRepository
) : ViewModel() {
    private val _productsLiveDataArray: Array<MutableLiveData<List<IngredientSearch>>> =
        Array(5) { MutableLiveData<List<IngredientSearch>>() }
    val productsLiveDataArray: Array<LiveData<List<IngredientSearch>>> =
        Array(_productsLiveDataArray.size) { i -> _productsLiveDataArray[i] }

    fun loadProducts(mealID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val products = repo.loadProducts(mealID)
            _productsLiveDataArray[mealID].postValue(products)
        }
    }

    fun deleteProduct(ingredient: IngredientSearch, mealIndex: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.removeProduct(ingredient)
            _productsLiveDataArray[mealIndex].value?.let {
                val list = it.toMutableList()
                list.remove(ingredient)
                _productsLiveDataArray[mealIndex].postValue(list)
            }
        }
    }
}
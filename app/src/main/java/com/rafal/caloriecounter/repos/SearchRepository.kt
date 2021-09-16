package com.rafal.caloriecounter.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rafal.caloriecounter.api.FoodAPI
import com.rafal.caloriecounter.data.IngredientSearch
import com.rafal.caloriecounter.pagingsources.SearchIngredientPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: FoodAPI
) {
    fun searchIngredients(
        query: String,
        metaInformation: Boolean
    ): Flow<PagingData<IngredientSearch>> {
        return Pager(
            PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                maxSize = 100
            )
        ) {
            SearchIngredientPagingSource(api, query, metaInformation)
        }.flow
    }
}
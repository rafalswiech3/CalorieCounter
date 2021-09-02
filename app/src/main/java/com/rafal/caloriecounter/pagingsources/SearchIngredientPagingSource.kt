package com.rafal.caloriecounter.pagingsources

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rafal.caloriecounter.api.FoodAPI
import com.rafal.caloriecounter.data.IngredientSearch
import java.lang.Exception
import javax.inject.Inject

class SearchIngredientPagingSource(
    private val api: FoodAPI,
    private val query: String,
    private val metaInformation: Boolean
) : PagingSource<Int, IngredientSearch>() {
    override fun getRefreshKey(state: PagingState<Int, IngredientSearch>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, IngredientSearch> {
        val pos = params.key ?: 0

        return try {
            val response = api.searchIngredients(query, pos, metaInformation)
            val results = response.body()!!.results

            val resultsWithInfo = mutableListOf<IngredientSearch>()

            var index = 0
            results.forEach {
                if(index == 0) {
                    val response = api.getIngredientInfo(id = it.id, amount = 100)
                    resultsWithInfo.add(response.body()!!)
                } else {
                    resultsWithInfo.add(results[index])
                }
                index++
            }

            LoadResult.Page(
                data = resultsWithInfo,
                prevKey = if (pos == 0) null else pos - 10,
                nextKey = if (results.isEmpty()) null else pos + 10
            )
        } catch (exception: Exception) {
            Log.d("TAg", "Exception: ${exception.toString()}")
            return LoadResult.Error(exception)
        }
    }
}
package com.rafal.caloriecounter.api

import com.rafal.caloriecounter.data.IngredientSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FoodAPI {
    @GET("food/ingredients/search")
    suspend fun searchIngredients(
        @Query("query") query: String,
        @Query("offset") offset: Int,
        @Query("metaInformation") metaInformation: Boolean
    ) : Response<IngredientSearchResponse>
}
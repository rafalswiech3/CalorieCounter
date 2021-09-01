package com.rafal.caloriecounter.di

import android.content.Context
import androidx.room.Room
import com.rafal.caloriecounter.api.FoodAPI
import com.rafal.caloriecounter.db.AppDatabase
import com.rafal.caloriecounter.db.IngredientsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://api.spoonacular.com/"
const val API_KEY = "74bb70d489914e4a92cc323856dae0bf"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    var request = chain.request()
                    val url = request.url.newBuilder().addQueryParameter("apiKey", API_KEY).build()
                    request = request.newBuilder().url(url).build()
                    chain.proceed(request)
                }
            )
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit) : FoodAPI {
        return retrofit.create(FoodAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ingredients_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideIngredientsDao(db: AppDatabase): IngredientsDao {
        return db.ingredientDao()
    }


}
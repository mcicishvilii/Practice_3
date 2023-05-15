package com.example.practice_3.di

import android.content.Context
import com.example.practice_3.comon.Constants
import com.example.practice_3.data.service.MoviesService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    val gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideMovies(): MoviesService =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_MOVIE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MoviesService::class.java)

    @Singleton
    @Provides
    fun providesContext(@ApplicationContext context: Context): Context {
        return context
    }
}
package com.example.marvelassessmentapplication.data

import com.example.marvelassessmentapplication.domain.repository.MarvelRepository
import com.example.marvelassessmentapplication.domain.repository.MarvelRepositoryImplementation
import com.example.marvelassessmentapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiConfig {
    @Provides
    @Singleton
    fun provideMarvelApi():AppApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMarvelRepository(api:AppApi): MarvelRepository {
        return MarvelRepositoryImplementation(api)
    }
}
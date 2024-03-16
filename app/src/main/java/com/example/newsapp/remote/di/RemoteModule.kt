package com.example.newsapp.remote.di

import com.example.newsapp.common.util.Constants.BASE_URL
import com.example.newsapp.remote.data.remote.RemoteApi
import com.example.newsapp.remote.data.repository.RemoteRepository
import com.example.newsapp.remote.domain.repository.IRemoteRepository
import com.example.newsapp.remote.domain.use_cases.GetBreakingNews
import com.example.newsapp.remote.domain.use_cases.GetHomeNews
import com.example.newsapp.remote.domain.use_cases.GetSearchedNews
import com.example.newsapp.remote.domain.use_cases.GetTagNews
import com.example.newsapp.remote.domain.use_cases.RemoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideApi(): RemoteApi{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(api: RemoteApi): IRemoteRepository{
        return RemoteRepository(api)
    }

    @Provides
    @Singleton
    fun provideRemoteUseCases(repository: IRemoteRepository): RemoteUseCases{
        return RemoteUseCases(
            getBreakingNews = GetBreakingNews(repository),
            getTagNews = GetTagNews(repository),
            getSearchedNews = GetSearchedNews(repository),
            getHomeNews = GetHomeNews(repository)
        )
    }
}
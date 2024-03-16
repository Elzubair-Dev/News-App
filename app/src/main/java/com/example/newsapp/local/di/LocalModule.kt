package com.example.newsapp.local.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.local.data.data_source.LocalDatabase
import com.example.newsapp.local.data.repository.LocalRepository
import com.example.newsapp.local.domain.repository.ILocalRepository
import com.example.newsapp.local.domain.use_cases.DeleteArticle
import com.example.newsapp.local.domain.use_cases.GetArticles
import com.example.newsapp.local.domain.use_cases.InsertArticle
import com.example.newsapp.local.domain.use_cases.LocalUseCases
import com.example.newsapp.local.domain.use_cases.SearchForArticles
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideLocalDatabase(app: Application): LocalDatabase {
        return Room.databaseBuilder(
            app,
            LocalDatabase::class.java,
            LocalDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(db: LocalDatabase): ILocalRepository {
        return LocalRepository(db.localDao)
    }

    @Provides
    @Singleton
    fun provideLocalUseCases(repository: ILocalRepository): LocalUseCases {
        return LocalUseCases(
            getArticles = GetArticles(repository),
            searchForArticles = SearchForArticles(repository),
            insertArticle = InsertArticle(repository),
            deleteArticle = DeleteArticle(repository)
        )
    }
}
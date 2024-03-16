package com.example.newsapp.common.di

import android.app.Application
import com.example.newsapp.common.data.repository.UserInfo
import com.example.newsapp.common.domain.user_info.repository.IUserInfo
import com.example.newsapp.common.domain.user_info.use_cases.GetChangesFlag
import com.example.newsapp.common.domain.user_info.use_cases.GetUserFavCategory
import com.example.newsapp.common.domain.user_info.use_cases.GetUserFavCountryCode
import com.example.newsapp.common.domain.user_info.use_cases.GetUserFavTag
import com.example.newsapp.common.domain.user_info.use_cases.GetUserImage
import com.example.newsapp.common.domain.user_info.use_cases.GetUserName
import com.example.newsapp.common.domain.user_info.use_cases.SaveUserFavCategory
import com.example.newsapp.common.domain.user_info.use_cases.SaveUserFavCountryCode
import com.example.newsapp.common.domain.user_info.use_cases.SaveUserFavTag
import com.example.newsapp.common.domain.user_info.use_cases.SaveUserImage
import com.example.newsapp.common.domain.user_info.use_cases.SaveUserName
import com.example.newsapp.common.domain.user_info.use_cases.UpdateChangesFlag
import com.example.newsapp.common.domain.user_info.use_cases.UserInfoUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    @Singleton
    fun provideUserInfo(
        application: Application
    ): IUserInfo = UserInfo(application)

    @Provides
    @Singleton
    fun provideUserInfoUseCases(
        repository: IUserInfo
    ) = UserInfoUseCases(
        updateChangesFlag = UpdateChangesFlag(repository),
        getChangesFlag = GetChangesFlag(repository),
        saveUserName = SaveUserName(repository),
        getUserName = GetUserName(repository),
        saveUserImage = SaveUserImage(repository),
        getUserImage = GetUserImage(repository),
        saveUserFavCountryCode = SaveUserFavCountryCode(repository),
        getUserFavCountryCode = GetUserFavCountryCode(repository),
        saveUserFavCategory = SaveUserFavCategory(repository),
        getUserFavCategory = GetUserFavCategory(repository),
        saveUserFavTag = SaveUserFavTag(repository),
        getUserFavTag = GetUserFavTag(repository)
    )
}
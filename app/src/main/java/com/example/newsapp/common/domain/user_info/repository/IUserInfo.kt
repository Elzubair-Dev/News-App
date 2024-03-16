package com.example.newsapp.common.domain.user_info.repository

import kotlinx.coroutines.flow.Flow

interface IUserInfo {
    suspend fun updateChangesFlag(flag: Int)
    fun getChangesFlag(): Flow<Int>

    suspend fun saveUserName(name: String)
    fun getUserName(): Flow<String>

    suspend fun saveUserImage(image: String)
    fun getUserImage(): Flow<String?>

    suspend fun saveUserFavCountry(country: String)
    fun getUserCountry(): Flow<String>

    suspend fun saveUserFavCategory(category: String)
    fun getUserFavCategory(): Flow<String>

    suspend fun saveUserFavTag(tag: String)
    fun getUserFavTag(): Flow<String>
}
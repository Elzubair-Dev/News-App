package com.example.newsapp.common.domain.user_info.use_cases

import com.example.newsapp.common.domain.user_info.repository.IUserInfo

class SaveUserFavTag(
    private val repository: IUserInfo
) {
    suspend operator fun invoke(tag: String){
        repository.saveUserFavTag(tag)
    }
}
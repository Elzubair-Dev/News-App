package com.example.newsapp.common.domain.user_info.use_cases

import com.example.newsapp.common.domain.user_info.repository.IUserInfo

class SaveUserFavCategory (
    private val repository: IUserInfo
) {
    suspend operator fun invoke(category: String){
        repository.saveUserFavCategory(category)
    }
}
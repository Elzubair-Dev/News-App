package com.example.newsapp.common.domain.user_info.use_cases

import com.example.newsapp.common.domain.user_info.repository.IUserInfo

class SaveUserName(
    private val repository: IUserInfo
) {
    suspend operator fun invoke(name: String){
        repository.saveUserName(name)
    }
}
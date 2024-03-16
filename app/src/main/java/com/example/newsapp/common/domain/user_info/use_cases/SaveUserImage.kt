package com.example.newsapp.common.domain.user_info.use_cases

import com.example.newsapp.common.domain.user_info.repository.IUserInfo

class SaveUserImage(
    private val repository: IUserInfo
) {
    suspend operator fun invoke(image: String){
        repository.saveUserImage(image)
    }
}
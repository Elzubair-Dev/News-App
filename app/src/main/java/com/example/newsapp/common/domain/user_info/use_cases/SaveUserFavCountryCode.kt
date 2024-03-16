package com.example.newsapp.common.domain.user_info.use_cases

import com.example.newsapp.common.domain.user_info.repository.IUserInfo

class SaveUserFavCountryCode(
    private val repository: IUserInfo
) {
    suspend operator fun invoke(code: String){
        repository.saveUserFavCountry(code)
    }
}
package com.example.newsapp.common.domain.user_info.use_cases

import com.example.newsapp.common.domain.user_info.repository.IUserInfo

class UpdateChangesFlag(
    private val repository: IUserInfo
) {
    suspend operator fun invoke(flag: Int){
        repository.updateChangesFlag(flag)
    }
}
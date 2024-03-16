package com.example.newsapp.common.domain.user_info.use_cases

import com.example.newsapp.common.domain.user_info.repository.IUserInfo
import kotlinx.coroutines.flow.Flow

class GetUserFavTag (
    private val repository: IUserInfo
) {
    operator fun invoke(): Flow<String> {
        return repository.getUserFavTag()
    }
}
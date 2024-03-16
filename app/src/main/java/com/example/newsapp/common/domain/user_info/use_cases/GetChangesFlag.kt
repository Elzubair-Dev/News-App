package com.example.newsapp.common.domain.user_info.use_cases

import com.example.newsapp.common.domain.user_info.repository.IUserInfo
import kotlinx.coroutines.flow.Flow

class GetChangesFlag(
    private val repository: IUserInfo
) {
    operator fun invoke(): Flow<Int> {
        return repository.getChangesFlag()
    }
}
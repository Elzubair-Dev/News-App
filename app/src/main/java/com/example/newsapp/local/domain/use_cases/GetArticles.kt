package com.example.newsapp.local.domain.use_cases

import com.example.newsapp.local.domain.model.LocalArticle
import com.example.newsapp.local.domain.repository.ILocalRepository
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val repository: ILocalRepository
) {
    operator fun invoke(): Flow<List<LocalArticle>> {
        return repository.getArticles()
    }
}
package com.example.newsapp.local.domain.use_cases

import com.example.newsapp.local.domain.model.LocalArticle
import com.example.newsapp.local.domain.repository.ILocalRepository
import kotlinx.coroutines.flow.Flow

class SearchForArticles(
    private val repository: ILocalRepository
) {
    operator fun invoke(query: String): Flow<List<LocalArticle>> {
        return repository.searchForArticles(query)
    }
}
package com.example.newsapp.local.domain.use_cases

import com.example.newsapp.local.domain.model.LocalArticle
import com.example.newsapp.local.domain.repository.ILocalRepository

class DeleteArticle(
    private val repository: ILocalRepository
) {
    suspend operator fun invoke(article: LocalArticle){
        repository.deleteArticle(article)
    }
}
package com.example.newsapp.local.domain.use_cases

import com.example.newsapp.local.domain.model.LocalArticle
import com.example.newsapp.local.domain.repository.ILocalRepository

class InsertArticle(
    private val repository: ILocalRepository
) {
    suspend operator fun invoke(article: LocalArticle){
        repository.insertArticle(article)
    }
}
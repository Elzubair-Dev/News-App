package com.example.newsapp.local.domain.use_cases

data class LocalUseCases(
    val getArticles: GetArticles,
    val searchForArticles: SearchForArticles,
    val insertArticle: InsertArticle,
    val deleteArticle: DeleteArticle
)

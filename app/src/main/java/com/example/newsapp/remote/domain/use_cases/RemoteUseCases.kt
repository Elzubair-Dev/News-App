package com.example.newsapp.remote.domain.use_cases

data class RemoteUseCases(
    val getBreakingNews: GetBreakingNews,
    val getTagNews: GetTagNews,
    val getSearchedNews: GetSearchedNews,
    val getHomeNews: GetHomeNews
)

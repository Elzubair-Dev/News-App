package com.example.newsapp.remote.domain.model

import com.example.newsapp.common.data.remote.dto.Source
import com.example.newsapp.local.domain.model.LocalArticle

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)

fun Article.toLocalArticle(): LocalArticle{
    return LocalArticle(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

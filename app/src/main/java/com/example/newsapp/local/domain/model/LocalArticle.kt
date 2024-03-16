package com.example.newsapp.local.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.common.data.remote.dto.Source

@Entity
data class LocalArticle(
    @PrimaryKey
    val url: String,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val urlToImage: String?
)

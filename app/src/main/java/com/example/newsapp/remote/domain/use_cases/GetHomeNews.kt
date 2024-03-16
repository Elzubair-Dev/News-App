package com.example.newsapp.remote.domain.use_cases

import com.example.newsapp.common.util.Resource
import com.example.newsapp.remote.data.remote.dto.ArticleDto
import com.example.newsapp.remote.domain.repository.IRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHomeNews @Inject constructor(
    private val repository: IRemoteRepository
) {
    operator fun invoke(category: String, countryCode: String, tag: String): Flow<Resource<ArticleDto>> = flow {
        try {
            emit(Resource.Loading())
            val breakingNews =  repository.getBreakingNews(category = category, countryCode = countryCode)
            val tagNews =  repository.getTagNews(tag = tag)

            val news = listOf(breakingNews, tagNews)

            emit(Resource.Success(news))
        }catch (e: HttpException){
            emit(Resource.Error(emptyList(), e.localizedMessage ?: "unexpected error"))
        }catch (e: IOException){
            emit(Resource.Error(emptyList(), e.localizedMessage ?: "Check out your internet connection ..."))
        }
    }
}
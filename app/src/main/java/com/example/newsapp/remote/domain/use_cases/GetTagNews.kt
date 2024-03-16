package com.example.newsapp.remote.domain.use_cases

import com.example.newsapp.common.util.Resource
import com.example.newsapp.remote.data.remote.dto.ArticleDto
import com.example.newsapp.remote.domain.repository.IRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTagNews @Inject constructor(
    private val repository: IRemoteRepository
) {
    operator fun invoke(tag: String): Flow<Resource<ArticleDto>> = flow {
        try {
            emit(Resource.Loading())
            val tagNews = listOf( repository.getTagNews(tag = tag))
            emit(Resource.Success(tagNews))
        }catch (e: HttpException){
            emit(Resource.Error(emptyList(), e.localizedMessage ?: "unexpected error"))
        }catch (e: IOException){
            emit(Resource.Error(emptyList(), e.localizedMessage ?: "Check out your internet connection ..."))
        }
    }
}
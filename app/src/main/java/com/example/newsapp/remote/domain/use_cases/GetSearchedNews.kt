package com.example.newsapp.remote.domain.use_cases

import com.example.newsapp.common.util.Resource
import com.example.newsapp.remote.data.remote.dto.ArticleDto
import com.example.newsapp.remote.domain.repository.IRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSearchedNews @Inject constructor(
    private val repository: IRemoteRepository
) {
    operator fun invoke(query: String, sort: String): Flow<Resource<ArticleDto>> = flow {
        try {
            emit(Resource.Loading())
            val searchedNews =  listOf( repository.getSearchedNews(query = query, sort = sort))
            emit(Resource.Success(searchedNews))
        }catch (e: HttpException){
            emit(Resource.Error(emptyList(), e.localizedMessage ?: "unexpected error"))
        }catch (e: IOException){
            emit(Resource.Error(emptyList(), e.localizedMessage ?: "Check out your internet connection ..."))
        }
    }
}
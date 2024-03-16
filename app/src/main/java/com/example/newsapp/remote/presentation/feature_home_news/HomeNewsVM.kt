package com.example.newsapp.remote.presentation.feature_home_news

import android.graphics.Bitmap
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.common.domain.user_info.use_cases.UserInfoUseCases
import com.example.newsapp.common.util.Common.Categories
import com.example.newsapp.common.util.Common.Countries
import com.example.newsapp.common.util.Common.codeToCountry
import com.example.newsapp.common.util.Common.toBitmap
import com.example.newsapp.common.util.Common.toCategory
import com.example.newsapp.common.util.Resource
import com.example.newsapp.local.domain.use_cases.LocalUseCases
import com.example.newsapp.remote.domain.use_cases.RemoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@Stable
@HiltViewModel
class HomeNewsVM @Inject constructor(
    private val useCases: RemoteUseCases,
    private val bookmarkUseCases: LocalUseCases,
    private val userInfoUseCases: UserInfoUseCases
) : ViewModel() {
    private val _state = mutableStateOf(HomeNewsState())
    val state: State<HomeNewsState> = _state

    private var _greeting = ""
    private var _profileImageBitmap: Bitmap? = null
    private var _category = Categories[4]
    private var _country = Countries.first()
    private var _tag = Categories[4].mainTag

    var isUpdated = mutableStateOf(false)
        private set

    init {
        userInfoUseCases.getUserName().onEach {
            _greeting = "Hi, $it"
        }.launchIn(viewModelScope)

        userInfoUseCases.getUserImage().onEach {
            _profileImageBitmap = it.toBitmap()
        }.launchIn(viewModelScope)

        userInfoUseCases.getUserFavCountryCode().onEach {
            _country = it.codeToCountry()
        }.launchIn(viewModelScope)

        userInfoUseCases.getUserFavCategory().onEach {
            _category = it.toCategory()
        }.launchIn(viewModelScope)

        userInfoUseCases.getUserFavTag().onEach {
            _tag = it
        }.launchIn(viewModelScope)

        _state.value = state.value.copy(
            greeting = _greeting,
            category = _category.title.lowercase(),
            countryCode = _country.code,
            tag = _tag,
            profileImageBitmap = _profileImageBitmap
        )


        getAllNews(
            countryCode = _state.value.countryCode,
            category = _state.value.category,
            tag = _tag.lowercase()
        )
    }

    fun onEvent(event: HomeNewsEvents) {
        when (event) {

            is HomeNewsEvents.SaveArticle -> {
                viewModelScope.launch {
                    bookmarkUseCases.insertArticle(
                        event.article
                    )
                }
            }

            is HomeNewsEvents.UpdateTag -> {
                _state.value = state.value.copy(
                    greeting = _greeting,
                    category = _category.title.lowercase(),
                    countryCode = _country.code,
                    tag = _tag,
                    profileImageBitmap = _profileImageBitmap
                )

                getAllNews(
                    countryCode = _state.value.countryCode,
                    category = _state.value.category,
                    tag = event.tag.lowercase()
                )

                viewModelScope.launch {
                    userInfoUseCases.saveUserFavTag(event.tag)
                    userInfoUseCases.updateChangesFlag(1)
                }

            }

            is HomeNewsEvents.Update -> {


                userInfoUseCases.getChangesFlag().onEach {
                    isUpdated.value = it >= 1
                }.launchIn(viewModelScope)




                if (isUpdated.value){



                    _state.value = state.value.copy(
                        greeting = _greeting,
                        category = _category.title.lowercase(),
                        countryCode = _country.code,
                        tag = _tag,
                        profileImageBitmap = _profileImageBitmap
                    )

                    getAllNews(
                        countryCode = _state.value.countryCode,
                        category = _state.value.category,
                        tag = _tag.lowercase()
                    )

                    viewModelScope.launch {
                        userInfoUseCases.updateChangesFlag(0)
                    }
                }
                else{
                    userInfoUseCases.getUserName().onEach {
                        _greeting = "Hi, $it"
                    }.launchIn(viewModelScope)

                    userInfoUseCases.getUserImage().onEach {
                        _profileImageBitmap = it.toBitmap()
                    }.launchIn(viewModelScope)

                    _state.value = state.value.copy(
                        greeting = _greeting,
                        category = _category.title.lowercase(),
                        countryCode = _country.code,
                        tag = _tag,
                        profileImageBitmap = _profileImageBitmap
                    )
                }

            }
        }
    }

    private fun getAllNews(
        countryCode: String,
        category: String,
        tag: String
    ) {
        useCases.getHomeNews(
            countryCode = countryCode,
            category = category,
            tag = tag
        )
            .onEach { resource ->
                when (resource) {

                    is Resource.Success -> {
                        _state.value = HomeNewsState(
                            breakingArticles = resource.data.first()?.articles ?: emptyList(),
                            tagArticles = resource.data.last()?.articles ?: emptyList(),
                            greeting = _state.value.greeting,
                            profileImageBitmap = _state.value.profileImageBitmap,
                            category = category,
                            tag = tag
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = HomeNewsState(
                            isLoading = true,
                            greeting = _greeting,
                            category = category,
                            tag = tag,
                            profileImageBitmap = _profileImageBitmap
                        )
                    }

                    is Resource.Error -> {
                        _state.value =
                            HomeNewsState(
                                error = resource.message ?: "Unexpected error ...",
                                greeting = _greeting,
                                category = category,
                                tag = tag,
                                profileImageBitmap = _profileImageBitmap
                            )
                    }
                }
            }.launchIn(viewModelScope)
    }
}
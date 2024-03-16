package com.example.newsapp.local.presentation.feature_profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.common.domain.user_info.use_cases.UserInfoUseCases
import com.example.newsapp.common.util.Common.encodeToString
import com.example.newsapp.common.util.Common.toBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileVM @Inject constructor(
    private val userInfoUseCases: UserInfoUseCases
) : ViewModel() {
    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state


    init {
        userInfoUseCases.getUserName().onEach {
            _state.value = state.value.copy(
                name = it
            )
        }.launchIn(viewModelScope)

        userInfoUseCases.getUserImage().onEach {
            _state.value = state.value.copy(
                image = it.toBitmap()
            )
        }.launchIn(viewModelScope)


        userInfoUseCases.getUserFavCountryCode().onEach {
            _state.value = state.value.copy(
                countryCode = it
            )
        }.launchIn(viewModelScope)

        userInfoUseCases.getUserFavCategory().onEach {
            _state.value = state.value.copy(
                category = it
            )
        }.launchIn(viewModelScope)

        userInfoUseCases.getUserFavTag().onEach {
            _state.value = state.value.copy(
                tag = it
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ProfileEvents) {
        when (event) {
            is ProfileEvents.ChangeImage -> {
                if (event.image != _state.value.image){
                    _state.value = state.value.copy(
                        image = event.image
                    )

                    viewModelScope.launch {
                        userInfoUseCases.saveUserImage(event.image.encodeToString())
                    }
                }
            }

            is ProfileEvents.ChooseCategory -> {
               if (event.category.title != _state.value.category){
                   _state.value = state.value.copy(
                       category = event.category.title
                   )

                   viewModelScope.launch {
                       userInfoUseCases.saveUserFavCategory(event.category.title.lowercase())
                       userInfoUseCases.saveUserFavTag(event.category.mainTag)
                       userInfoUseCases.updateChangesFlag(1)
                   }
               }
            }

            is ProfileEvents.ChooseCountry -> {
               if (event.countryCode != _state.value.countryCode){
                   _state.value = state.value.copy(
                       countryCode = event.countryCode
                   )


                   viewModelScope.launch {
                       userInfoUseCases.saveUserFavCountryCode(event.countryCode)
                       userInfoUseCases.updateChangesFlag(1)
                   }
               }
            }

            is ProfileEvents.ChooseTag -> {
                if (event.tag != _state.value.tag){
                    _state.value = state.value.copy(
                        tag = event.tag
                    )

                    viewModelScope.launch {
                        userInfoUseCases.saveUserFavTag(event.tag)
                        userInfoUseCases.updateChangesFlag(1)
                    }
                }
            }

            is ProfileEvents.EnterName -> {
                if (event.name != _state.value.name){
                    _state.value = state.value.copy(
                        name = event.name
                    )
                    viewModelScope.launch {
                        userInfoUseCases.saveUserName(event.name)
                    }
                }

            }
        }
    }
}
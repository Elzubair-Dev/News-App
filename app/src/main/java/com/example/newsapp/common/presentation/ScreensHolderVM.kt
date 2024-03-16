package com.example.newsapp.common.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.R
import com.example.newsapp.common.domain.user_info.use_cases.UserInfoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreensHolderVM @Inject constructor(
    private val userInfoUseCases: UserInfoUseCases
) : ViewModel() {
    private val _darkTheme = mutableStateOf(false)
    val darkTheme: State<Boolean> = _darkTheme

    private val _themeIcon = mutableIntStateOf(R.drawable.baseline_bedtime_24)
    val themeIcon: State<Int> = _themeIcon

    init {
        viewModelScope.launch {
            userInfoUseCases.updateChangesFlag(0)
        }
    }

    fun onDarkThemeChange() {
        _darkTheme.value = !darkTheme.value

        _themeIcon.intValue = if (_darkTheme.value) {
            R.drawable.baseline_wb_sunny_24
        } else {
            R.drawable.baseline_bedtime_24
        }
    }
}
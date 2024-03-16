package com.example.newsapp.local.presentation.feature_profile


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.common.util.Common.BusinessTags
import com.example.newsapp.common.util.Common.EntertainmentTags
import com.example.newsapp.common.util.Common.GeneralTags
import com.example.newsapp.common.util.Common.HealthTags
import com.example.newsapp.common.util.Common.ScienceTags
import com.example.newsapp.common.util.Common.SportsTags
import com.example.newsapp.common.util.Common.TechnologyTags
import com.example.newsapp.common.util.Tag
import com.example.newsapp.local.presentation.feature_profile.components.LandscapeProfileContent
import com.example.newsapp.local.presentation.feature_profile.components.PortraitProfileContent

@Composable
fun ProfileScreen(
    navController: NavController,
    isPortrait: Boolean,
    iconId: Int,
    onThemeIconClick: () -> Unit,
    viewModel: ProfileVM = hiltViewModel()
) {
    val state = viewModel.state.value

    val tags: List<Tag> = when (state.category) {
        "business" -> {
            BusinessTags
        }

        "entertainment" -> {
            EntertainmentTags
        }

        "health" -> {
            HealthTags
        }

        "science" -> {
            ScienceTags
        }

        "sports" -> {
            SportsTags
        }

        "technology" -> {
            TechnologyTags
        }

        else -> {
            GeneralTags
        }
    }

    if (isPortrait) {
        PortraitProfileContent(
            navController = navController,
            iconId = iconId,
            name = state.name,
            tags = tags,
            image = state.image,
            currentCountryCode = state.countryCode,
            currentCategory = state.category,
            currentTag = state.tag,
            onImageChange = { image ->
                viewModel.onEvent(ProfileEvents.ChangeImage(image))
            },
            onEnterName = { name ->
                viewModel.onEvent(ProfileEvents.EnterName(name))
            },
            onCountryClick = { code ->
                viewModel.onEvent(ProfileEvents.ChooseCountry(code))
            },
            onTagClick = { tag ->
                viewModel.onEvent(ProfileEvents.ChooseTag(tag))
            },
            onCategoryClick = { category ->
                viewModel.onEvent(ProfileEvents.ChooseCategory(category))
            },
            onThemeIconClick = onThemeIconClick
        )
    }
    else{
        LandscapeProfileContent(
            navController = navController,
            iconId = iconId,
            name = state.name,
            tags = tags,
            image = state.image,
            currentCountryCode = state.countryCode,
            currentCategory = state.category,
            currentTag = state.tag,
            onImageChange = { image ->
                viewModel.onEvent(ProfileEvents.ChangeImage(image))
            },
            onEnterName = { name ->
                viewModel.onEvent(ProfileEvents.EnterName(name))
            },
            onCountryClick = { code ->
                viewModel.onEvent(ProfileEvents.ChooseCountry(code))
            },
            onTagClick = { tag ->
                viewModel.onEvent(ProfileEvents.ChooseTag(tag))
            },
            onCategoryClick = { category ->
                viewModel.onEvent(ProfileEvents.ChooseCategory(category))
            },
            onThemeIconClick = onThemeIconClick
        )
    }
}
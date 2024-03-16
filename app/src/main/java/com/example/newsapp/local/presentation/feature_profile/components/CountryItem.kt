package com.example.newsapp.local.presentation.feature_profile.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.common.util.Country

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun CountryItem(
    country: Country,
    currentCountryCode: String,
    onCountryClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.secondary)
            .clickable {
                onCountryClick(country.code)
            }
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedContent(
            targetState = currentCountryCode == country.code,
            transitionSpec = { slideInHorizontally { it } togetherWith slideOutHorizontally { it } },
            label = ""
        ) { isVisible ->
            if (isVisible) {
                Image(
                    painter = painterResource(id = country.flag),
                    contentDescription = country.name,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }

        Spacer(modifier = Modifier.padding(horizontal = 3.dp))

        Text(
            text = country.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
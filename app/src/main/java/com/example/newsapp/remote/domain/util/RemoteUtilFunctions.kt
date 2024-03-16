package com.example.newsapp.remote.domain.util

import com.example.newsapp.R
import com.example.newsapp.common.util.Common

object RemoteUtilFunctions {
    fun placeholderID(category: String): Int{
        return when(category){
            "business" -> {
                R.drawable.business_placeholder
            }

            "entertainment" -> {
                R.drawable.movies_placeholder
            }

            "health" -> {
                R.drawable.healthcare_placeholder
            }

            "science" -> {
                R.drawable.science_placeholder
            }

            "sports" -> {
                R.drawable.sports_placeholder
            }

            "technology" -> {
                R.drawable.tech_placeholder
            }

            else -> {
                R.drawable.news_placeholder
            }
        }
    }
}
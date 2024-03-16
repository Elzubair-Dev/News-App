package com.example.newsapp.common.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.common.data.repository.DatastoreKeys.CHANGES_FLAG
import com.example.newsapp.common.data.repository.DatastoreKeys.USER_FAV_CATEGORY
import com.example.newsapp.common.data.repository.DatastoreKeys.USER_FAV_COUNTRY_CODE
import com.example.newsapp.common.data.repository.DatastoreKeys.USER_FAV_TAG
import com.example.newsapp.common.data.repository.DatastoreKeys.USER_IMAGE
import com.example.newsapp.common.data.repository.DatastoreKeys.USER_NAME
import com.example.newsapp.common.domain.user_info.repository.IUserInfo
import com.example.newsapp.common.util.Constants.CATEGORY
import com.example.newsapp.common.util.Constants.COUNTRY_CODE
import com.example.newsapp.common.util.Constants.FLAG
import com.example.newsapp.common.util.Constants.IMAGE
import com.example.newsapp.common.util.Constants.NAME
import com.example.newsapp.common.util.Constants.TAG
import com.example.newsapp.common.util.Constants.USER_INFO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserInfo(
    private val context: Context
): IUserInfo {
    override suspend fun updateChangesFlag(flag: Int) {
        context.datastore.edit { preference ->
            preference[CHANGES_FLAG] = flag

        }
    }

    override fun getChangesFlag(): Flow<Int> {
        return context.datastore.data.map { preference ->
            preference[CHANGES_FLAG] ?: 0
        }
    }

    override suspend fun saveUserName(name: String) {
        context.datastore.edit { preference ->
            preference[USER_NAME] = name

        }
    }

    override fun getUserName(): Flow<String> {
        return context.datastore.data.map { preference ->
            preference[USER_NAME] ?: "Sir"
        }
    }

    override suspend fun saveUserImage(image: String) {
        context.datastore.edit { preference ->
            preference[USER_IMAGE] = image
        }
    }

    override fun getUserImage(): Flow<String?> {
        return context.datastore.data.map { preference ->
            preference[USER_IMAGE]
        }
    }

    override suspend fun saveUserFavCountry(country: String) {
        context.datastore.edit { preference ->
            preference[USER_FAV_COUNTRY_CODE] = country
        }
    }

    override fun getUserCountry(): Flow<String> {
        return context.datastore.data.map { preference ->
            preference[USER_FAV_COUNTRY_CODE] ?: "gb"
        }
    }

    override suspend fun saveUserFavCategory(category: String) {
        context.datastore.edit { preference ->
            preference[USER_FAV_CATEGORY] = category
        }
    }

    override fun getUserFavCategory(): Flow<String> {
        return context.datastore.data.map { preference ->
            preference[USER_FAV_CATEGORY] ?: "general"
        }
    }

    override suspend fun saveUserFavTag(tag: String) {
        context.datastore.edit { preference ->
            preference[USER_FAV_TAG] = tag
        }
    }

    override fun getUserFavTag(): Flow<String> {
        return context.datastore.data.map { preference ->
            preference[USER_FAV_TAG] ?: "discovery"
        }
    }
}

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(USER_INFO)

private object DatastoreKeys{
    val CHANGES_FLAG = intPreferencesKey(FLAG)
    val USER_NAME = stringPreferencesKey(NAME)
    val USER_IMAGE = stringPreferencesKey(IMAGE)
    val USER_FAV_COUNTRY_CODE = stringPreferencesKey(COUNTRY_CODE)
    val USER_FAV_CATEGORY = stringPreferencesKey(CATEGORY)
    val USER_FAV_TAG = stringPreferencesKey(TAG)
}
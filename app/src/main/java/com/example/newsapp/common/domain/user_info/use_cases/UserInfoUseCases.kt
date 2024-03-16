package com.example.newsapp.common.domain.user_info.use_cases

data class UserInfoUseCases(
    val updateChangesFlag: UpdateChangesFlag,
    val getChangesFlag: GetChangesFlag,
    val saveUserName: SaveUserName,
    val getUserName: GetUserName,
    val saveUserImage: SaveUserImage,
    val getUserImage: GetUserImage,
    val saveUserFavCountryCode: SaveUserFavCountryCode,
    val getUserFavCountryCode: GetUserFavCountryCode,
    val saveUserFavCategory: SaveUserFavCategory,
    val getUserFavCategory: GetUserFavCategory,
    val saveUserFavTag: SaveUserFavTag,
    val getUserFavTag: GetUserFavTag
)

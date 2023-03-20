package ru.juraogurcov.multitool.ui.person

import android.graphics.drawable.Drawable

data class UserInfo(
    val firstNameUser: String?,
    val secondNameUser: String?,
    val thirdNameUser: String?,
    val dayOfBirthUser: String?
)
data class UserImage(
    val avatar: Drawable
)
package ru.juraogurcov.multitool.ui.person

import android.graphics.drawable.Drawable
import android.text.style.LineHeightSpan

data class UserInfo(
    val firstNameUser: String?,
    val secondNameUser: String?,
    val thirdNameUser: String?,
    val dayOfBirthUser: String?
)
data class UserImage(
    val id: String?,
    val url: String?,
    val width: Int?,
    val height: Int?
)
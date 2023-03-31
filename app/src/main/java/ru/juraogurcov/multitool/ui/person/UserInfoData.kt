package ru.juraogurcov.multitool.ui.person

data class UserInfoData(
    val firstNameUser: String?,
    val secondNameUser: String?,
    val thirdNameUser: String?,
    val dayOfBirthUser: String?
)
data class UserImageData(
    val id: String?,
    val url: String?,
    val width: Int?,
    val height: Int?
)
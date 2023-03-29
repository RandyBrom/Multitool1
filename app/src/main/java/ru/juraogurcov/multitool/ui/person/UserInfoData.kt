package ru.juraogurcov.multitool.ui.person

data class UserInfoData(
    val firstNameUser: String?,
    val secondNameUser: String?,
    val thirdNameUser: String?,
    val dayOfBirthUser: String?
)
data class UserImageData(
    var id: String?,
    var url: String?,
    var width: Int?,
    var height: Int?
)
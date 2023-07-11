package ru.juraogurcov.multitool.data


data class UserInfoData(
    var firstNameUser: String = "",
    var secondNameUser: String = "",
    var thirdNameUser: String = "",
    var dayOfBirthUser: String = ""
)

data class UserImageData(
    var id: String,
    var url: String
)
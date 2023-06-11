package ru.juraogurcov.multitool.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("UserInfoData")
data class UserInfoData(
    @PrimaryKey(false)
    var firstNameUser: String = "",
    var secondNameUser: String = "",
    var thirdNameUser: String = "",
    var dayOfBirthUser: String = ""
)

@Entity("UserImageData")
data class UserImageData(
    @PrimaryKey(false)
    var id: String,
    var url: String
)
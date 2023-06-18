package ru.juraogurcov.multitool.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("UserInfoData")
data class UserInfoData(
    @PrimaryKey(false)
    var id:Int? = 0 ,
    @ColumnInfo("firstNameUser")
    var firstNameUser: String = "",
    @ColumnInfo("secondNameUser")
    var secondNameUser: String = "",
    @ColumnInfo("thirdNameUser")
    var thirdNameUser: String = "",
    @ColumnInfo("dayOfBirthUser")
    var dayOfBirthUser: String = ""
)

@Entity("UserImageData")
data class UserImageData(
    @PrimaryKey(false)
    var id: String,
    var url: String
)
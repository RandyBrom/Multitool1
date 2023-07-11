package ru.juraogurcov.multitool.ui.person

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.juraogurcov.multitool.AppContext
import ru.juraogurcov.multitool.data.UserImageData
import ru.juraogurcov.multitool.data.UserInfoData

class PersonViewModel() : ViewModel() {
    val firstNameKey = "FIRST_NAME"
    val secondNameKey = "SECOND_NAME"
    val thirdNameKey = "THIRD_NAME"
    val dateOfBirthKey = "DATE_OF_BIRTH_NAME"

    companion object {
        private val _imageProfileInfo = MutableLiveData<UserImageData>()
        private val _userProfileInfo = MutableLiveData<UserInfoData>()
        private val fileUserInfo =
            AppContext.getAppContext().getSharedPreferences("AccountInfo", Context.MODE_PRIVATE)

    }

    fun setUserAvatarInfo(userAvatarInfo: UserImageData) {  // sending package of data to class with list of data types
        _imageProfileInfo.value = userAvatarInfo
    }

    val imageProfileInfo: LiveData<UserImageData> = _imageProfileInfo
    val userProfileInfo: LiveData<UserInfoData> = _userProfileInfo
    fun setUserInfo(userInfoData: UserInfoData) =
        viewModelScope.launch {
            fileUserInfo.edit().putString(firstNameKey, userInfoData.firstNameUser).apply()
            fileUserInfo.edit().putString(secondNameKey, userInfoData.secondNameUser).apply()
            fileUserInfo.edit().putString(thirdNameKey, userInfoData.thirdNameUser).apply()
            fileUserInfo.edit().putString(dateOfBirthKey, userInfoData.dayOfBirthUser).apply()
            _userProfileInfo.value = userInfoData
        }

    fun getUserInfo(): UserInfoData {
        val userInfoData = UserInfoData(
            fileUserInfo.getString(firstNameKey, "")!!,
            fileUserInfo.getString(secondNameKey, "")!!,
            fileUserInfo.getString(thirdNameKey, "")!!,
            fileUserInfo.getString(dateOfBirthKey, "")!!
        )
        _userProfileInfo.value = userInfoData
        return userInfoData
    }


}
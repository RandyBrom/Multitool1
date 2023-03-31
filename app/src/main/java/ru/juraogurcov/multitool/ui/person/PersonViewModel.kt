package ru.juraogurcov.multitool.ui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonViewModel : ViewModel() {
    fun setUserInfo(userInfoData: UserInfoData) {  // sending package of data to class with list of data types
        _textUserInfoData.value = userInfoData
    }
    private val _textUserInfoData = MutableLiveData<UserInfoData>()

    companion object {
        fun setUserAvatarInfo(userAvatarInfo: UserImageData) {  // sending package of data to class with list of data types
            _imageProfileInfo.value = userAvatarInfo
        }

        private val _imageProfileInfo = MutableLiveData<UserImageData>()
    }
    val textUserInfoData: LiveData<UserInfoData> = _textUserInfoData
    val imageProfileInfo: LiveData<UserImageData> = _imageProfileInfo

}
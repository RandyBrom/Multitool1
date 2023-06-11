package ru.juraogurcov.multitool.ui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.juraogurcov.multitool.data.Dao
import ru.juraogurcov.multitool.data.UserImageData
import ru.juraogurcov.multitool.data.UserInfoData

class PersonViewModel(private val dao: Dao) : ViewModel() {
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

    private val _state = dao.getUserInfo()
    val state = _state
    fun saveUserFirstName(name: String) {
        viewModelScope.launch {
            dao.replaceUserInfo(UserInfoData(firstNameUser = name))
        }

    }

    fun saveUserSecondName(name: String) {
        viewModelScope.launch {
            dao.replaceUserInfo(UserInfoData(secondNameUser = name))
        }

    }

    fun saveUserThirdName(name: String) {
        viewModelScope.launch {
            dao.replaceUserInfo(UserInfoData(thirdNameUser = name))
        }

    }

    fun saveUserDOB(dOB: String) {
        viewModelScope.launch {
            dao.replaceUserInfo(UserInfoData(dayOfBirthUser = dOB))
        }

    }

}
package ru.juraogurcov.multitool.ui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.juraogurcov.multitool.data.Dao
import ru.juraogurcov.multitool.data.MainBD
import ru.juraogurcov.multitool.data.UserImageData
import ru.juraogurcov.multitool.data.UserInfoData

class PersonViewModel(mainBD: MainBD) : ViewModel() {

    companion object {
        fun setUserAvatarInfo(userAvatarInfo: UserImageData) {  // sending package of data to class with list of data types
            _imageProfileInfo.value = userAvatarInfo
        }

        private val _imageProfileInfo = MutableLiveData<UserImageData>()
    }

    val imageProfileInfo: LiveData<UserImageData> = _imageProfileInfo
    private val _dao = mainBD.getDao()

    val _state = _dao.getUserInfo().asLiveData()
    val state = _state

    fun checkState(state: LiveData<UserInfoData>): LiveData<UserInfoData> {
        return if (state.value?.firstNameUser == null)
            _state
        else
            state
    }

    fun saveUserFirstName(name: String) {
        viewModelScope.launch {
            _dao.replaceUserInfo(UserInfoData(firstNameUser = name))
            val _state = _dao.getUserInfo().asLiveData()
            println(name)
            println(_state.value?.firstNameUser)
            println(_dao.getUserInfo().asLiveData().value?.firstNameUser)
        }
    }
//
//    fun saveUserSecondName(name: String) {
//        viewModelScope.launch {
//            dao.replaceUserInfo(UserInfoData(secondNameUser = name))
//        }
//
//    }
//
//    fun saveUserThirdName(name: String) {
//        viewModelScope.launch {
//            dao.replaceUserInfo(UserInfoData(thirdNameUser = name))
//        }
//
//    }
//
//    fun saveUserDOB(dOB: String) {
//        viewModelScope.launch {
//            dao.replaceUserInfo(UserInfoData(dayOfBirthUser = dOB))
//        }
//
//    }

}
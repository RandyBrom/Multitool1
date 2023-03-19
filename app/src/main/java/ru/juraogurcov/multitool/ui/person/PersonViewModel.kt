package ru.juraogurcov.multitool.ui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.juraogurcov.multitool.R
import ru.juraogurcov.multitool.ui.more.UserInfo

class PersonViewModel : ViewModel() {
    fun setUserInfo(userInfo: UserInfo) {  // sending package of data to class with list of data types
        _textUserInfo.value = userInfo

    }
    private val _textUserInfo = MutableLiveData<UserInfo>()

    private val _imageProfileId = MutableLiveData<Int>().apply {
        value = R.drawable.ic_person_button
    }

    val textUserInfo: LiveData<UserInfo> = _textUserInfo
    val imageProfileId: LiveData<Int> = _imageProfileId
}
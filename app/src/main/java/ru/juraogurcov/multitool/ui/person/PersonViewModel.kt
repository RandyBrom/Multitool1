package ru.juraogurcov.multitool.ui.person

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.juraogurcov.multitool.R
import ru.juraogurcov.multitool.ui.more.UserInfo

class PersonViewModel : ViewModel() {
    fun setUserInfo(userInfo: UserInfo) {
        _text.value = userInfo
    }
    private val _text = MutableLiveData<UserInfo>()

    private val _imageProfileId = MutableLiveData<Int>().apply {
        value = R.drawable.ic_person_button
    }

    val text: LiveData<UserInfo> = _text
    val imageProfileId: LiveData<Int> = _imageProfileId
}
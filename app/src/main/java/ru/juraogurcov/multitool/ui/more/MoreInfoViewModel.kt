package ru.juraogurcov.multitool.ui.more

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.juraogurcov.multitool.R

class MoreInfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = R.string.more_btn.toString()
    }
    val text: LiveData<String> = _text
}
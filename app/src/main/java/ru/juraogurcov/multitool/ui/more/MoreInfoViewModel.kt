package ru.juraogurcov.multitool.ui.more

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.juraogurcov.multitool.R

class MoreInfoViewModel : ViewModel() {

    private val _text = MutableLiveData<Int>().apply {
        value = R.string.more_btn
    }
    val text: LiveData<Int> = _text
}
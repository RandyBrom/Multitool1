package ru.juraogurcov.multitool.ui.person

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.juraogurcov.multitool.R

class PersonViewModel : ViewModel() {

    private val _text = MutableLiveData<Int>().apply {
         value = R.string.person_btn
    }
    private val _imageProfileId = MutableLiveData<Int>().apply {
        value = R.drawable.ic_person_button
    }
    val text: LiveData<Int> = _text
    val imageProfileId: LiveData<Int> = _imageProfileId
}
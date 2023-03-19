package ru.juraogurcov.multitool.viewUtils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.addTextChangeListener(onChange: (CharSequence?) -> Unit ) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onChange.invoke(s)
        }

        override fun afterTextChanged(s: Editable?) {

        }

    })

}
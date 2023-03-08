package ru.juraogurcov.multitool

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import ru.juraogurcov.multitool.MainActivity.Companion.key

class TestActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val mainText = findViewById<TextView>(R.id.text2)
        mainText.text = intent.getStringExtra(key)
    }
}
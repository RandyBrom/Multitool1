package ru.juraogurcov.multitool

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    companion object{
        val key = "boba"
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val personButton = findViewById<Button>(R.id.percent)
        val mainText = findViewById<EditText>(R.id.editText)
        personButton.setOnClickListener {
            val text = mainText.text
            val intent = Intent(this, TestActivity :: class.java)
            intent.putExtra(key, text.toString())
            startActivity(intent)
        }

    }

}
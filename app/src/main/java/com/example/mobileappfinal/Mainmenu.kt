package com.example.mobileappfinal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.TextView

class Mainmenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)

        val startbutton = findViewById<TextView>(R.id.Start_button)
        startbutton.text = "Start"

        val Titletext = findViewById<TextView>(R.id.TitleText)
        Titletext.text = "Pew Pew Bang Bang"

        startbutton.setOnClickListener {
            goMainActivity()
        }
    }


    fun goMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

package com.example.mobileappfinal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.TextView

class Mainmenu : AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_mainmenu)

            val startbutton = findViewById<TextView>(R.id.startbutton)
            startbutton.text = "Start"

            val Titletext = findViewById<TextView>(R.id.Titletext)
            Titletext.text = "Pew Pew Bang Bang"

            startbutton.setOnClickListener {
                goGamescreen()
            }
        }


         fun goGamescreen() {
            val intent = Intent(this, Gamescreen::class.java)
            startActivity(intent)
        }
    }


package com.example.mobileappfinal

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class Gameover : AppCompatActivity() {
    lateinit var tvPoints: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameover)

        val points: Int = getIntent().getExtras()!!.getInt("points")
        tvPoints = findViewById(R.id.tvPoints)
        tvPoints.setText("" + points)

        val retrybutton = findViewById<TextView>(R.id.Retry_button)
        retrybutton.text = "Retry"

        val Titletext = findViewById<TextView>(R.id.GameOverText)
        Titletext.text = "Game Over"

        val exitbutton = findViewById<TextView>(R.id.exit_button)
        exitbutton.text = "Main Menu"

        retrybutton.setOnClickListener {
            goMainActivity()
        }
        exitbutton.setOnClickListener {
            exitGame()
        }
    }



    fun goMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun exitGame(){

        val intent = Intent(this, Mainmenu::class.java)
        startActivity(intent)
    }
}
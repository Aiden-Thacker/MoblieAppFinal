package com.example.mobileappfinal

import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
//    var screenWidth: Int = 0
//    var screenHeight: Int = 0
//    lateinit var  player: Player






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Level(this))
    }



}


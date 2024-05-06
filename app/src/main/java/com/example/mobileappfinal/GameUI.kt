package com.example.mobileappfinal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class GameUI : AppCompatActivity() {

    private var paused : Boolean = false
    private val context: Context = applicationContext
    private var handler: Handler? = null
    private var screenWidth: Int = 0
    var screenHeight: Int = 0
    private lateinit var shipHealth: Bitmap
    private var points = 0
    private var life = 3
    private val textSize = 80
    private val scorePaint = Paint().apply {
        color = Color.RED
        textSize = textSize
        textAlign = Paint.Align.LEFT
    }



    fun playerHealth() {

        @SuppressLint("DrawAllocation")
        fun onDraw(canvas: Canvas) {
            //Draw the players health and points
            shipHealth = BitmapFactory.decodeResource(context.resources, R.drawable.ship)
            canvas.drawText("Pts: $points", 0f, textSize.toFloat(), scorePaint)

            for (i in life downTo 1) {
                canvas.drawBitmap(shipHealth, (screenWidth - shipHealth.width * i).toFloat(), 0f, null)
            }

            //When the player loses all lives go to game over screen
            if (life == 0) {
                paused = true
                handler = null
                //val intent = Intent(context, GameOver::class.java)
                intent.putExtra("points", points)
                context.startActivity(intent)
                (context as Activity).finish()
            }

        }

    }
}








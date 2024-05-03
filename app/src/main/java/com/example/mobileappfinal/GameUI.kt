package com.example.mobileappfinal

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
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

    fun playerHealth() {

        @SuppressLint("DrawAllocation")
        fun onDraw(canvas: Canvas) {
            //Draw the players health
            shipHealth = BitmapFactory.decodeResource(context.resources, R.drawable.ship)


            /*for (i in life downTo 1) {
                canvas.drawBitmap(shipHealth, screenWidth - shipHealth.width * i, 0f, null)
            }*/

            if (life == 0) {
                paused = true
                handler = null
                /*val intent = Intent(context, GameOver::class.java)
                intent.putExtra("points", points)
                context.startActivity(intent)
                (context as Activity).finish() */
            }

        }

    }
}








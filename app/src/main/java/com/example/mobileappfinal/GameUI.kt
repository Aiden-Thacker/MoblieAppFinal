package com.example.mobileappfinal

import android.content.Context
import android.content.Intent
import android.graphics.Canvas

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.os.Handler

class GameUI : AppCompatActivity() {
//An attempt to Draw score and background to Canvas
    override fun onDraw(canvas: Canvas){
        canvas.drawBitmap(Bitmap, 0, 0)
        canvas.drawText("Score: ", 0, 0, scorePaint)







    }

    private var paused : Boolean = false
    private val context: Context = applicationContext
    private var handler: Handler? = null
    private var points = 0
    private var life = 3

    fun playerHealth()
    {




        if (life == 0) {
            paused = true
            handler = null
            val intent = Intent(context, GameOver::class.java)
            intent.putExtra("points", points)
            context.startActivity(intent)
            (context as Activity).finish()
        }

    }




}






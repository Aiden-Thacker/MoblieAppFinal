package com.example.mobileappfinal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.os.Handler
import android.os.Looper
import android.view.Display
import android.view.MotionEvent
import android.view.View

import java.util.ArrayList
import java.util.Random
class Level(context: Context) : View(context)
{
    private val runnable = Runnable { invalidate() }
    private var context: Context = context
    private lateinit var handler: Handler
    private var UPDATE_MILLIS: Long = 30
    private var paused = false
    private lateinit var enemySpaceship: EnemyShip
    private lateinit var random: Random
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    init {
        val display: Display = (context as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        random = Random()
        enemySpaceship = EnemyShip(context)
        handler = Handler()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas)
    {
        enemySpaceship.enemyX += enemySpaceship.enemyVelocity
        // If enemySpaceship collides with right wall, reverse enemies velocity
        if (enemySpaceship.enemyX + enemySpaceship.getEnemySpaceshipWidth() >= screenWidth)
        {
            enemySpaceship.enemyVelocity *= -1
        }
        // If enemySpaceship collides with left wall, reverse enemies velocity
        if (enemySpaceship.enemyX <= 0)
        {
            enemySpaceship.enemyVelocity *= -1
        }

        // Draw the enemy Spaceship
        canvas.drawBitmap(enemySpaceship.getEnemySpaceship(), enemySpaceship.enemyX.toFloat(), enemySpaceship.enemyY.toFloat(), null)

        //If not paused make it runnable (like update in unity)
        if (!paused) handler.postDelayed(runnable, UPDATE_MILLIS)
    }
}
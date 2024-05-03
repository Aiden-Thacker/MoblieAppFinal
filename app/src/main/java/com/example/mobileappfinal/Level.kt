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
    private lateinit var  player: Player
    private lateinit var random: Random
    var screenWidth: Int = 0
    var screenHeight: Int = 0
    lateinit var background: Bitmap

    init {
        val display: Display = (context as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        random = Random()
        enemySpaceship = EnemyShip(context)
        player = Player(context, screenWidth, screenHeight)
        handler = Handler()
        background = BitmapFactory.decodeResource(context.resources, R.drawable.backgroundmobliegame)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas)
    {
        //Draw background for game
        canvas.drawBitmap(background, 0f, 0f, null)

        enemySpaceship.x += enemySpaceship.enemyVelocity
        // If enemySpaceship collides with right wall, reverse enemies velocity
        if (enemySpaceship.x + enemySpaceship.getEnemySpaceshipWidth() >= screenWidth)
        {
            enemySpaceship.enemyVelocity *= -1
        }
        // If enemySpaceship collides with left wall, reverse enemies velocity
        if (enemySpaceship.x <= 0)
        {
            enemySpaceship.enemyVelocity *= -1
        }

        // Draw the enemy Spaceship
        canvas.drawBitmap(enemySpaceship.getEnemySpaceship(), enemySpaceship.x.toFloat(), enemySpaceship.y.toFloat(), null)

        if(player.x > screenWidth - player.getPlayerWidth()){
            player.x = screenWidth - player.getPlayerWidth();
        }else if(player.x < 0){
            player.x = 0;
        }

        canvas.drawBitmap(player.getPlayer(), player.x.toFloat(), player.y.toFloat(), null)



        //If not paused make it runnable (like update in unity)
        if (!paused) handler.postDelayed(runnable, UPDATE_MILLIS)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean
    {
        val touchX = event.x;
        if (event.action == MotionEvent.ACTION_UP)
        {

        }
        if (event.action == MotionEvent.ACTION_DOWN)
        {

        }
        if (event.action == MotionEvent.ACTION_MOVE)
        {

            player.x = touchX.toInt()

        }

        return true;
    }
}


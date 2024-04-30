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
import android.view.Display
import android.view.MotionEvent
import android.view.View

import java.util.ArrayList
import java.util.Random
class Level(context: Context) : View(context)
{
    private var context: Context = context
    private lateinit var background: Bitmap
    private lateinit var lifeImage: Bitmap
    private lateinit var handler: Handler
    private var UPDATE_MILLIS: Long = 30
    private var points = 0
    //private var life = 3
    private lateinit var scorePaint: Paint
    private var TEXT_SIZE = 80
    private var paused = false
    //private var ourSpaceship: OurSpaceship
    private lateinit var enemySpaceship: EnemyShip
    private lateinit var random: Random
    private lateinit var enemyShots: ArrayList<Bullet>
    private lateinit var ourShots: ArrayList<Bullet>
    //private var explosion: Explosion
    //private var explosions: ArrayList<Explosion>
    private var enemyShotAction = false
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    init {
        val display: Display = (context as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        random = Random()
        enemyShots = ArrayList()
        ourShots = ArrayList()
        //explosions = ArrayList()
        //ourSpaceship = OurSpaceship(context)
        enemySpaceship = EnemyShip(context)
        handler = Handler()
        //background = BitmapFactory.decodeResource(context.resources, R.drawable.background)
        //lifeImage = BitmapFactory.decodeResource(context.resources, R.drawable.life)
        scorePaint = Paint()
        scorePaint.color = Color.RED
        scorePaint.textSize = TEXT_SIZE.toFloat()
        scorePaint.textAlign = Paint.Align.LEFT
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas)
    {
        enemySpaceship.enemyX += enemySpaceship.enemyVelocity
        // If enemySpaceship collides with right wall, reverse enemyVelocity
        if (enemySpaceship.enemyX + enemySpaceship.getEnemySpaceshipWidth() >= screenWidth) {
            enemySpaceship.enemyVelocity *= -1
        }
        // If enemySpaceship collides with left wall, again reverse enemyVelocity
        if (enemySpaceship.enemyX <= 0) {
            enemySpaceship.enemyVelocity *= -1
        }
        // Till enemyShotAction is false, enemy should fire shots from random travelled distance
//        if (!enemyShotAction) {
//            if (enemySpaceship.enemyX >= 200 + random.nextInt(400)) {
//                val enemyShot = Bullet(context, enemySpaceship.enemyX + enemySpaceship.getEnemySpaceshipWidth() / 2, enemySpaceship.enemyY)
//                enemyShots.add(enemyShot)
//                // We're making enemyShotAction to true so that enemy can take a short at a time
//                enemyShotAction = true
//            }
//            if (enemySpaceship.enemyX >= 400 + random.nextInt(800)) {
//                val enemyShot = Bullet(context, enemySpaceship.enemyX + enemySpaceship.getEnemySpaceshipWidth() / 2, enemySpaceship.enemyY)
//                enemyShots.add(enemyShot)
//                // We're making enemyShotAction to true so that enemy can take a short at a time
//                enemyShotAction = true
//            } else {
//                val enemyShot = Bullet(context, enemySpaceship.enemyX + enemySpaceship.getEnemySpaceshipWidth() / 2, enemySpaceship.enemyY)
//                enemyShots.add(enemyShot)
//                // We're making enemyShotAction to true so that enemy can take a short at a time
//                enemyShotAction = true
//            }
//        }
        // Draw the enemy Spaceship
        canvas.drawBitmap(enemySpaceship.getEnemySpaceship(), enemySpaceship.enemyX.toFloat(), enemySpaceship.enemyY.toFloat(), null)


        // Draw the enemy shot downwards our spaceship and if it's being hit, decrement life, remove
        // the shot object from enemyShots ArrayList and show an explosion.
        // Else if, it goes away through the bottom edge of the screen also remove
        // the shot object from enemyShots.
        // When there is no enemyShots no the screen, change enemyShotAction to false, so that enemy
        // can shot.
//        for (i in enemyShots.indices) {
//            enemyShots[i].bulletY += 15
//            canvas.drawBitmap(enemyShots[i].bullet, enemyShots[i].bulletX.toFloat(), enemyShots[i].bulletY.toFloat(), null)
//            if (enemyShots[i].bulletY >= screenHeight) {
//                enemyShots.removeAt(i)
//            }
//            if (enemyShots.size < 1) {
//                enemyShotAction = false
//            }
//        }
        // Draw our spaceship shots towards the enemy. If there is a collision between our shot and enemy
        // spaceship, increment points, remove the shot from ourShots and create a new Explosion object.
        // Else if, our shot goes away through the top edge of the screen also remove
        // the shot object from enemyShots ArrayList.
//        for (i in ourShots.indices) {
//            ourShots[i].bulletY -= 15
//            canvas.drawBitmap(ourShots[i].bullet, ourShots[i].bulletX.toFloat(), ourShots[i].bulletY.toFloat(), null)
//            if (ourShots[i].bulletX >= enemySpaceship.enemyX && ourShots[i].bulletX <= enemySpaceship.enemyX + enemySpaceship.getEnemySpaceshipWidth() &&
//                ourShots[i].bulletY <= enemySpaceship.getEnemySpaceshipWidth() && ourShots[i].bulletY >= enemySpaceship.enemyY
//            ) {
//                points++
//                ourShots.removeAt(i)
//                //explosion = Explosion(context, enemySpaceship.ex.toInt(), enemySpaceship.ey.toInt())
//                //explosions.add(explosion)
//            } else if (ourShots[i].bulletY <= 0) {
//                ourShots.removeAt(i)
//            }
//        }
        // If not paused, we’ll call the postDelayed() method on handler object which will cause the
        // run method inside Runnable to be executed after 30 milliseconds, that is the value inside
        // UPDATE_MILLIS.
//        if (!paused) {
//            handler.postDelayed(runnable, UPDATE_MILLIS)
//        }
    }
}
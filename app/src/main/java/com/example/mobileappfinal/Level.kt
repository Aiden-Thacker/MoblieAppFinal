package com.example.mobileappfinal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.os.Handler
import android.view.Display
import android.view.MotionEvent
import android.view.View
import java.util.Random

import java.util.ArrayList
import java.util.Random
import kotlin.math.sin

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
    var frequency: Int = 100
    var amplitude: Int = 150
    lateinit var background: Bitmap
    var enemyShotAction = false
    var playerShotAction = false
    private val enemyBullet: ArrayList<Bullet> = ArrayList()
    private val ourBullet: ArrayList<Bullet> = ArrayList()




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

        // Update enemy spaceship position based on sine function
        enemySpaceship.y = (screenHeight / 4 + sin(enemySpaceship.x.toDouble() / frequency) * amplitude).toInt()

        enemySpaceship.x += enemySpaceship.enemyVelocity
        // If enemySpaceship collides with right wall, reverse enemies velocity
        if (enemySpaceship.x + enemySpaceship.getEnemySpaceshipWidth() >= screenWidth) {
            enemySpaceship.enemyVelocity *= -1
        }
        // If enemySpaceship collides with left wall, reverse enemies velocity
        if (enemySpaceship.x <= 0) {
            enemySpaceship.enemyVelocity *= -1
        }

        if (!enemyShotAction && ::enemySpaceship.isInitialized) {
            when {
                enemySpaceship.x >= 200 + random.nextInt(400) -> {
                    val enemyShot = Bullet(context, enemySpaceship.x + enemySpaceship.getEnemySpaceshipWidth() / 2, enemySpaceship.y)
                    enemyBullet.add(enemyShot)
                    enemyShotAction = true
                }
                enemySpaceship.x >= 400 + random.nextInt(800) -> {
                    val enemyShot = Bullet(context, enemySpaceship.x + enemySpaceship.getEnemySpaceshipWidth() / 2, enemySpaceship.y)
                    enemyBullet.add(enemyShot)
                    enemyShotAction = true
                }
                else -> {
                    val enemyShot = Bullet(context, enemySpaceship.x + enemySpaceship.getEnemySpaceshipWidth() / 2, enemySpaceship.y)
                    enemyBullet.add(enemyShot)
                    enemyShotAction = true
                }
            }
        }
        if (!playerShotAction && ::player.isInitialized) {
            when {
                player.x >= 200 + random.nextInt(400) -> {
                    val playerShot = Bullet(context, player.x + player.getPlayerWidth() / 2, player.y)
                    ourBullet.add(playerShot)
                    enemyShotAction = true
                }
                enemySpaceship.x >= 400 + random.nextInt(800) -> {
                    val playerShot = Bullet(context, player.x + player.getPlayerWidth() / 2, player.y)
                    ourBullet.add(playerShot)
                    enemyShotAction = true
                }
                else -> {
                    val playerShot = Bullet(context, player.x + player.getPlayerWidth() / 2, player.y)
                    ourBullet.add(playerShot)
                    enemyShotAction = true
                }
            }
        }




        // Draw the enemy Spaceship
        canvas.drawBitmap(enemySpaceship.getEnemySpaceship(), enemySpaceship.x.toFloat(), enemySpaceship.y.toFloat(), null)

        if(player.x > screenWidth - player.getPlayerWidth()){
            player.x = screenWidth - player.getPlayerWidth();
        }else if(player.x < 0){
            player.x = 0;
        }

        canvas.drawBitmap(player.getPlayer(), player.x.toFloat(), player.y.toFloat(), null)

        // Draw the enemy shot downwards our spaceship and if it's being hit, decrement life, remove
        // the shot object from enemyShots ArrayList and show an explosion.
        // Else if, it goes away through the bottom edge of the screen also remove
        // the shot object from enemyShots.
        // When there is no enemyShots no the screen, change enemyShotAction to false, so that enemy
        // can shot.
        val iterator = enemyBullet.iterator()
        while (iterator.hasNext()) {
            val bullet = iterator.next()
            bullet.y += 15
            canvas.drawBitmap(
                bullet.getBulletBitmap(),
                bullet.x.toFloat(),
                bullet.y.toFloat(),
                null
            )
            if (bullet.x >= player.x && bullet.x <= player.x + player.getPlayerWidth() && bullet.y >= player.y && bullet.y <= screenHeight) {
                iterator.remove()
            } else if (bullet.y >= screenHeight) {
                iterator.remove()
            }
        }
        if (enemyBullet.isEmpty()) {
            enemyShotAction = false
        }
        val iterator2 = ourBullet.iterator()
        while (iterator2.hasNext()) {
            val bullet = iterator2.next()
            bullet.y -= 15
            canvas.drawBitmap(
                bullet.getBulletBitmap(),
                bullet.x.toFloat(),
                bullet.y.toFloat(),
                null
            )
            if (bullet.x >= enemySpaceship.x && bullet.x <= enemySpaceship.x + enemySpaceship.getEnemySpaceshipWidth() && bullet.y >= enemySpaceship.y && bullet.y <= screenHeight) {
                iterator2.remove()
            } else if (bullet.y >= screenHeight) {
                iterator2.remove()
            }
        }
        if (ourBullet.isEmpty()) {
            playerShotAction = false
        }

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


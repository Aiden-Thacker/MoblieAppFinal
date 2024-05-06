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
import java.util.Random
import java.util.ArrayList
import kotlin.math.sin

class Level(context: Context) : View(context)
{
    private val runnable = Runnable { invalidate() }
    private var context: Context = context
    private var handler: Handler? = null
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
    private var playerShotDelay: Long = 0
    private var playerLastShotTime: Long = 0
    private lateinit var shipHealth: Bitmap
    private var points = 0
    private val textSize = 120
    private val scorePaint = Paint().apply {
        color = Color.MAGENTA
        textSize = 120F
        textAlign = Paint.Align.LEFT
    }




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
        background = BitmapFactory.decodeResource(context.resources, R.drawable.gamescreen2)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas)
    {
        //Draw background for game
        canvas.drawBitmap(background, 0f, 0f, null)
        //Draw the players health and points
        canvas.drawText("Pts: $points", 0f, textSize.toFloat(), scorePaint)

        // Load the health image
        val originalHealthBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ship)
        val scaleFactor = 0.50f // 50% smaller

        // Resize the health image
        val scaledHealthBitmap = Bitmap.createScaledBitmap(originalHealthBitmap,
            (originalHealthBitmap.width * scaleFactor).toInt(),
            (originalHealthBitmap.height * scaleFactor).toInt(), false)

        // Draw the health images
        for (i in player.life downTo 1)
        {
            // Adjust the position where the health images are drawn
            val healthImageX = screenWidth - (scaledHealthBitmap.width * i) - 20 // Adjust as needed
            val healthImageY = 20 // Adjust as needed
            canvas.drawBitmap(scaledHealthBitmap, healthImageX.toFloat(), healthImageY.toFloat(), null)
        }

        //When the player loses all lives go to game over screen
        if (player.life == 0) {
            paused = true
            handler = null
//            val intent = Intent(context, GameOver::class.java)
//            intent.putExtra("points", points)
//            context.startActivity(intent)
//            (context as Activity).finish()
        }

        // Update enemy spaceship position based on sine function
        enemySpaceship.y = (screenHeight / 4 + sin(enemySpaceship.x.toDouble() / frequency) * amplitude).toInt()

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

        //Enables the ability for the Enemy to shoot at the Player
        if (!enemyShotAction && ::enemySpaceship.isInitialized)
        {
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

        // Player Shoots at a random-ish Time
        val currentTime = System.currentTimeMillis()
        if (currentTime - playerLastShotTime >= playerShotDelay)
        {
            playerLastShotTime = currentTime
            playerShotDelay = (1000 + random.nextInt(3000)).toLong() // Random delay between 1 to 3 seconds
            val playerShot = Bullet(context, player.x + player.getPlayerWidth() / 2, player.y)
            ourBullet.add(playerShot)
            playerShotAction = true
        }

        // Draw the enemy Spaceship
        canvas.drawBitmap(enemySpaceship.getEnemySpaceship(), enemySpaceship.x.toFloat(), enemySpaceship.y.toFloat(), null)

        //Player Borders
        if(player.x > screenWidth - player.getPlayerWidth())
        {
            player.x = screenWidth - player.getPlayerWidth();
        }else if(player.x < 0)
        {
            player.x = 0;
        }

        //Draw Player
        canvas.drawBitmap(player.getPlayer(), player.x.toFloat(), player.y.toFloat(), null)

        // Draws Enemy's bullets
        val iterator = enemyBullet.iterator()
        while (iterator.hasNext())
        {
            val bullet = iterator.next()
            bullet.y += 15
            canvas.drawBitmap(
                bullet.getBulletBitmap(),
                bullet.x.toFloat(),
                bullet.y.toFloat(),
                null
            )
            if (bullet.x >= player.x && bullet.x <= player.x + player.getPlayerWidth() && bullet.y >= player.y && bullet.y <= screenHeight)
            {
                iterator.remove()
                player.life--
            } else if (bullet.y <= 0)
            {
                iterator.remove()
            }
        }
        if (enemyBullet.isEmpty())
        {
            enemyShotAction = false
        }

        //Draw Player's bullets
        val iterator2 = ourBullet.iterator()
        while (iterator2.hasNext())
        {
            val bullet = iterator2.next()
            bullet.y -= 15
            canvas.drawBitmap(
                bullet.getBulletBitmap(),
                bullet.x.toFloat(),
                bullet.y.toFloat(),
                null
            )
            //Checks if it Collides with Enemy
            if (bullet.x + bullet.getBulletBitmap().width >= enemySpaceship.x &&
                bullet.x <= enemySpaceship.x + enemySpaceship.getEnemySpaceshipWidth() &&
                bullet.y + bullet.getBulletBitmap().height >= enemySpaceship.y &&
                bullet.y <= enemySpaceship.y + enemySpaceship.getEnemySpaceshipHeight())
            {
                iterator2.remove()
                enemySpaceship.enemyLife--;
                if (enemySpaceship.enemyLife <= 0) {
                    enemyShotAction = false // Stop enemy shooting
                    spawnNewEnemy() // Spawn a new enemy
                    points++
                }
            } else if (bullet.y >= screenHeight)
            {
                iterator2.remove()
            }
        }
        if (ourBullet.isEmpty())
        {
            playerShotAction = false
        }

        //If not paused make it runnable (like update in unity)
        if (!paused) handler?.postDelayed(runnable, UPDATE_MILLIS)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean
    {
        val touchX = event.x;
        val touchY = event.y;
        if (event.action == MotionEvent.ACTION_DOWN)
        {

        }
        if (event.action == MotionEvent.ACTION_MOVE)
        {

            player.x = touchX.toInt()

        }

        return true;
    }

    //Spawn New Enemy Upon Death
    fun spawnNewEnemy()
    {
        enemySpaceship = EnemyShip(context)
        enemySpaceship.enemyLife = 1
    }
}


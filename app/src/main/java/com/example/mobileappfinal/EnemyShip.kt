package com.example.mobileappfinal

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.Random

class EnemyShip(private val context: Context)
{
    private val enemySpaceship: Bitmap
    var enemyX: Int = 0
    var enemyY: Int = 0
    var enemyVelocity: Int = 0
    private val random: Random

    init
    {
        enemySpaceship = BitmapFactory.decodeResource(context.resources, R.drawable.shipsidesfive)
        random = Random()
        enemyX = 200 + random.nextInt(400)
        enemyY = 0
        enemyVelocity = 14 + random.nextInt(10)
    }

    fun getEnemySpaceship(): Bitmap
    {
        return enemySpaceship
    }

    fun getEnemySpaceshipWidth(): Int
    {
        return enemySpaceship.width
    }

    fun getEnemySpaceshipHeight(): Int
    {
        return enemySpaceship.height
    }
}
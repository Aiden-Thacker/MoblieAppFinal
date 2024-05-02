package com.example.mobileappfinal

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import java.util.Random

class EnemyShip(private val context: Context)
{
    private val originalEnemySpaceship: Bitmap
    private lateinit var scaledEnemySpaceship: Bitmap
    var x: Int = 0
    var y: Int = 0
    var enemyVelocity: Int = 0
    private val random: Random

    //Spawns the ship in
    init {
        originalEnemySpaceship = BitmapFactory.decodeResource(context.resources, R.drawable.shipsidesfive)
        random = Random()
        x = 200 + random.nextInt(400)
        y = 0
        enemyVelocity = 14 + random.nextInt(10)
        scaleBitmap()
        rotateBitmap()
    }
    //Scaling the image on of the AI
    private fun scaleBitmap()
    {
        val scaleFactor = 1.5f
        scaledEnemySpaceship = Bitmap.createScaledBitmap(originalEnemySpaceship,
            (originalEnemySpaceship.width.toFloat() * scaleFactor).toInt(),
            (originalEnemySpaceship.height.toFloat() * scaleFactor).toInt(), false)
    }

    //Rotates the image to face Player
    private fun rotateBitmap()
    {
        val matrix = Matrix()
        matrix.postRotate(180f)

        scaledEnemySpaceship = Bitmap.createBitmap(scaledEnemySpaceship, 0, 0, scaledEnemySpaceship.width, scaledEnemySpaceship.height, matrix, true)
    }

    //Returns the scale of the AI image
    fun getEnemySpaceship(): Bitmap
    {
        return scaledEnemySpaceship
    }

    //Returns the width of the AI image
    fun getEnemySpaceshipWidth(): Int
    {
        return scaledEnemySpaceship.width
    }

    //Returns the height of the AI image
    fun getEnemySpaceshipHeight(): Int
    {
        return scaledEnemySpaceship.height
    }
}
package com.example.mobileappfinal

import android.content.Context

import android.graphics.Bitmap

import android.graphics.BitmapFactory
import java.util.Random

class Player(var context: Context,var screenWidth: Int, var screenHeight: Int)
{
    var ourSpaceship: Bitmap
    var x: Int
    var y: Int
    var life: Int = 3
    var random: Random

    init
    {
        //Shrinking the player by 25%
        //God I hope this works for Liam
        val originalBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ship)
        val scaleFactor = 0.75f
        ourSpaceship = Bitmap.createScaledBitmap(originalBitmap,
            (originalBitmap.width * scaleFactor).toInt(),
            (originalBitmap.height * scaleFactor).toInt(), false)
        random = Random()
        x = random.nextInt(screenWidth)
        y = screenHeight - ourSpaceship.getHeight()
    }

    fun getPlayer(): Bitmap
    {
        return ourSpaceship
    }

    fun getPlayerWidth(): Int
    {
        return ourSpaceship.width
    }

    fun getPlayerHeight(): Int
    {
        return ourSpaceship.height
    }

}



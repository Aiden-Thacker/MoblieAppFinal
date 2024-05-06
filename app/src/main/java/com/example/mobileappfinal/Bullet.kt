package com.example.mobileappfinal

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Bullet(private val context: Context, var x: Int, var y: Int)
{
    val bullet: Bitmap;

    init
    {
        val originalBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.effectpurple)

        // Calculate the scaled width and height
        val scaledWidth = originalBitmap.width / 2
        val scaledHeight = originalBitmap.height / 2

        // Create a scaled version of the bitmap
        bullet = Bitmap.createScaledBitmap(originalBitmap, scaledWidth, scaledHeight, false)

        // Recycle the original bitmap to free up memory
        originalBitmap.recycle()
    }

    fun getBulletBitmap(): Bitmap
    {
        return bullet
    }

    fun getBulletWidth(): Int
    {
        return bullet.width
    }

    fun getBulletHeight(): Int
    {
        return bullet.height
    }
}
package com.example.mobileappfinal

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Bullet(private val context: Context, var x: Int, var y: Int)
{
    val bullet: Bitmap;

    init
    {
        bullet = BitmapFactory.decodeResource(context.resources, R.drawable.effectpurple)
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
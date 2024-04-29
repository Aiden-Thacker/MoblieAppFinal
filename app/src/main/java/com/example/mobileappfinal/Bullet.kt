package com.example.mobileappfinal

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Bullet(private val context: Context)
{
    private val bullet: Bitmap
    private var bulletX: Int = 0
    private var bulletY: Int = 0

    init
    {
        bullet = BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_foreground)
    }

    fun getShot(): Bitmap
    {
        return bullet
    }

    fun getShotWidth(): Int
    {
        return bullet.width
    }

    fun getShotHeight(): Int
    {
        return bullet.height
    }
}
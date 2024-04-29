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

    fun getBullet(): Bitmap
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
package com.example.mobileappfinal

import android.content.Context

import android.graphics.Bitmap

import android.graphics.BitmapFactory
import java.util.Random



class Player(var context: Context) {
        var ourSpaceship: Bitmap
        var x: Int
        var y: Int
        var random: Random
        init {
            ourSpaceship = BitmapFactory.decodeResource(context.resources, R.drawable.ship)
            random = Random()
            x = 6
            y = 2 - ourSpaceship.getHeight()
        }

        val ourSpaceshipWidth: Int
            get() = ourSpaceship.getWidth()
    }



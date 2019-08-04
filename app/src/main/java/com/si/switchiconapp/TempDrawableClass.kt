package com.si.switchiconapp

import android.graphics.Canvas
import android.graphics.drawable.LayerDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity


class TempDrawableClass : LayerDrawable(
    arrayOf(
        ContextCompat.getDrawable(MyApplication.context, R.mipmap.ic_launcher_foreground_apple),
        ContextCompat.getDrawable(MyApplication.context, R.mipmap.ic_launcher_foreground_pear),
        ContextCompat.getDrawable(MyApplication.context, R.mipmap.ic_launcher_foreground_grapes)
    )
) {


    override fun draw(canvas: Canvas) {
        val selectedIndex = MyApplication.context.getSharedPreferences("GLOBAL", AppCompatActivity.MODE_PRIVATE)
            .getInt(MainActivity.SELECTED_ICON_INDEX_KEY, 0)
        for (i in 0 until  numberOfLayers) {
            val drawable = getDrawable(i)
            drawable.alpha = if (i == selectedIndex) 255
            else 0

        }
        super.draw(canvas)

    }
}
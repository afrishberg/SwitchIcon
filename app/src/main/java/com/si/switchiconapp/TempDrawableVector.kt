package com.si.switchiconapp

import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.devs.vectorchildfinder.VectorChildFinder

class TempDrawableVector : VectorDrawable() {

    init {
    }


    companion object {
        val drawableArray = listOf(
            R.drawable.ic_android_black_24dp,
            R.drawable.ic_beenhere_black_24dp,
            R.drawable.ic_bluetooth_audio_black_24dp
        )
    }

    override fun getIntrinsicWidth(): Int {
        return MyApplication.context.resources.getDimensionPixelOffset(R.dimen.size)
    }

    override fun getIntrinsicHeight(): Int {
        return MyApplication.context.resources.getDimensionPixelOffset(R.dimen.size)
    }

    override fun draw(canvas: Canvas) {
        val randomInteger = (1..2).shuffled().first()
//        val vcf = VectorChildFinder()
        val drawable = ContextCompat.getDrawable(MyApplication.context, drawableArray[randomInteger])!!
        drawable.draw(canvas)
    }
}

//class Finder:
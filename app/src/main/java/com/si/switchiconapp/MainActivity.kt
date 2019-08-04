package com.si.switchiconapp

import android.content.ComponentName
import android.content.pm.PackageManager
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.icon_layout.view.*

class MainActivity : AppCompatActivity() {


    companion object {
        val packagePrefix = "com.si.switchiconapp."
        val iconDataSet = arrayOf(
            IconData(
                iconRes = R.mipmap.ic_launcher_apple_round,
                name = "apple",
                aliasName = "MainActivity"
            ),
            IconData(
                iconRes = R.mipmap.ic_launcher_pear_round,
                name = "pear",
                aliasName = "Alias1"
            ),
            IconData(
                iconRes = R.mipmap.ic_launcher_grapes_round,
                name = "grapes",
                aliasName = "Alias2"
            )
        )

        val SELECTED_ICON_INDEX_KEY = "selected_icon_index_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawIcons()
        setIconAndAlias()
    }

    private fun drawIcons() {
        for (i in 0 until all_icons_container.childCount) {
            val childAt = all_icons_container.getChildAt(i)
            childAt.icon_image.setImageResource(iconDataSet[i].iconRes)
            childAt.icon_name.text = iconDataSet[i].name
            childAt.setOnClickListener {
                setSelectedIconIndex(i)
                setIconAndAlias()
            }

        }
    }

    private fun setIconAndAlias() {
        populateSelectedIcon()
        setEnabledAlias()
    }

    fun getSelectedIconIndex() : Int {
        return getSharedPreferences("GLOBAL", MODE_PRIVATE).getInt(SELECTED_ICON_INDEX_KEY, 0)
    }

    fun setSelectedIconIndex(index: Int) {
        getSharedPreferences("GLOBAL", MODE_PRIVATE).edit().putInt(SELECTED_ICON_INDEX_KEY, index).apply()
    }

    fun populateSelectedIcon() {
        val selectedIconIndex = getSelectedIconIndex()
        for (i in 0 until all_icons_container.childCount) {
            val view = all_icons_container.getChildAt(i)
            if (i == selectedIconIndex) {
                view.setBackgroundColor(ContextCompat.getColor(this, R.color.light_blue_background))
            } else {
                view.setBackgroundColor(Color.TRANSPARENT)
            }
        }
        temp_iv.setImageDrawable(null)
        temp_iv.setImageDrawable(TempDrawableClass())

    }

    private fun setEnabledAlias() {
        val pm = packageManager
//        val selectedIconIndex = getSelectedIconIndex()
        val selectedIconIndex = 0
        iconDataSet.forEachIndexed { index, iconData ->
            val enabled = index == selectedIconIndex
            pm.setComponentEnabledSetting(
                ComponentName(
                    this@MainActivity,
                    packagePrefix + iconData.aliasName
                ),
                if (enabled) PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                else PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                ,
                PackageManager.DONT_KILL_APP
            )

        }
    }
}

data class IconData(@DrawableRes val iconRes: Int, val name: String, val aliasName: String)

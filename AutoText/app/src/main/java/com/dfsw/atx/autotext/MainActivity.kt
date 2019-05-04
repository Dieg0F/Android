package com.dfsw.atx.autotext

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.widget.TextViewCompat
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TextViewCompat.setAutoSizeTextTypeWithDefaults(title_text,
                TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)

        TextViewCompat.setAutoSizeTextTypeWithDefaults(body_text,
                TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)

        TextViewCompat.setAutoSizeTextTypeWithDefaults(footer_text,
                TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)


        val seekBar = seek_bar as SeekBar
        seekBar.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                setHorizontalMargin(title_text, p1)
                setHorizontalMargin(body_text, p1)
                setHorizontalMargin(footer_text, p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    fun setHorizontalMargin(view: View, value: Int) {
        val newLayoutParams = view.layoutParams as ConstraintLayout.LayoutParams

        newLayoutParams.marginEnd = value
        newLayoutParams.marginStart = value

        view.layoutParams = newLayoutParams
    }
}

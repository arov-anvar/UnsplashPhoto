package com.anvar.unsplashphoto.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.anvar.unsplashphoto.R
import kotlinx.android.synthetic.main.count_view.view.*

class CountView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.count_view, this)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountView, 0 , 0)
        val countText = typedArray.getInt(R.styleable.CountView_countText, 0)
        val titleText = typedArray.getText(R.styleable.CountView_titleText)
        countTxt.text = countText.toString()
        titleTxt.text = titleText
    }

    fun setCount(count: Int) {
        countTxt.text = count.toString()
    }
}
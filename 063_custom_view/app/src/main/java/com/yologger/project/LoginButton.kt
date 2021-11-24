package com.yologger.project

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class LoginButton: RelativeLayout {

    private lateinit var relativeLayout: RelativeLayout
    private lateinit var imageViewIcon: ImageView
    private lateinit var textViewButtonText: TextView

    // Called when programmatically
    constructor(context: Context): super(context) {
        inflateView()
    }

    // Called in XML (Layout Editor)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        inflateView()
        getAttrs(attrs)
    }

    // Called in XML with theme
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs) {
        inflateView()
        getAttrs(attrs, defStyle)
    }

    private fun inflateView() {
        val view = inflate(context, R.layout.login_button, this)
        relativeLayout = view.findViewById(R.id.login_button_rl)
        imageViewIcon = view.findViewById(R.id.login_button_iv)
        textViewButtonText = view.findViewById(R.id.login_button_tv)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginButton)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginButton, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val iconSrc = typedArray.getResourceId(R.styleable.LoginButton_iconSrc, 0)
        val buttonText = typedArray.getText(R.styleable.LoginButton_buttonText)
        val backgroundTint = typedArray.getColor(R.styleable.LoginButton_backgroundTint, Color.TRANSPARENT)

        imageViewIcon.setImageResource(iconSrc)
        textViewButtonText.text = buttonText
        relativeLayout.setBackgroundColor(backgroundTint)
    }

    fun setIcon(resId: Int) {
        imageViewIcon.setImageResource(resId)
    }

    fun setText(text: String) {
        textViewButtonText.text = text
    }
    
}
package com.yologger.project

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes

class PostView: LinearLayout {

    private lateinit var mImageViewAvatar: ImageView
    private lateinit var mTextViewWriter: TextView
    private lateinit var mTextViewContent: TextView

    constructor(context: Context): super(context) {
        inflateLayout()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        inflateLayout()
        applyAttrs(attrs)
    }

    private fun inflateLayout() {
        val view = inflate(context, R.layout.view_post, this)
        mImageViewAvatar = view.findViewById(R.id.view_post_iv_avatar)
        mTextViewWriter = view.findViewById(R.id.view_post_tv_writer)
        mTextViewContent = view.findViewById(R.id.view_post_tv_content)
    }

    private fun applyAttrs(attrs: AttributeSet) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.PostView)
        val avatarSrc = typedArray.getResourceId(R.styleable.PostView_avatarSrc, 0)
        val writerText = typedArray.getText(R.styleable.PostView_writerText)
        val contentText = typedArray.getText(R.styleable.PostView_contentText)
        typedArray.recycle()

        mImageViewAvatar.setImageResource(avatarSrc)
        mTextViewWriter.text = writerText
        mTextViewContent.text = contentText
    }

    fun setAvatarImage(@DrawableRes resId: Int) {
        mImageViewAvatar.setImageResource(resId)
    }

    fun setWriter(text: String) {
        mTextViewWriter.text = text
    }

    fun setContext(text: String) {
        mTextViewContent.text = text
    }
}
package com.example.customsearch

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.Layout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.custom_search_view.view.*

class CustomSearchView : LinearLayout, View.OnClickListener {
    private lateinit var mCallback: OnSearchButtonClickedListener

    override fun onClick(view: View?) {
        if (view?.id == buttonDelete.id) {
            searchText.setText("")
        } else if (view?.id == buttonSearch.id) {
            mCallback.onSearchButtonClicked(this, searchText.text.toString())
        }
    }

    interface OnSearchButtonClickedListener {
        fun onSearchButtonClicked(source: CustomSearchView, currentText: String)
    }

    fun setOnSearchButtonClickedListener(listener: OnSearchButtonClickedListener) {
        mCallback = listener
    }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.custom_search_view, this)
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomSearchView, defStyle, 0)
        val charSequence = typedArray.getString(R.styleable.CustomSearchView_hintText)

        if (charSequence != null) {
            setHintText(charSequence.toString())
        }

        val textSize: Int = typedArray.getDimensionPixelOffset(R.styleable.CustomSearchView_textSize, 16)

        if (textSize > 0) {
            setTextSize(textSize)
        }

        typedArray.recycle()

        buttonSearch.setOnClickListener(this)
        buttonDelete.setOnClickListener(this)
    }

    fun setHintText(text: String) {
        searchText.setHint(text)
        invalidate()
        requestLayout()
    }

    fun setTextSize(size: Int) {
        searchText.setTextSize(size.toFloat())
        invalidate()
        requestLayout()
    }
}
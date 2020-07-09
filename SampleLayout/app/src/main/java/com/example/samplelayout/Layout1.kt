package com.example.samplelayout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class Layout1 : LinearLayout {
    var imageView: ImageView? = null
    var textView: TextView? = null
    var textView2: TextView? = null

    constructor(context: Context?) : super(context) { init(context) }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { init(context) }

    private fun init(context: Context?) {
        val inflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout1, this, true)

        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)
    }

    public fun setImage(resId: Int) {
        imageView?.setImageResource(resId)
    }

    public fun setName(name: String) {
        textView?.text = name
    }

    public fun setMobile(mobile: String) {
        textView2?.text = mobile
    }
}
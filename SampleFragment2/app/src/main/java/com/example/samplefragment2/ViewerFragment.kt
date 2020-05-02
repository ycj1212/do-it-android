package com.example.samplefragment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ViewerFragment : Fragment() {
    var imageView: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_viewer, container, false)

        imageView = rootView.findViewById(R.id.imageView)

        return rootView
    }

    fun setImage(resId: Int) {
        imageView?.setImageResource(resId)
    }
}
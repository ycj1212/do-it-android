package com.example.samplefragment2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

interface ImageSelectionCallback {
    fun onImageSelected(position: Int)
}

class ListFragment : Fragment() {
    var callback: ImageSelectionCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ImageSelectionCallback) {
            callback = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)
        val button1 = rootView.findViewById<Button>(R.id.button01)
        button1.setOnClickListener{
            if (callback != null) {
                callback?.onImageSelected(0)
            }
        }
        val button2 = rootView.findViewById<Button>(R.id.button02)
        button2.setOnClickListener{
            if (callback != null) {
                callback?.onImageSelected(1)
            }
        }
        val button3 = rootView.findViewById<Button>(R.id.button03)
        button3.setOnClickListener{
            if (callback != null) {
                callback?.onImageSelected(2)
            }
        }

        return rootView
    }
}
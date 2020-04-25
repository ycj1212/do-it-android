package com.example.samplefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        val button = rootView.findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val activity = activity as? MainActivity
            activity?.onFragmentChanged(0)
        }

        return rootView
    }
}

package com.example.doitmission22

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class Fragment1() : Fragment() {
    private lateinit var edittext: EditText
    private lateinit var edittext2: EditText
    private lateinit var edittext3: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment1, container, false)

        edittext = view.findViewById(R.id.edittext)
        edittext2 = view.findViewById(R.id.edittext2)
        edittext3 = view.findViewById(R.id.edittext3)

        val button: Button = view.findViewById(R.id.button)
        button.setOnClickListener {
            val title = edittext.text.toString()
            val author = edittext2.text.toString()
            val contents = edittext3.text.toString()

            // 데이터베이스에 저장

            Toast.makeText(view.context, "저장완료", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
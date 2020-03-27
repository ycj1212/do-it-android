package com.example.sampleorientation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var name: String? = null
    var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showToast("onCreate 호출됨.")

        editText = findViewById(R.id.editText)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            name = editText?.text.toString()
            showToast("입력된 값을 변수에 저장했습니다 : $name")
        }

        if (savedInstanceState != null) {
            name = savedInstanceState.getString("name")
            showToast("값을 복원했습니다 : $name")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("name", name)
    }

    override fun onStart() {
        super.onStart()
        showToast("onStart 호출됨.")
    }

    override fun onStop() {
        super.onStop()
        showToast("onStop 호출됨.")
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("onDestroy 호출됨.")
    }

    private fun showToast(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show()
    }
}

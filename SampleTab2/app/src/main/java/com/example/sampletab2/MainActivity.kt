package com.example.sampletab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var fragment1: Fragment1? = null
    var fragment2: Fragment2? = null
    var fragment3: Fragment3? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment1 = Fragment1()
        fragment2 = Fragment2()
        fragment3 = Fragment3()

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment1!!).commit()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tab1 -> {
                    Toast.makeText(applicationContext, "첫 번째 탭 선택됨", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment1!!).commit()
                    true
                }
                R.id.tab2 -> {
                    Toast.makeText(applicationContext, "두 번째 탭 선택됨", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment2!!).commit()
                    true
                }
                R.id.tab3 -> {
                    Toast.makeText(applicationContext, "세 번째 탭 선택됨", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment3!!).commit()
                    true
                }
                else -> false
            }
        }
    }
}
